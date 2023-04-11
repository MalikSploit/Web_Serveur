<#ftl encoding="utf-8">
<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">


<div class="content">
    <#if error??>
        <div class="error">Une erreur est survenue : ${error}</div>

    <#else>

        <h1>Liste des professeurs</h1>

        <table>
            <thead>
            <tr>
                <td>Identifiant</td>
                <td>Prénom</td>
                <td>Nom</td>
                <td>Identifiant de connexion</td>
                <td>Action</td>
            </tr>
            </thead>
            <#list profs as prof>
                <tr>
                    <td><a href="/profs/${prof.id}">${prof.id}</a></td>
                    <td>${prof.firstName}</td>
                    <td>${prof.lastName}</td>
                    <td><a href="/profs/${prof.id}">${prof.identifier}</a></td>
                    <td><#if connectedUser??>
                        <a href="/profs/${prof.id}/delete"><i style="color: #ff0000" class="fa-solid fa-xmark"></i>
                            </a></#if></td>
                </tr>
            </#list>
        </table>

        <#if profs?size == 0>
            <p>Aucune professeur dans le système.</p>
        </#if>

        <#if connectedUser??>

            <hr>
            <h1>Ajouter un professeur</h1>

            <form action="/profs" method="post">
                <label for="lastname">Nom</label><input type="text" id="lastname" name="lastname" placeholder="Nom"/>
                <label for="firstname">Prénom</label><input type="text" id="firstname" name="firstname"
                                                            placeholder="Prénom"/>
                <label for="identifier">Identifiant</label><input type="text" id="identifier" name="identifier"
                                                                  placeholder="Identifiant"/>
                <label for="password">Mot de passe</label><input type="password" id="password" name="password"
                                                                 placeholder="Mot de passe"/>
                <input type="submit" value="Créer le professeur">
            </form>
        </#if>
    </#if>
</div>
</body>

</html>
