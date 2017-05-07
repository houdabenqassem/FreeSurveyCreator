package com.fsc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Answer)
class AnswerSpec extends Specification {

    def Answer answer

    def setup() {
        answer = new Answer(questionId: 3, question: "What is the question?", answer : "This is an answer", questionType: "text")
    }

    def cleanup() {
    }

    void "Test answer basics"() {
        given:
        setup()
        when: "num questions is correct"
        answer != null
        then: "Validation returns false"
        answer.questionId == "3"
        answer.question == "What is the question?"
        answer.answer == "This is an answer"
        answer.questionType == "text"
    }
}