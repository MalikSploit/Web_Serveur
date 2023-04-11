<#ftl encoding="utf-8">
<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">


<div class="content">
    <#if error??>
        <div class="error">Une erreur est survenue : ${error}</div>
    <#else>

        <h1>Liste des élèves</h1>

        <table>
            <thead>
            <tr>
                <td>Identifiant</td>
                <td>Élève</td>
                <td>Classe</td>
                <td>Action</td>
            </tr>
            </thead>
            <#list students as student>
                <tr>
                    <td><a href="/students/${student.id}">${student.id}</a></td>
                    <td><a href="/students/${student.id}">${student.firstName} ${student.lastName}</a></td>
                    <td>${student.classroom}</td>
                    <td><#if connectedUser??>
                        <a href="/students/${student.id}/delete"><i style="color: #ff0000"
                                                                    class="fa-solid fa-xmark"></i>
                            </a></#if></td>
                </tr>
            </#list>
        </table>

        <#if students?size == 0>
            <p>Aucun élève dans le système.</p>
        </#if>

        <#if connectedUser??>

            <hr>
            <h1>Ajouter un élève</h1>

            <form action="/students" method="post">
                <label for="lastname">Nom</label><input type="text" id="lastname" name="lastname"
                                                        placeholder="Nom de famille"/>
                <label for="firstname">Prénom</label><input type="text" id="firstname" name="firstname"
                                                            placeholder="Prénom"/>
                <label for="classroom">Classe</label><input type="text" id="classroom" name="classroom"
                                                            placeholder="Classe"/>
                <input type="submit" value="Créer l'élève">
            </form>
        </#if>
    </#if>
</div>
</body>

</html>
