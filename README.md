My final project will be a customizable survey, with a user interface.  When the result corresponds to a certain pre-determined criteria, it would direct to a different view, with recommendations, this project will come in the form of a Grails application with adequate testing.
1) The server side will manage one survey that is represented by a JSON file. The survey can be customized by simply changing the JSON (admin operation). Questions can be customized and a few input types can be customized (selection between: drop down, text box, checkbox)

2) A user interface will render the survey that a user can fill out. Multiple users will fill out the same survey.

3) Another user interface (for the admin) to visualize the answers of the survey taken by users.

What requirements will not be handled by this application:
- Future extension of this application is to allow the generation of custom survey through a user interface. This will require the concept of a client login. the client will create the survey than send it to users to fill out. Also a user login might be required. However this is a much more complex and will not be implemented for this project.


`Commands Used`

  - Create the application: grails create-app FreeSurveyCreator
  
  - Create domain classes: grails create-domain-class com.fsc.Survey grails create-domain-class com.fsc.Page grails create-domain-class com.fsc.Question
  
  - Create the controller: grails generate-all Survey
  
  - Create service grails: grails create-service com.fsc.Survey
  
  - Create Taglib grails create-taglib com.fsc.SurveyPage
    and the template file used in the tag lib and Build project to generate views inside Survey (classes/production).
  
  `Instructions on How to use the Survey:`
  
  - JSON schema for survey The file: resources/questions.json is the data source file used to drive they survey rendering. A survey is composed of pages. Each page is a collection of questions. Questions within a page can be grouped in sections (with a section title) The survey schema should follow these rules:
  
  1. numPages attribute with the number of pages (N pages)
  2. each page is defined as page0 ... pageN-1
  3. for each page define a number of questions by specifying the parameter: num
  4. each question is defined with a number 1 to num
  5. within each question specify: 
      a) a sectionTitle field is a section title is needed to group questions 
      b) a label to define the text for the question 
      c) a name to identify the question 
      d) a type to identify the type of ui widget to answer. The current types supported are: (text: for a text box, select: for a drop donw list, switch: for a two way exclusive choice) 
      e) a value to defines values to choose from for select and switch components. It is important to keep in mind these rules when editing the resources/questions.json file. This files drives the page building therefore an error on this file will break the parsing and the survey model building