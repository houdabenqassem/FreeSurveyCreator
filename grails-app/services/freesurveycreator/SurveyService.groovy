package freesurveycreator

import com.fsc.Page
import com.fsc.Question
import com.fsc.Survey
import grails.converters.JSON
import org.grails.core.io.ResourceLocator

class SurveyService {

    def filePath = "resources/questions.json"
    def Survey survey

    SurveyService() {
        loadSurveyJson()
    }

    def getSurvey() {
        return survey
    }

    /**
     * The application loads a json file to demonstrate how it would be used to build a model that would drive the UI rendering
     * of a survey organized in pages and each page has one or more sections and each section has a number of questions that can
     * be answered with pre-defined supported UI widgets.
     * The current application implementation can handle one survey that can be taken by multiple users
     *
     * If we were thinking to scale this application, it would  handle multiple surveys (maybe the json would be saved in a mongo db).
     * A user could sign-up, create a survey (maybe through a UI designer). However this (multi-survey) is beyond the scope of
     * this implementation.
     *
     * @return
     */
    def loadSurveyJson() {

        def text = this.class.classLoader.getResourceAsStream(filePath).getText()
        def json = JSON.parse(text)

        survey = new Survey(numPages: json.numPages)

        for (int i=0; i<survey.numPages; i++) {
            def pageJson = json["page" + i]

            Page page = new Page(numQuestions: pageJson.num)

            for (int j=1; j<=pageJson.num; j++) {
                def questionsJson = pageJson["" + j]
                Question question = new Question(
                        sectionTitle: questionsJson.sectionTitle,
                        label: questionsJson.label,
                        name: questionsJson.name,
                        type: questionsJson.type,
                        value: questionsJson.value,
                        category: questionsJson.category
                )

                page.questions.put(j, question)
            }

            survey.pages.put("page" + i, page)

        }
    }
}