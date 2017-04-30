package com.fsc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Question)
class QuestionSpec extends Specification {

    def question

    def setup() {
        question = new Question (
                sectionTitle: "Please fill out company information",
                label: "Company Name",
                name: "compName",
                type: "text",
                value: "",
                category: ""
        )
    }

    def cleanup() {
    }

    void "Test question basics"() {
        given:
        setup()
        when: "question field is type text"
        question.value = Arrays.asList("default value")
        question.category = "category1"
        then: "Validation returns false"
        question.value == Arrays.asList("default value")
        question.category == "category1"
        question.type == "text"
    }
}