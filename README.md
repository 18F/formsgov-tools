# README.md
## FormService Tools

This is the main repository for the FormService tools.  There is a companion repository for the FormService / Forms as a Service (FaaS) microservice.

## Repository Structure

The FormService Tools GitHub Repository contains various tools used to help during development and testing of the FormService.


## Tools

In this repository, there are many simple tools that aid in the development and testing of FormService functionality.  Following is a brief description of each tool along with execution instructions.

### ApiKeys

The ApiKeys tool is a simple webpage that allows the user to input a Form.io form definition path and displays the API Key names for the fields on each page of the form.

To run:
* Pull down the apiKeys directory
* Open the "ApiKeys.html" page in a browser
* Input the form definition path

That's it!

### cmdApiKeys

The cmdApiKeys tool is a simple command line tool that allows the user to input a Form.io form definition path, and a local output file path and outputs the API Key names for the fields on each page of the form.

To run:
* Pull down the cmdApiKeys directory
* Run the following command, replacing 'form_path' and 'output_path' with appropriate names

C:\dev\cmdApiKeys>gradlew run --args="form_path output_path"

Here is an example call:

C:\dev\cmdApiKeys>gradlew run --args="https://dev-portal.fs.gsa.gov/dev/mtwform apiKeys.txt"

When the tool completes, the apiKeys.txt file should be in the current directory.  The file will contain the names of the API components by page. 
