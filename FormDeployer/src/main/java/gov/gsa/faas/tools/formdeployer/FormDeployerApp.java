/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gov.gsa.faas.tools.formdeployer;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class FormDeployerApp {

    private HttpClient formIoClient = null;
    private static String createEndpoint = "/form";

    public FormDeployerApp() {
        formIoClient = HttpClient.newBuilder().proxy(ProxySelector.getDefault()).build();
    }

    private URI appendRelativeUrl(URI basePath, String relativeUrl) throws URISyntaxException, MalformedURLException {
        URL baseUrl = basePath.toURL();
        URL appendedUrl = new URL(baseUrl.getProtocol(), baseUrl.getHost(), baseUrl.getPort(), baseUrl.getPath() + relativeUrl, null);
        return appendedUrl.toURI();
    }

    //TODO - change to Java 8 version
    private HttpRequest formulateFormCreateRequest(String authToken, URI basePath, String formJson)
            throws URISyntaxException, MalformedURLException {
        return HttpRequest.newBuilder().header("x-token", authToken).uri(appendRelativeUrl(basePath, "/form")).POST(BodyPublishers.ofString(formJson))
        .build();
    }

    /**
     * Connect to the rest endpoint at apiPath using authToken and returns a
     * JsonNode object parsed from the json returned from the rest call
     * 
     * @param apiPath   the full path to a json rest endpoint
     * @param authToken the x-token value to be provided in the header to
     *                  authenticate to the json rest endpoint
     * @return JsonNode a JsonNode object representing the json parsed from the rest
     *         endpoint
     * @throws InterruptedException
     * @throws IOException
     * @throws URISyntaxException
     */
    public String getFormJson(String apiPath, String authToken)
            throws IOException, InterruptedException, URISyntaxException {

        HttpRequest apiRequest = HttpRequest.newBuilder().header("x-token", authToken).uri(new URI(apiPath)).GET()
                .build();

        HttpResponse<String> apiResponse = formIoClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());

        return apiResponse.body();
    }

    /**
     * Connect to the rest endpoint at apiPath using authToken and returns a
     * JsonNode object parsed from the json returned from the rest call
     * 
     * @param apiPath   the full path to a json rest endpoint
     * @param authToken the x-token value to be provided in the header to
     *                  authenticate to the json rest endpoint
     * @return JsonNode a JsonNode object representing the json parsed from the rest
     *         endpoint
     * @throws InterruptedException
     * @throws IOException
     * @throws URISyntaxException
     */
    public boolean publishFormJson(URI basePath, String authToken, String formJson)
            throws IOException, InterruptedException, URISyntaxException {

        HttpRequest apiRequest = formulateFormCreateRequest(authToken, basePath, formJson);

        HttpResponse<String> apiResponse = formIoClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());

        return true;
    }

    /**
     * Writes formatted information to a file. For each form definition page, the
     * page number and title is displayed under which is listed the API Key name for
     * each component on that page. If components are within another form type (like
     * a field set), they will be indented to show the relationship.
     * 
     * @param args Three arguments are required:
     *             <ol>
     *             <li>API Path: The full path to the Form.io form definition json
     *             path</li>
     *             <li>Auth Token: The x-token value to be provided in the header to
     *             authenticate to the Form.io form definition json rest server</li>
     *             <li>Output Filename: The full path to the file where the API Keys
     *             for the above form defintion should be written</li>
     *             </ol>
     */
    public static void main(String[] args) {
        if (args == null || args.length != 3) {
            System.out.println("Three arguments are required.\n"
                    + "API Path: The full path to the Form.io form definition json path \n"
                    + "Auth Token: The x-token value to be provided in the header to authenticate to the Form.io form definition json rest server \n"
                    + "Output Filename: The full path to the file where the API Keys for the above form definition should be written \n");
        } else {
            String apiPath = args[0];
            String authToken = args[1];
            String outputFilename = args[2];
            FormDeployerApp formDeployerApp = new FormDeployerApp();
            try {
                // get form, or get json input
                String firstFormJson = formDeployerApp.getFormJson(apiPath, authToken);

                // create form in second environment
                formDeployerApp.publishFormJson(publishPath, authToken);

                // how do we "deploy" the form officially? Do we need to, or does the create do
                // this?

                JsonNode parsedFormDefintion = formDeployerApp.connectAndParse(apiPath, authToken);
                String apiKeyOutput = formDeployerApp.getApiKeys(parsedFormDefintion);
                formDeployerApp.writeKeyOutput(apiKeyOutput, outputFilename);
                System.out.println("Successfully wrote the form definition json API keys defined within {" + apiPath
                        + "} to the {" + outputFilename + "} file.");
            } catch (IOException | InterruptedException | URISyntaxException e) {
                System.out.println("Unable to write the form definition json API keys defined within {" + apiPath
                        + "} to the {" + outputFilename + "} file.");
                e.printStackTrace();
            }
        }
    }

}
