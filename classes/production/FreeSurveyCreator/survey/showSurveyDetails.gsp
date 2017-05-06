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

<div class="container">
    <g:each var="i" in="${ (0..<survey.numPages) }" status="counter1">
        <div class="container">
            <g:set var="page" value="${survey.pages["page" + i]}"/>
            <h2>Page: ${i}</h2>
            <g:each in="${page.questions}" var="key2, question" status="counter2">

                <div class="row"> <!-- add a question to the page -->
                    <label for="name" class="col-sm-5 control-label" id="${question.name}Label">${question.label}</label>
                    <div class="col-sm-7">

                    <!-- type=text: A simple text box -->
                        <g:if test="${question.type == "text"}">
                            <label for="name" class="control-label">${page.answers[question.name].answer}</label>
                        </g:if>

                    <!-- type=select: A drop down list -->
                        <g:if test="${question.type == "select"}">
                            <label for="name" class="control-label">${page.answers[question.name].answer}</label>
                        </g:if>

                    <!-- type=switch: A switch -->
                        <g:if test="${question.type == "switch"}">
                            <g:if test="${page.answers[question.name].answer == "1"}">
                                <label for="name" class="control-label">No</label>
                            </g:if>
                            <g:if test="${page.answers[question.name].answer == "0"}">
                                <label for="name" class="control-label">Yes</label>
                            </g:if>
                        </g:if>

                    </div>
                </div>
            </g:each>
        </div>
    </g:each>
</div>

<div class="container" style="margin-top: 30px">
    <div class="row">
        <g:link controller="survey" action="listSurveyResults">
            <input name="submit" type="submit" value="Back To List" class="btn btn-primary">
        </g:link>
    </div>
</div>

<script>
</script>

</body>
</html>