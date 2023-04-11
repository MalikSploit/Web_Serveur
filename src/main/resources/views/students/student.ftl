<#ftl encoding="utf-8">

<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">
<div class="content">

    <#if error??>
        <div class="error">Une erreur est survenue : ${error}</div>
    <#else>

    <h1>${student.firstName} ${student.lastName} - ${student.classroom}</h1>

    <hr>
    <h1>Gommettes attribuées</h1>


    <table>
        <thead>
        <tr>
            <td>Gommette</td>
            <td>Motif</td>
            <td>Date</td>
            <td>Professeur</td>
            <td>Actions</td>
        </tr>
        </thead>
        <#list student.assignedStickers as g>
            <tr>
                <td style="background: ${g.sticker.color}">${g.sticker.description}</td>
                <td>${g.reason}</td>
                <td>${g.date}</td>
                <td><a href="/profs/${g.prof.id}">${g.prof.firstName} ${g.prof.lastName}</a></td>

                <td><#if connectedUser??>
                    <a href="/students/${g.student.id}/stickers/${g.id}/delete"><i style="color: #ff0000"
                                                                                class="fa-solid fa-xmark"></i>
                        </a></#if></td>
            </tr>
        </#list>
    </table>
    <#if student.assignedStickers?size == 0>
        <p>Aucune gommette dans le système.</p>
    </#if>


    <#if connectedUser??>

        <hr>
        <h1>Ajouter une gommette</h1>
        <form action="/students/${student.id}/stickers" method="post">
            <label for="reason">Comportement</label><input type="text" id="reason" name="reason"
                                                                 placeholder="Motif"/>
            <select id="sticker" name="sticker">
                <#list stickers as sticker>
                    <option value="${sticker.id}">${sticker.description}</option>
                </#list>
            </select>
            <input type="submit" value="Ajouter une gommette">
        </form>

        <hr>

        <h1>Mettre à jour l'élève</h1>

        <form action="/students/${student.id}" method="post">
            <label for="lastname">Nom</label><input type="text" id="lastname" name="lastname" placeholder="Nom de famille"
                                               value="${student.lastName}"/>
            <label for="firstname">Prénom</label><input type="text" id="firstname" name="firstname" placeholder="Prénom"
                                                     value="${student.firstName}"/>
            <label for="classroom">Classe</label><input type="text" id="classroom" name="classroom" placeholder="Classe"
                                                     value="${student.classroom}"/>
            <input type="submit" value="Mettre à jour l'élève">
        </form>
    </#if>
    </#if>

</div>

</body>

</html>
