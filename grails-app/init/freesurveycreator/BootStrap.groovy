package freesurveycreator

import com.fsc.Page
import com.fsc.Question
import com.fsc.Survey
import grails.converters.JSON

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        environments {
            development {
                println "Development execution"
            }
            test {
                println "Test execution"
            }
            production {
                println "Production execution"
            }
        }
    }

    def destroy = {
    }
}