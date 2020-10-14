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
* Run the following command, replacing 'form_path', 'auth_key', and 'output_path' with appropriate values

C:\dev\FormApiKeys>gradlew run --args="form_path auth_key output_path"

Here is an example call (auth key not provided):

C:\dev\FormApiKeys>gradlew run --args="https://dev-portal.fs.gsa.gov/dev/mtwform xxxxxxxxxxxxxxxxx apiKeys.txt"

When the tool completes, the apiKeys.txt file should be in the current directory.  The file will contain the names of the API components (and their component types) by page.

*Development Note: In order for the tests in this tool to run successfully, create a "formiokeys.env" file in the root directory, and populate the following value with an auth key: FORMIO_DEV_API_KEY=xxxxxxxxxxxxxxxxxxxxxx*
