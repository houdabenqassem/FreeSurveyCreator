package com.fsc

class Survey {
    int id
    int numPages
    Map<String, Page> pages = new HashMap<String, Page>();

    static mapWith = "mongo"


    static constraints = {
    }
}