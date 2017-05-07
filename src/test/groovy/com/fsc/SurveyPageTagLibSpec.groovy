package com.fsc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(SurveyPageTagLib)
class SurveyPageTagLibSpec extends Specification {

    Map render
    Page page = new Page()
    def answers = []

    def setup() {
        tagLib.metaClass.render = { Map attrs ->
            render = attrs
        }

        page.questions = [:].withDefault{key -> return 0}
    }

    def cleanup() {
    }

    void "Test showSurveyPage"() {
        when:
        tagLib.showSurveyPage(page: page, answers : answers)
        then:
        render.template == '/survey/surveyPageTpl'
        render.model == [questions : [:], total : 0, answers : []]
    }
}