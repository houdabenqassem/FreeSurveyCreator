package freesurveycreator

import com.fsc.Page
import com.fsc.Question
import com.fsc.Role
import com.fsc.Survey
import com.fsc.User
import com.fsc.UserRole
import grails.converters.JSON

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        environments {
            development {
                println "Development execution"
                setupRoles()
            }
            test {
                println "Test execution"
            }
            production {
                println "Production execution"
                setupRoles()
            }
        }
    }

    def destroy = {
    }

    def setupRoles() {
        println "Setting up user roles"

        User admin = new User(username: 'admin', password: 'password')
        admin.save(flush: true, failOnError: true)

        User user = new User(username: 'user', password: 'user')
        user.save(flsuh:true, failOnError: true)

        Role adminRole = new Role(authority: Role.ROLE_ADMIN)
        adminRole.save(flush:true, failOnError: true)

        Role userRole = new Role(authority: Role.ROLE_USER)
        userRole.save(flush:true, failOnError: true)

        UserRole.create(admin, adminRole)
        UserRole.create(admin, userRole)
        UserRole.create(user, userRole)
    }
}