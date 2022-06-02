package gov.gsa.faas.tools.submissionmover;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SubmissionMoverApp {

    private HttpClient formIoClient = null;
    private String targetEnvAuthToken = null;
    private String targetEnvPath = null;
    private ObjectMapper mapper = null;

    public SubmissionMoverApp(String targetEnvAuthToken, String targetEnvPath) {
        this.formIoClient = HttpClient.newBuilder().proxy(ProxySelector.getDefault()).build();
        this.targetEnvAuthToken = targetEnvAuthToken;
        this.targetEnvPath = targetEnvPath;
        this.mapper = new ObjectMapper();
    }

    
    /** 
     * Read the form at the provided path into a FormDefinition structure
     * 
     * @param formDefinitionPath The full path to the Form.io form definition json file
     * @return FormDefinition The FormDefinition object populated from the file at the path provided
     * @throws IOException if the file cannot be read
     */
    public JsonNode readSubmissionsJson(String submissionsPath) throws IOException {

        Path path = FileSystems.getDefault().getPath(submissionsPath);
        String submissionsContent = Files.readString(path, StandardCharsets.US_ASCII);

        // read json into Submission[] using Jackson ObjectMapper
        return mapper.readTree(submissionsContent);
    }

  
    /** 
     * Publishes the FormDefinition to the target environment, creating the form if it doesn't exist
     * or updating the form if it does exist
     * 
     * @param formDef The FormDefinition to publish
     * @return String A status value.  "error" if there was a problem, "created" if the form was created, and "updated" if the form already existed and was modified
     * @throws IOException if the form.io destination location cannot be reached
     * @throws InterruptedException if the rest call to the destination location is interrupted
     * @throws URISyntaxException if the path to create or update the form cannot be formed
     */
    public String updateSubmissions(JsonNode submissions) throws IOException, InterruptedException, URISyntaxException {

        String basePutPath = targetEnvPath + "/" + "submission";
        String publishResponse = "error";
        int numSubmissionsUpdated = 0;

        if (submissions.isArray()) {
            for (JsonNode singleSubmission : submissions) 
            {
                //get submission id from singleSubmission
                String submissionId = "";
                submissionId = singleSubmission.at("/_id").asText();

                URI putUri = new URI(basePutPath + "/" + submissionId);

                // try to update the submission
                HttpRequest formUpdateRequest = HttpRequest.newBuilder().header("x-admin-key", targetEnvAuthToken)
                        .header("Content-Type", "application/json").uri(putUri)
                        .PUT(BodyPublishers.ofString(singleSubmission.toPrettyString())).build();

                HttpResponse<String> formUpdateResponse = formIoClient.send(formUpdateRequest,
                        HttpResponse.BodyHandlers.ofString());

                if (formUpdateResponse.statusCode() == 200) {
                    numSubmissionsUpdated++;
                }
            }

            if (numSubmissionsUpdated == submissions.size()) {
                publishResponse = "updated " + numSubmissionsUpdated;
            }
            else {
                publishResponse = "error - only " + numSubmissionsUpdated + " of " + submissions.size() + " updated";
            }
        }

        return publishResponse;
    }


    /**
     * Takes a given form defintion json file and publishes it to the Target environment.
     * Will create the form if it doesn't already exist, or will update the form if it does exist.
     * 
     * @param args Three arguments are required:
     *             <ol>
     *             <li>JSON Submissions File Path: The full path to the Form.io submissions json file to update in the target environment</li>
     *             <li>Target Environment Auth Token: The x-token value to be provided in the header to authenticate to the Form.io API server</li>
     *             <li>Target Environment Path: The full path to the target environment</li>
     *             </ol>
     * @throws JsonProcessingException
     */
    public static void main(String[] args) {
        if (args == null || args.length != 3) {
            System.out.println("Three arguments are required.\n"
                    + "JSON Submissions File Path: The full path to the Form.io submissions json file to update in the target environment \n"
                    + "Target Environment Auth Token: The x-token value to be provided in the header to authenticate to the Form.io API server \n"
                    + "Target Environment Path: The full path to the target environment \n");
        } else {
            String jsonSubmissionsPath = args[0];
            String targetEnvAuthToken = args[1];
            String targetEnvPath = args[2];

            SubmissionMoverApp SubmissionMoverApp = new SubmissionMoverApp(targetEnvAuthToken, targetEnvPath);

            try {
                //Read the form into a JsonNode structure
                JsonNode submissions = SubmissionMoverApp.readSubmissionsJson(jsonSubmissionsPath);

                String updateStatus = SubmissionMoverApp.updateSubmissions(submissions);

                if(!updateStatus.contains("error")){
                    System.out.println("Successfully " + updateStatus + " the submissions from the file at {" + jsonSubmissionsPath + "} to {" + targetEnvPath + "}.");
                }
                else{
                    System.out.println("Unable to update the submissions from the file at {" + jsonSubmissionsPath + "} to {" + targetEnvPath + "}. " + updateStatus);
                }
            } catch (Exception e) {
                System.out.println("Unable to update the submissions from the file at {" + jsonSubmissionsPath + "} to {" + targetEnvPath + "}.");
                e.printStackTrace();
            }
        }
    }
}
