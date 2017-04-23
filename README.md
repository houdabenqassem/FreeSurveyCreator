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
  
  - Create service grails: create-service Survey