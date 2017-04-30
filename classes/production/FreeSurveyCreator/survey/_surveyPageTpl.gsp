<div>
    <g:each var="mapQuestions" in="${data.take(entries)}" status="counter">
        <tr>
            <!--td class="unstyled">${counter+1}. </td-->

            <td class="unstyled">
                <g:link controller="gameStats"
                        action="showStats"
                        params="[playerName: mapElement.key]"
                        class="class">${mapElement.key}
                </g:link>
            </td>

            <td class="unstyled">${mapElement.value}</td>
        </tr>
    </g:each>
</div>