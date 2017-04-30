package com.fsc

import grails.converters.JSON

class SurveyService {

    def filePath = "resources/questions.json"
    def Survey survey
    def currentPageNumber = 0
    def answers = []
    def totalNumberPages

    SurveyService() {
        loadSurveyJson()
    }

    def getCurrentPageNumber() {
        return currentPageNumber
    }

    def increasePageNumber() {
        currentPageNumber++
        println currentPageNumber
    }

    def decreasePageNumber() {
        currentPageNumber--
        println currentPageNumber
    }

    def getPageAnswers() {
        return answers
    }

    def isLastPage() {
        return (currentPageNumber+1) == totalNumberPages
    }

    def getSurvey() {
        return survey
    }

    /**
     * The application loads a json file to demonstrate how it would be used to build a model that would drive the UI rendering
     * of a survey organized in pages and each page has one or more sections and each section has a number of questions that can
     * be answered with pre-defined supported UI widgets.
     * The current application implementation can handle one survey that can be taken by multiple users
     *
     * If we were thinking to scale this application, it would  handle multiple surveys (maybe the json would be saved in a mongo db).
     * A user could sign-up, create a survey (maybe through a UI designer). However this (multi-survey) is beyond the scope of
     * this implementation.
     *
     * @return
     */
    def loadSurveyJson() {

        def text = this.class.classLoader.getResourceAsStream(filePath).getText()
        def json = JSON.parse(text)

        totalNumberPages = json.numPages

        survey = new Survey(numPages: json.numPages)

        for (int i=0; i<survey.numPages; i++) {
            def pageJson = json["page" + i]

            Page page = new Page(numQuestions: pageJson.num)

            for (int j=1; j<=pageJson.num; j++) {
                def questionsJson = pageJson["" + j]

                Question question = new Question(
                        sectionTitle: questionsJson.sectionTitle,
                        label: questionsJson.label,
                        name: questionsJson.name,
                        type: questionsJson.type,
                        value: questionsJson.value,
                        category: questionsJson.category
                )

                page.questions.put(j, question)
            }

            survey.pages.put("page" + i, page)

        }
    }
}