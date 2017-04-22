package com.fsc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Page)
class PageSpec extends Specification {

    def Page page

    def setup() {
        page = new Page(numQuestions: 3)

        Question q1 = new Question (
                sectionTitle: "Please fill out company information",
                label: "Company Name",
                name: "compName",
                type: "text",
                value: "",
                category: ""
        )

        Map<Integer, Question> questions = new HashMap<>()
        for (int i=1; i<= 3; i++) {
            questions.put(i, q1);
        }

        page.questions = questions
    }

    def cleanup() {
    }

    void "Test page basics"() {
        given:
        setup()
        when: "num questions is correct"
        page.questions != null && page.questions.size() > 0
        then: "Validation returns false"
        page.questions.entrySet().size() == page.numQuestions
    }
}