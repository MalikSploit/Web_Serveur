package com.uca;

import com.uca.core.Studentcore;
import com.uca.core.AssignedStickerCore;
import com.uca.core.StickerCore;
import com.uca.core.ProfCore;
import com.uca.dao._Initializer;
import com.uca.entity.StudentEntity;
import com.uca.entity.ProfEntity;
import com.uca.gui.*;
import com.uca.security.doLogin;
import spark.Request;

import static spark.Spark.*;

public class StartServer {

    public static void main(String[] args) {
        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            return LandingGUI.getLandingPage(connectedUser);
        });

        get("/students", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            return StudentGUI.getAllStudents(connectedUser);
        });

        get("/students/:id", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);

            int eleveId = parseIdFromRequest(req, "id");
            if (eleveId == -1) {
                res.status(400);
                return null;
            }

            return StudentGUI.getStudent(connectedUser, eleveId);
        });

        get("/stickers", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);

            return StickerGUI.getAllStickers(connectedUser);
        });

        get("/profs", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);

            return ProfGUI.getAllProfs(connectedUser);
        });

        get("/profs/:id", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);

            int profId = parseIdFromRequest(req, "id");
            if (profId == -1) {
                res.status(400);
                return null;
            }

            return ProfGUI.getProf(connectedUser, profId);
        });

        get("/profile", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            connectedUser = ProfCore.getByIdentifier(connectedUser.getIdentifier(), false);

            return ProfileGUI.getProfile(connectedUser);
        });

        get("/students/:id/delete", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }


            int studentId = parseIdFromRequest(req, "id");
            if (studentId == -1) {
                res.status(400);
                return null;
            }


            Studentcore.delete(studentId);
            res.redirect("/students");
            return null;
        });


        get("/stickers/:id/delete", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int stickerId = parseIdFromRequest(req, "id");
            if (stickerId == -1) {
                res.status(400);
                return null;
            }

            StickerCore.delete(stickerId);
            res.redirect("/stickers");
            return null;
        });


        get("/profs/:id/delete", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int profId = parseIdFromRequest(req, "id");
            if (profId == -1) {
                res.status(400);
                return null;
            }

            ProfCore.delete(profId);
            res.redirect("/profs");
            return null;
        });


        get("/students/:id/stickers/:stickerid/delete", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int studentId = parseIdFromRequest(req, "id");
            if (studentId == -1) {
                res.status(400);
                return null;
            }

            int stickerId = parseIdFromRequest(req, "stickerid");
            if (stickerId == -1) {
                res.status(400);
                return null;
            }

            AssignedStickerCore.delete(stickerId);
            res.redirect("/students/" + studentId);
            return null;
        });


        post("/students", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"lastname", "firstname", "classroom"})) {
                res.redirect("/students", 301);
                return null;
            }

            StudentEntity eleve = Studentcore.create(req.queryParams("lastname"), req.queryParams("firstname"), req.queryParams("classroom"));

            res.redirect("/students/" + eleve.getId(), 301);
            return null;
        });

        post("/stickers", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"color", "description"})) {
                res.redirect("/stickers", 301);
                return null;
            }

            StickerCore.create(req.queryParams("color"), req.queryParams("description"));

            res.redirect("/stickers", 301);
            return null;
        });

        post("/password", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"password"})) {
                res.redirect("/profile", 301);
                return null;
            }

            ProfCore.updatePassword(connectedUser.getId(), req.queryParams("password"));

            res.redirect("/profile", 301);
            return null;
        });

        post("/profs", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"password", "identifier", "firstname", "lastname"})) {
                res.redirect("/profs", 301);
                return null;
            }

            ProfEntity entity = ProfCore.create(req.queryParams("lastname"), req.queryParams("firstname"), req.queryParams("identifier"), req.queryParams("password"));

            res.redirect("/profs/" + entity.getId(), 301);
            return null;
        });

        post("/students/:id/stickers", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int studentId = parseIdFromRequest(req, "id");
            if (studentId == -1) {
                res.status(400);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"sticker", "reason"})) {
                res.redirect("/students/"+studentId, 301);
                return null;
            }

            int gommetteId;
            try {
                gommetteId = Integer.parseInt(req.queryParams("sticker"));
            } catch (Exception e) {
                res.status(400);
                return null;
            }

            AssignedStickerCore.create(gommetteId, studentId, connectedUser.getId(), req.queryParams("reason"));
            res.redirect("/students/" + req.params(":id"), 301);
            return null;

        });

        post("/students/:id", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int studentId = parseIdFromRequest(req, "id");
            if (studentId == -1) {
                res.status(400);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"lastname", "firstname", "classroom"})) {
                res.redirect("/students", 301);
                return null;
            }

            Studentcore.update(studentId, req.queryParams("lastname"), req.queryParams("firstname"), req.queryParams("classroom"));
            res.redirect("/students/" + req.params(":id"), 301);
            return null;

        });


        post("/profs/:id", (req, res) -> {
            ProfEntity connectedUser = getAuthenticatedUser(req);
            if (connectedUser == null) {
                res.redirect("/", 301);
                return null;
            }

            int profId = parseIdFromRequest(req, "id");
            if (profId == -1) {
                res.status(400);
                return null;
            }

            if (isAnyFieldEmpty(req, new String[]{"lastname", "firstname", "identifier"})) {
                res.redirect("/profs" + profId, 301);
                return null;
            }

            ProfCore.update(profId, req.queryParams("lastname"), req.queryParams("firstname"), req.queryParams("identifier"));
            res.redirect("/profs/" + profId, 301);
            return null;

        });


        post("/login", (req, res) -> {
            if (req.queryParams("identifier") != null) {
                String username = req.queryParams("identifier");
                String password = req.queryParams("password");

                ProfEntity userEntity = new ProfEntity();
                userEntity.setIdentifier(username);
                userEntity.setPassword(password);

                try {
                    res.cookie("/", "auth", doLogin.login(userEntity), 36000, false, true);
                } catch (Exception e) {
                    res.redirect("/", 301);
                }
            }
            res.redirect("/", 301);
            return "";
        });

        get("/logout", (req, res) -> {
            res.removeCookie("auth");
            res.redirect("/", 301);
            return "";
        });

    }


    // ----------- Méthodes privées
    private static int parseIdFromRequest(spark.Request req, String idName) {
        String id = req.params(":" + idName);
        // Si l'id est null ou pas un entier, alors on retourne une erreur
        if (id == null) {
            return -1;
        }

        int myId;

        try {
            myId = Integer.parseInt(id);
        } catch (Exception e) {
            return -1;
        }

        return myId;
    }

    private static boolean isAnyFieldEmpty(spark.Request req, String[] fields) {
        for (String f : fields) {
            if (req.queryParams(f) == null || req.queryParams(f).isBlank()) {
                return true;
            }
        }
        return false;
    }

    private static ProfEntity getAuthenticatedUser(Request req) {
        String token = req.cookie("auth");
        return token == null ? null : doLogin.introspect(token);
    }

}