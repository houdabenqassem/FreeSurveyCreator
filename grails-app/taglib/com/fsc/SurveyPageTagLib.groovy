package com.fsc

class SurveyPageTagLib {
    static defaultEncodeAs = "raw"
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showSurveyPage = {attrs ->
        def pages = attrs.page
        def pageNumber = attrs.pageNumber
        def answers = attrs.answers

        def page = pages.get("page" + pageNumber)

        out << render(template:"/survey/surveyPageTpl", model: [questions : page.questions, total : page.numQuestions, answers : answers])
    }
}