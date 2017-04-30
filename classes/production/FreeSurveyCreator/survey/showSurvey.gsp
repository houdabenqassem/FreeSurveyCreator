<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Survey</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'bootstrap.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'standing.css')}" type="text/css">

    <asset:javascript src="assets/javascripts/jquery-2.2.0.min.js"/>
    <asset:javascript src="assets/javascripts/bootstrap.js"/>
    <asset:javascript src="application.js"/>
</head>
<body>
<g:set var="currentSurveyPage" value="0" scope="session" />

<h1>Please take the survey</h1>

<div class="row tab-content">
    <g:showSurveyPage page="${surveyPages.pages}" pageNumber="${currentSurveyPage}"/>
</div>

</body>
</html>