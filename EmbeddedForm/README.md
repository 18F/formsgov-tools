# README.md
## EmbeddedForm

The purpose of this project is to provide testable, modifiable examples of how a form.io form could be embedded in to a webpage for developers.  Form.io ha snippets of how to implement these examples under the "</> Embed" tab within the form.io developer portal.  The purpose of this project is to provide a place to try out a full implementation.

Form.io has documentation on ALL of the additional embedding parameters and options at https://help.form.io/developers/rendering .  The Quick Inline Embed Configurator link provided in the documentation might be of use as well.

It is really useful to test forms out using the technology under which they will be rendered officially, to verify things like:
* page spacing
* returning to the top of the page on subsequent wizard pages
* display of error messages
* color / size / font rendering
* etc.

---

### How to use this project

It is recommended that developers consider which embedding option is better for them 
(inline being preferred/recommended by form.io), and then copy the example into a project folder.

The form URL is hardcoded within the example HTML, so for testing, the developer will 
have to modify the URL to indicate the environment, stage, and form name that they are testing.

Please note that because this project is used for developer testing, the stylesheet and 
javascript versions may not be up to date.  

Developers can reference formio versions with @latest, or with the version number. Version 
number is recommended to ensure changes to libararies do not catch the team unaware.

---

### Developer things to know about form.io

If using premium components (like datasource), the form.io premium css and js files need to be used.  A version of these files can be found within the assets / premium folder.  To obtain the latest versions, follow these instructions from the form.io vendor:

> The premium components require the steps here to be followed: 
>
> https://pkg.form.io/-/web/detail/@formio/instructions
>
> We add portal.form.io usernames to the PKG system.

---

### Build and run the webserver

Run the following commands to run the webserver without the docker container:

`npm install`

`npm start`

A webserver will start on your local machine at http://localhost:8080/  

The landing page will appear, describing the different types of examples included in this project. Choose the example you wish to test.

---
### Note if you do not have access to formio/premium (you will know this if you get 401 when you run npm install)
Check the following link: https://help.form.io/userguide/forms/premium-components

### Build and run the docker container

Run the following commands to build the docker container:

`docker build -t {{username}}/{{dockername}} .` replacing {{username}} and {{dockername}} with your own values.

Execute the docker container by running `docker run -p 8888:8080 {{username}}/{{dockername}}`  

The landing page will appear, describing the different types of examples included in this project. Choose the example you wish to test.