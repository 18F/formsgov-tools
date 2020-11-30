# README.md
## FormService Tools

This is the main repository for the FormService tools.  There is a companion repository for the FormService / Forms as a Service (FaaS) microservice.

## Repository Structure

The FormService Tools GitHub Repository contains various tools used to help during development and testing of the FormService.


## Tools

In this repository, there are many simple tools that aid in the development and testing of FormService functionality.  Following is a brief description of each tool along with execution instructions.

### Form API Keys

The FormApiKeys tool is a simple command line tool that allows the user to input a Form.io form definition path, an authentication token for the form definition server, and a local output file path and outputs the API Key names for the fields on each page of the form.

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
