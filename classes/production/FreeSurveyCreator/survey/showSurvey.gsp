<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Survey</title>

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
<g:set var="svr" bean="surveyService"/>

<div class="jumbotron text-center">
    <h3>Page: <span class="label label-info">${currentPageNum+1}</span> of <span class="label label-info">${nmPages}</span></h3>
</div>

<form class="form-horizontal" role="form" style="width: 80%;" pageNumber="${currentPageNum}">
    <g:showSurveyPage page="${page}" answers="${svr.getPageAnswers()}"/>

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
    var getAnswers = function() {
        var elements = $('.uicontrol');
        var pageAnswer = {
            pageNumber : $('form').attr('pageNumber'),
            answers : []
        };
        for (var i in elements) {
            if (! isNaN(i)) {
                var uielement = elements[i];
                var type = $(uielement).attr('comptype');
                if (type) {
                    var id = $(uielement).attr('id');
                    var answer;
                    if (type == 'text') {
                        answer = $(uielement).val().trim();
                    } else if (type == 'select') {
                        answer = $('#' + id + '  option:selected').text().trim();
                    } else if (type == 'switch') {
                        answer = $('#' + id).prop('checked') ? 0 : 1;
                    }
                    var answerObj = {
                        questionId : id,
                        question : $('#' + id + 'Label').text(),
                        questionType : $(uielement).attr('comptype'),
                        answer : answer
                    };
                    pageAnswer.answers.push(answerObj)
                }
            }
        }
        return pageAnswer;
    }
    $('#nextPageId').click(function(event) {
        event.preventDefault();
        var bodyData = getAnswers();
        console.log(bodyData);
        $.ajax({ url: "/survey/incresePageNumber",
            type: 'POST',
            data: JSON.stringify(bodyData),
            contentType: "application/json; charset=utf-8",
            success: function(resp) {
                window.location.reload();
            }
        });
    });
    $('#prevPageId').click(function(event) {
        event.preventDefault();
        var bodyData = getAnswers();
        console.log(bodyData);
        $.ajax({ url: "/survey/decresePageNumber",
            type: 'POST',
            data: JSON.stringify(bodyData),
            contentType: "application/json; charset=utf-8",
            success: function(resp) {
                window.location.reload();
            }
        });
    });
</script>

</body>
</html>