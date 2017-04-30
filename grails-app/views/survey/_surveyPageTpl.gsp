<div>
    <g:each var="question" in="${questions.take(total)}" status="counter">

        <g:if test="${question.value.sectionTitle}">
            <div class="row">
                <h3 class="col-sm-12 col-sm-offset-5"><span class="label label-default">${question.value.sectionTitle}</span></h3>
            </div>
        </g:if>

        <div class="form-group"> <!-- add a question to the page -->
            <label for="name" class="col-sm-5 control-label">${question.value.label}</label>
            <div class="col-sm-7">

            <!-- type=text: A simple text box -->
                <g:if test="${question.value.type == "text"}">
                    <input type="${question.value.type}" class="form-control"
                           id="${question.value.name}" name="${question.value.name}" placeholder="${question.value.label}" value="${answers[counter]}">
                </g:if>

            <!-- type=select: A drop down list -->
                <g:if test="${question.value.type == "select"}">
                    <select class="form-control"
                            id="${question.value.name}" placeholder="${question.value.label}" value="${answers[counter]}">
                        <g:each var="optionValue" in="${question.value.value}" status="i">
                            <option value="${optionValue}">${optionValue}</option>
                        </g:each>
                    </select>
                </g:if>
            </div>
        </div>

    </g:each>
</div>