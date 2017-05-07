package com.fsc

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

//import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class SurveyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", showSurvey: "GET",
                             incresePageNumber: "POST",
                             decresePageNumber: "POST",
                             submitSurvey : "POST",
                             listSurveyResults: "GET",
                             showSurveyDetails: "GET"
    ]

    def SurveyService surveyService

    def index(Integer max) {
        surveyService.loadSurveyJson()
        params.max = Math.min(max ?: 10, 100)
        respond Survey.list(params), model:[surveyCount: Survey.count()]
    }

    def show(Survey survey) {
        respond survey
    }

    def create() {
        respond new Survey(params)
    }

    @Transactional
    def save(Survey survey) {
        if (survey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (survey.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond survey.errors, view:'create'
            return
        }

        survey.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'survey.label', default: 'Survey'), survey.id])
                redirect survey
            }
            '*' { respond survey, [status: CREATED] }
        }
    }

    def edit(Survey survey) {
        respond survey
    }

    @Transactional
    def update(Survey survey) {
        if (survey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (survey.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond survey.errors, view:'edit'
            return
        }

        survey.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'survey.label', default: 'Survey'), survey.id])
                redirect survey
            }
            '*'{ respond survey, [status: OK] }
        }
    }

    @Transactional
    def delete(Survey survey) {

        if (survey == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        survey.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'survey.label', default: 'Survey'), survey.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'survey.label', default: 'Survey'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * entry point to render survey
     * @return
     */
    def showSurvey() {
        def survey = surveyService.getSurvey()
        def page = survey.pages.get("page" + surveyService.getCurrentPageNumber())

        render(view: "showSurvey", model: [page: page, nmPages: survey.getNumPages(), currentPageNum : surveyService.getCurrentPageNumber()])
    }

    /**
     * Next button handler
     * @return
     */
    def incresePageNumber() {
        def data = request.JSON
        surveyService.updateAnswers(data)

        surveyService.increasePageNumber()
        redirect(action: "showSurvey")
    }

    /**
     * Previous button handler
     * @return
     */
    def decresePageNumber() {
        def data = request.JSON
        surveyService.updateAnswers(data)

        surveyService.decreasePageNumber()
        redirect(action: "showSurvey")
    }

    def submitSurvey() {
        def data = request.JSON
        surveyService.updateAnswers(data)

        surveyService.saveSurvey()
        render(view: "submitSurvey")
    }

    //@Secured([Role.ROLE_ADMIN])
    def listSurveyResults() {
        def surveys = surveyService.getSurveys();

        render(view: "listSurveyResults", model: [surveys: surveys])
    }

    def showSurveyDetails() {
        def id = params.surveyId

        def survey = surveyService.getSurveyById(id);

        render(view: "showSurveyDetails", model: [survey: survey])
    }
}