<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Survey</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'bootstrap.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'assets/stylesheets', file: 'survey.css')}" type="text/css">

    <asset:javascript src="assets/javascripts/jquery-2.2.0.min.js"/>
    <asset:javascript src="assets/javascripts/bootstrap.js"/>
    <asset:javascript src="application.js"/>
</head>
<body>
<g:set var="svr" bean="surveyService"/>

<div class="jumbotron text-center">
    <h3>Page: <span class="label label-info">${svr.getCurrentPageNumber()+1}</span> of <span class="label label-info">${surveyPages.numPages}</span></h3>
</div>

<form class="form-horizontal" role="form" style="width: 80%;">
    <g:showSurveyPage page="${surveyPages.pages}" pageNumber="${svr.getCurrentPageNumber()}" answers="${svr.getPageAnswers()}"/>

    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-5">

            <g:if test="${svr.getCurrentPageNumber() > 0}">
                <input id="prevPageId" name="submit" type="submit" value="Prev" class="btn btn-primary" >
            </g:if>

            <g:if test="${! svr.isLastPage()}">
                <input id="nextPageId" name="submit" type="submit" value="Next" class="btn btn-primary" >
            </g:if>

            <g:if test="${svr.isLastPage()}">
                <input id="submitSurvey" name="submit" type="submit" value="Submit" class="btn btn-primary">
            </g:if>

        </div>
    </div>
</form>

<script>
    $('#nextPageId').click(function(event) {
        event.preventDefault();
        $.ajax({ url: "/survey/incresePageNumber", type: 'POST' });
    });
    $('#prevPageId').click(function(event) {
        event.preventDefault();
        $.ajax({ url: "/survey/decresePageNumber", type: 'POST' });
    });
</script>

</body>
</html>