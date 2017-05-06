package com.fsc

class Question {

    String sectionTitle /* Define the section title */
    String label        /* Define the label with the question */
    String name         /* field identifier */
    String type         /* type of widget: text, select, yes/no switch */
    List<String> value  /* values to choose from */
    String category     /* */

    static mapWith = "mongo"

    static constraints = {
    }
}
