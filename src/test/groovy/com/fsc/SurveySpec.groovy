package com.fsc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Survey)
class SurveySpec extends Specification {

    def Survey survey

    def setup() {
        survey = new Survey(numPages: 3)

        Question question = new Question (
                sectionTitle: "Please fill out company information",
                label: "Company Name",
                name: "compName",
                type: "text",
                value: "",
                category: ""
        )

        Map<String, Page> pages = new HashMap<>()

        for (int j=0; j<3; j++) {

            def page = new Page(numQuestions: 3)

            Map<Integer, Question> questions = new HashMap<>()
            for (int i=1; i<= 3; i++) {
                questions.put(i, question);
            }

            page.questions = questions

            pages.put("page" + j, page)
        }

        survey.pages = pages

    }

    def cleanup() {
    }

    void "Test page basics"() {
        given:
        setup()
        when: "num questions is correct"
        survey.numPages != null && survey.pages.size() > 0
        then: "Validation returns false"
        survey.pages.entrySet().size() == survey.numPages
    }
}