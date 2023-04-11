<#ftl encoding="utf-8">
<html>

<header>
    <title>Gommettarium</title>
    <link href="/style.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b56819fe89.js" crossorigin="anonymous"></script>
</header>

<head>
    <div class="topbar">
        <ul style="padding-right: 40px;">
            <li><a href="/">Accueil</a></li>
            <li>|</li>
            <li><a href="/students">Liste des élèves</a></li>
            <li>|</li>
            <li><a href="/profs">Liste des professeurs</a></li>
            <li>|</li>
            <li><a href="/stickers">Liste des gommettes</a></li>
        </ul>

        <#if !connectedUser??>
            <form action="/login" method="post" style="display: inline">
                <label for="identifier">Identifiant</label><input type="text" name="identifier" id="identifier"
                                                                   placeholder="Identifiant"/>
                <label for="password">Mot de passe</label><input type="password" name="password"
                                                                     id="password" placeholder="Mot de passe">
                <input type="submit" value="Se connecter">
            </form>
        <#else>
            <ul>
                <li>Bonjour, ${connectedUser.identifier}</li>
                <li>|</li>
                <li><a href="/profile">Mon profil</a></li>
                <li>|</li>
                <li><a href="/logout">Déconnexion</a></li>
            </ul>
        </#if>
    </div>

</head>
