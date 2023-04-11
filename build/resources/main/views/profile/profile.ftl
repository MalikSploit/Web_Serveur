<#ftl encoding="utf-8">
<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">

<div class="content">
<#if !connectedUser??>
        <h1>Vous n'êtes pas connecté. Accès interdit.</h1>
    <#else>
        <h1>Bonjour, ${connectedUser.firstName} ${connectedUser.lastName} - <a href="/profs/${connectedUser.id}"><i class="fa-solid fa-up-right-from-square"></i></a></h1>
        <hr>
        <h3>Changer de mot de passe</h3>
        <form action="/password" method="post">
            <input type="password" name="password" id="password" placeholder="Nouveau mot de passe"/>
            <input type="submit" value="Modifier mon mot de passe"/>
        </form>
    </#if>
</div>