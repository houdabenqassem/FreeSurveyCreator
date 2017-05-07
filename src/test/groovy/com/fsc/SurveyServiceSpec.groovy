package com.fsc

import grails.test.mixin.TestFor
import groovy.json.JsonBuilder
import org.json.JSONArray
import org.json.JSONObject
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SurveyService)
class SurveyServiceSpec extends Specification {

    def service

    def setup() {
        service = new SurveyService()
    }

    def cleanup() {
    }

    void "Test Loading"() {
        given:
        setup()
        when: "load json"
        service.loadSurveyJson()
        then: "Survey instance is populated"
        service.survey != null
        service.survey.numPages == 3
    }

    /*void "Test Answer"() {
        given:
            setup()
        when: "load json"
            service.loadSurveyJson()
            def answer = new JSONObject()
            answer.put("pageNumber", "0")
            JSONArray array = new JSONArray()
            def answ = new JSONObject()
            answ.put("questionId", "id01")
            answ.put("question", "abc")
            answ.put("answer", "dec")
            answ.put("questionType", "text")
            array.put(answ)
            answer.put("answers", array)
            service.updateAnswers(answer)
        then: "Survey instance is populated"
            service.survey != null
            service.survey.numPages == 3
    }*/
}