package com.fsc

import com.fsc.SurveyService

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SurveyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", showSurvey: "GET", incresePageNumber: "POST", decresePageNumber: "POST"]

    def SurveyService surveyService

    def index(Integer max) {
        service.loadSurveyJson()
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

    def showSurvey() {
        def survey = surveyService.getSurvey()
        def page = survey.pages.get("page" + surveyService.getCurrentPageNumber())

        println "Rendering View"
        render(view: "showSurvey", model: [page: page, nmPages: survey.getNumPages(), currentPageNum : surveyService.getCurrentPageNumber()])
    }

    def incresePageNumber() {
        surveyService.increasePageNumber()
        redirect(action: "showSurvey")
    }

    def decresePageNumber() {
        surveyService.decreasePageNumber()
        redirect(action: "showSurvey")
    }
}