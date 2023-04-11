<#ftl encoding="utf-8">
<#include "/global/header.ftl">

<body xmlns="http://www.w3.org/1999/html">

<div class="content">
    <#if error??>
        <div class="error">Une erreur est survenue : ${error}</div>

    <#else>

    <h1>Liste des gommettes</h1>

    <table>
        <thead>
        <tr>
            <td>Identifiant</td>
            <td>Couleur</td>
            <td>Description</td>
            <td>Action</td>
        </tr>
        </thead>
        <#list stickers as sticker>
            <tr>
                <td>${sticker.id}</td>
                <td>
                    <div style="width: 100%; height: 18px; border: 1px solid #000000; background: ${sticker.color}"></div>
                </td>
                <td>${sticker.description}</td>
                <td><#if connectedUser??>
                    <a href="/stickers/${sticker.id}/delete"><i style="color: #ff0000" class="fa-solid fa-xmark"></i>
                        </a></#if></td>
            </tr>
        </#list>
    </table>

    <#if stickers?size == 0>
        <p>Aucune gommette dans le système.</p>
    </#if>

    <#if connectedUser??>

        <hr>
        <h1>Ajouter une gommette</h1>

        <form action="/stickers" method="post">
            <label for="description">Description</label><input type="text" id="description" name="description"
                                                               placeholder="Description de la gommette"/>
            <label for="color">Couleur</label><input type="color" id="color" name="color" placeholder="Couleur"/>
            <input type="submit" value="Créer la gommette">
        </form>
    </#if>
    </#if>
</div>
</body>

</html>
