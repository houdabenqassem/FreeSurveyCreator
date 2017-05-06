package com.fsc

class Page {

    int numQuestions
    Map<Integer, Question> questions = new HashMap<Integer, Question>()
    Map<String, Answer> answers = new HashMap<String, Answer>()

    static mapWith = "mongo"

    static constraints = {
    }
}