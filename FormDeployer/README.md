# README.md
## FormService FormDeployer Tool

This is the main repository for the FormService FormDeployer.  The FormDeployer tool is a simple command line tool that can be used to deploy a form.io form to another stage.  The user provides a Form.io form definition file path, a comma separated list of create own submission roles, an authentication token for the destination form definition server, and the full path to the destination stage, and the tool deploys the form to the destination stage.  If the form does not exist, it will be created, otherwise, it will be updated.  The tool will primarily be used as part of the CI/CD process, but can be used in a standalone fashion if needed.

To run:
* Clone the FormDeployer
* Run the following command, replacing 'form_file_path', 'create_own_roles', 'dest_auth_key', 'dest_path' with appropriate values

C:\dev\FormDeployer>gradlew run --args="form_file_path create_own_roles dest_auth_key dest_path"

Here is an example call (auth key not provided):

C:\dev\FormDeployer>gradlew run --args="C:\formDefinitionToPublish.json Anonymous,Administrator,Authenticated xxxxxxxxxxxxxxxxx https://dev-portal.fs.gsa.gov/dev"

When the tool completes, the form should be deployed to the destination stage.  

*Development Note: In order for the tests in this tool to run successfully, create a "formiokeys.env" file in the test/resources directory, and populate the following value with an auth key: FORMIO_DEV_API_KEY=xxxxxxxxxxxxxxxxxxxxxx*