package freesurveycreator

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // "/"(view:"/index")

        "/" (controller: "survey", action: "showSurvey", params: [])
        "/admin" (controller: "survey", action: "listSurveyResults", params: [])

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}