<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Surveys Results</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'bootstrap.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'bootstrap-toggle.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'survey.css')}" type="text/css">

    <asset:javascript src="assets/javascripts/jquery-2.2.0.min.js"/>
    <asset:javascript src="assets/javascripts/bootstrap.js"/>
    <asset:javascript src="assets/javascripts/bootstrap-toggle.min.js"/>
    <asset:javascript src="application.js"/>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <th></th>
        <th>Company</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <g:each var="survey" in="${surveys}" status="index">
        <tr>
            <td>
                <g:link controller="survey"
                        action="showSurveyDetails"
                        params="[surveyId: survey.getId()]"
                        class="class">${index}
                </g:link>
            </td>
            <td>${survey.pages.get("page" + (survey.numPages-1)).answers.get('compName').answer}</td>
            <td>${survey.pages.get("page" + (survey.numPages-1)).answers.get('firstName').answer}</td>
            <td>${survey.pages.get("page" + (survey.numPages-1)).answers.get('lastName').answer}</td>
            <td>${survey.pages.get("page" + (survey.numPages-1)).answers.get('phoneNum').answer}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<script>
</script>

</body>
</html>