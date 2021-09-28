# README.md
## FormService Tools

This is the main repository for the FormService tools.  There is a companion repository for the FormService / Forms as a Service (FaaS) microservice.

## Repository Structure

The FormService Tools GitHub Repository contains various tools used to help during development and testing of the FormService.


## Tools

In this repository, there are many simple tools that aid in the development and testing of FormService functionality.  Following is a brief description of each tool along with execution instructions.

### Form API Keys

The FormApiKeys tool is a simple command line tool that allows the user to input a Form.io form definition path, an authentication token for the form definition server, a local output file path, and a flag indicating if the content fields should be printed and outputs the API Key names for the fields on each page of the form.

To run:
* Pull down the FormApiKeys directory
* Run the following command, replacing 'form_path', 'auth_key', 'output_path', and 'print_content_fields" with appropriate values

C:\dev\FormApiKeys>gradlew run --args="form_path auth_key output_path print_content_fields"

Here is an example call (auth key not provided):

C:\dev\FormApiKeys>gradlew run --args="https://dev-portal.fs.gsa.gov/dev/mtwform xxxxxxxxxxxxxxxxx apiKeys.txt true"

When the tool completes, the apiKeys.txt file should be in the current directory.  The file will contain the names of the API components (and their component types) by page.

*Development Note: In order for the tests in this tool to run successfully, create a "formiokeys.env" file in the test/resources directory, and populate the following value with an auth key: FORMIO_DEV_API_KEY=xxxxxxxxxxxxxxxxxxxxxx*

### EmbeddedForm

The EmbeddedForm tool is a docker container that runs a simple webserver that hosts an embedded USWDS form.io example.  Right now, the form URL is hardcoded within the html, to simulate the embedded functionality.

To run:
* Pull down the EmbeddedForm directory
* To run locally, run npm install, and then npm start.  A webserver will start on your local machine at http://localhost:8080/  Navigate to this URL and enter data into the form.  When the form is submitted, a submission page should appear.
* To create the docker container, at the command line, run "docker build -t {{username}}/{{dockername}} ." replacing {{username}} and {{dockername}} with your own values.  Then, you can execute the docker container by running "docker run -p 8888:8080 {{username}}/{{dockername}}"  Navigate to http://localhost:8888/ to enter data into the form.

### FormDeployer Tool

This is the main repository for the FormService FormDeployer.  The FormDeployer tool is a simple command line tool that can be used to deploy a form.io form to another stage.  The user provides a Form.io form definition file path, a comma separated list of create own submission roles, an authentication token for the destination form definition server, and the full path to the destination stage, and the tool deploys the form to the destination stage.  If the form does not exist, it will be created, otherwise, it will be updated.  The tool will primarily be used as part of the CI/CD process, but can be used in a standalone fashion if needed.

To run:
* Clone the FormDeployer
* Run the following command, replacing 'form_file_path', 'create_own_roles', 'dest_auth_key', 'dest_path' with appropriate values

C:\dev\FormDeployer>gradlew run --args="form_file_path create_own_roles dest_auth_key dest_path"

Here is an example call (auth key not provided):

C:\dev\FormDeployer>gradlew run --args="C:\formDefinitionToPublish.json Anonymous,Administrator,Authenticated xxxxxxxxxxxxxxxxx https://dev-portal.fs.gsa.gov/dev"

When the tool completes, the form should be deployed to the destination stage.  

*Development Note: In order for the tests in this tool to run successfully, create a "formiokeys.env" file in the test/resources directory, and populate the following value with an auth key: FORMIO_DEV_API_KEY=xxxxxxxxxxxxxxxxxxxxxx*

Steps the FormDeployer runs (or needs to run) to deploy a form to a new stage:
1. Read in a local Form definition json file (ENHANCEMENT depending on use case: Pull down a form definition json file from URL)
1. Parse the form definition json file to determine if the form is a PDF form  (ENHANCEMENT)
1. If the form is a PDF form: (ENHANCEMENT)
    1. Read in the source environment path
	1. Read in the source environment auth token
	1. Construct the URL to download the PDF, including the auth token in the header (Note - there was a bug here in the past with the auth token, and we might have to use a jwt token)
	1. Execute the command to download the PDF.
	1. Read in the target environment Path
	1. Read in the target environment Auth Token
	1. Construct the call to publish the PDF to the new environment, including the auth token in the header
	1. Update the form definition JSON file with the pdf path created in the previous step.
1. Read in the target environment Path
1. Read in the target environment Auth Token
1. Construct the call to publish the json to the new environment, including the auth token in the header
1. Try to put (update) the form
1. Check for 200 status to ensure update was successful.
1. If put fails, post (create) the form
	1. Modify the form definition json to include access control roles.  Currently gives "Create Own" privs to the roles passed in to the command line.
	1. Check for 201 status to ensure create was successful.


Notes:  There are other parts of the form definition that we will have to make sure work within this code: Actions - including webhooks and e-mail, form settings (like the sam_api_key), and more detailed look into resources and access rules.
Submission Server:  There is one weirdness with the submission server.  While the form definition can be promoted in the Central Hub, any actions must be added specifically to the submission server.  Not sure how we do this automatically, but we will want to consider adding this to this tool.  IT WILL GET EASILY FORGOTTEN!
1. 
1. 