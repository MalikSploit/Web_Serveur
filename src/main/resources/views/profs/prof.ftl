<#ftl encoding="utf-8">
<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">


<div class="content">
    <#if error??>
        <div class="error">Une erreur est survenue : ${error}</div>

    <#else>
        <h1>${prof.firstName} ${prof.lastName}</h1>

        <hr>
        <h1>Gommettes attribuées aux élèves</h1>


        <table>
            <thead>
            <tr>
                <td>Élève</td>
                <td>Date</td>
                <td>Gommette</td>
                <td>Motif</td>
                <td>Actions</td>
            </tr>
            </thead>
            <#list prof.assignedStickers as g>
                <tr>
                    <td><a href="/students/${g.student.id}">${g.student.firstName} ${g.student.lastName}</a></td>
                    <td>${g.date}</td>
                    <td style="background: ${g.sticker.color}">${g.sticker.description}</td>
                    <td>${g.reason}</td>
                    <td><#if connectedUser??>
                        <a href="/students/${g.student.id}/stickers/${g.id}/delete"><i style="color: #ff0000"
                                                                                       class="fa-solid fa-xmark"></i>
                            </a></#if></td>
                </tr>
            </#list>
        </table>
        <#if prof.assignedStickers?size == 0>
            <p>Aucune gommette dans le système.</p>
        </#if>


        <#if connectedUser??>

            <hr>
            <h1>Éditer le professeur</h1>

            <form action="/profs/${prof.id}" method="post">
                <label for="lastname">Nom</label><input type="text" id="lastname" name="lastname" placeholder="Nom"
                                                        value="${prof.lastName}"/>
                <label for="firstname">Prénom</label><input type="text" id="firstname" name="firstname"
                                                            placeholder="Prénom"
                                                            value="${prof.firstName}"/>
                <label for="identifier">Identifiant</label><input type="text" id="identifier" name="identifier"
                                                                  placeholder="Identifiant" value="${prof.identifier}"/>
                <input type="submit" value="Mettre à jour le professeur">
            </form>
        </#if>
    </#if>

</div>

</body>

</html>
