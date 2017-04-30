package com.fsc

class SurveyPageTagLib {
    static defaultEncodeAs = "raw"
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def showSurveyPage = {attrs ->
        def page = attrs.page
        def answers = attrs.answers

        out << render(template:"/survey/surveyPageTpl", model: [questions : page.questions, total : page.numQuestions, answers : answers])
    }
}