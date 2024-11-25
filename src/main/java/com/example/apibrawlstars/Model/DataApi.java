package com.example.apibrawlstars.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataApi {
    private Conexion conexion;

    public DataApi() {
        this.conexion = new Conexion();
    }

    // Método para obtener el token de la configuración
    private String getApiToken() {
<<<<<<< Updated upstream
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImU3YTc4OTRjLTE1YzUtNGI4YS1iMDQwLTkzNWFjZTdjZWUzNyIsImlhdCI6MTczMjUyMTY2Miwic3ViIjoiZGV2ZWxvcGVyLzMxMzhlMGMzLTBjMGQtNjkwMS00MDEwLTQwMDBhNTc5NjdjOCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTk1LjU3LjEwNC4xMjIiXSwidHlwZSI6ImNsaWVudCJ9XX0.tYv7zpxzxqLUtlfjitD5QhyfIiQVgbSEiF1YKY_q0Fy4fcI5tI9KsSGcePwXhQkG9M9rY2sA2AQanMj1El0dCA";
=======
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjIwOGRjYjBmLTg5NDQtNDQ5Yi04OGFmLTU4NzkzNjVmMDRiMyIsImlhdCI6MTczMjI2MTUwNCwic3ViIjoiZGV2ZWxvcGVyLzMxMzhlMGMzLTBjMGQtNjkwMS00MDEwLTQwMDBhNTc5NjdjOCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTk1LjU3LjEwNC4xMjIiXSwidHlwZSI6ImNsaWVudCJ9XX0.zI9H4CeP4yeMC9qhPG4LWmUncuUEGGRmMqy3wTMZ-dV8qzJRQOzZsIrYJvd5-uJ4fYIfWK0ACbmwMihm8mfYzA";
>>>>>>> Stashed changes
    }

    // Método para convertir la fecha al formato MySQL
    private String convertToMySQLDatetime(String dateTime) {
        try {
            // Formato original de la API
            SimpleDateFormat apiFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSS'Z'");
            apiFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Asegurarse de manejar UTC

            // Formato deseado para MySQL
            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Parsear y formatear
            Date date = apiFormat.parse(dateTime);
            return mysqlFormat.format(date);
        } catch (ParseException e) {
            System.err.println("Error al convertir la fecha: " + e.getMessage());
            return null; // Devuelve null si hay un error
        }
    }

    // Método para obtener los brawlers y almacenarlos en la base de datos
    public void fetchAndInsertBrawlers() {
        String apiUrlBrawlers = "https://api.brawlstars.com/v1/brawlers";
        String jsonResponse = getApiResponse(apiUrlBrawlers);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.err.println("Error: La respuesta de la API está vacía o nula.");
            return;
        }

        try {
            parseAndInsertBrawlers(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta de la API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener la respuesta de la API
    private String getApiResponse(String apiUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getApiToken());

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("HTTP Error: " + responseCode);
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    response.append(line);
                }
                errorReader.close();
                throw new RuntimeException("HTTP error code : " + responseCode + ". Response: " + response.toString());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    // Método para parsear la respuesta JSON y almacenar los datos en la base de datos
    private void parseAndInsertBrawlers(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray items = jsonObject.getJSONArray("items");

        try (Connection conn = conexion.conectarMySQL()) {
            String insertBrawlerQuery = "INSERT INTO Brawlers (playerTag, id, nameBrawler) VALUES (?, ?, ?)";
            String insertStarPowerQuery = "INSERT INTO StarPowers (id, name, brawlerId) VALUES (?, ?, ?)";
            String insertGadgetQuery = "INSERT INTO Gadgets (id, name, brawlerId) VALUES (?, ?, ?)";

            for (int i = 0; i < items.length(); i++) {
                JSONObject brawler = items.getJSONObject(i);
                String playerTag = "defaultTag"; // Hay que crear uno por defecto en ese caso un jugador "defaultTag"
                int brawlerId = brawler.getInt("id");
                String brawlerName = brawler.getString("name");

                // Insertar Brawler
                try (PreparedStatement pstmt = conn.prepareStatement(insertBrawlerQuery)) {
                    pstmt.setString(1, playerTag);
                    pstmt.setInt(2, brawlerId);
                    pstmt.setString(3, brawlerName);
                    pstmt.executeUpdate();
                }

                // Insertar Star Powers
                JSONArray starPowers = brawler.getJSONArray("starPowers");
                for (int j = 0; j < starPowers.length(); j++) {
                    JSONObject starPower = starPowers.getJSONObject(j);
                    int starPowerId = starPower.getInt("id");
                    String starPowerName = starPower.getString("name");

                    try (PreparedStatement pstmt = conn.prepareStatement(insertStarPowerQuery)) {
                        pstmt.setInt(1, starPowerId);
                        pstmt.setString(2, starPowerName);
                        pstmt.setInt(3, brawlerId);
                        pstmt.executeUpdate();
                    }
                }

                // Insertar Gadgets
                JSONArray gadgets = brawler.getJSONArray("gadgets");
                for (int j = 0; j < gadgets.length(); j++) {
                    JSONObject gadget = gadgets.getJSONObject(j);
                    int gadgetId = gadget.getInt("id");
                    String gadgetName = gadget.getString("name");

                    try (PreparedStatement pstmt = conn.prepareStatement(insertGadgetQuery)) {
                        pstmt.setInt(1, gadgetId);
                        pstmt.setString(2, gadgetName);
                        pstmt.setInt(3, brawlerId);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // - Todos los brawlers que se obtengan de la API se insertarán con el playerTag "defaultTag"
    // - Los Star Powers y Gadgets de cada Brawler tambien estaran asociados a ese playerTag
    //* =============== HASTA AQUI SE AÑADIERON POR DEFECTO EN LA BDD =============== *//



    //* =============== TRAER LOS DATOS DE LA API DEL EVENTO  =============== *//
    // - Se debe implementar un método que permita obtener los brawlers de la base de datos
    // Método para obtener los eventos y almacenarlos en la base de datos
    public void fetchAndInsertEvents() {
        String apiUrlEvents = "https://api.brawlstars.com/v1/events/rotation";
        String jsonResponse = getApiResponse(apiUrlEvents);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.err.println("Error: La respuesta de la API está vacía o nula.");
            return;
        }

        try {
            parseAndInsertEvents(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta de la API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para parsear la respuesta JSON e insertar los eventos en la base de datos
    private void parseAndInsertEvents(String jsonResponse) {
        JSONArray eventsArray = new JSONArray(jsonResponse);

        try (Connection conn = conexion.conectarMySQL()) {
            String insertEventQuery = "INSERT INTO eventos (startTime, endTime, slotId, eventId, mode, map, modifiers) VALUES (?, ?, ?, ?, ?, ?, ?)";

            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject eventObj = eventsArray.getJSONObject(i);

                // Extraer datos del objeto JSON
                String startTime = convertToMySQLDatetime(eventObj.getString("startTime"));
                String endTime = convertToMySQLDatetime(eventObj.getString("endTime"));
                int slotId = eventObj.getInt("slotId");
                JSONObject eventDetails = eventObj.getJSONObject("event");
                int eventId = eventDetails.getInt("id");
                String mode = eventDetails.getString("mode");
                String map = eventDetails.getString("map");
                JSONArray modifiersArray = eventDetails.optJSONArray("modifiers");

                // Convertir modifiers a JSON (si existe)
                String modifiers = modifiersArray != null ? modifiersArray.toString() : null;

                // Validar fechas
                if (startTime == null || endTime == null) {
                    System.err.println("Error: Fechas inválidas para el evento ID: " + eventId);
                    continue; // Saltar este evento
                }

                // Insertar el evento en la base de datos
                try (PreparedStatement pstmt = conn.prepareStatement(insertEventQuery)) {
                    pstmt.setString(1, startTime);
                    pstmt.setString(2, endTime);
                    pstmt.setInt(3, slotId);
                    pstmt.setInt(4, eventId);
                    pstmt.setString(5, mode);
                    pstmt.setString(6, map);
                    pstmt.setString(7, modifiers);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //* =============== TRAER LOS DATOS DE LA API DEL JUGADOR  =============== *//
    public void fetchAndInsertPlayer(String playerTag) {
        String formattedPlayerTag = playerTag.replace("#", "");

        String apiUrlPlayer = "https://api.brawlstars.com/v1/players/%23" + formattedPlayerTag;

        String jsonResponse = getApiResponse(apiUrlPlayer);
        System.out.println("Respuesta de la API: " + jsonResponse);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.err.println("Error: La respuesta de la API está vacía o nula.");
            return;
        }

        try {
            parseAndInsertPlayers(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta de la API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void parseAndInsertPlayers(String jsonResponse) {
        JSONObject playerObj = new JSONObject(jsonResponse);

        String playerTag = playerObj.getString("tag");
        String playerName = playerObj.getString("name");
        String nameColor = playerObj.optString("nameColor", null);
        int iconId = playerObj.getJSONObject("icon").getInt("id");
        int trophies = playerObj.getInt("trophies");
        int highestTrophies = playerObj.getInt("highestTrophies");
        int expLevel = playerObj.getInt("expLevel");
        int expPoints = playerObj.getInt("expPoints");
        boolean isQualifiedFromChampChallenge = playerObj.getBoolean("isQualifiedFromChampionshipChallenge");
        int victories3vs3 = playerObj.getInt("3vs3Victories");
        int soloVictories = playerObj.getInt("soloVictories");
        int duoVictories = playerObj.getInt("duoVictories");
        int bestRoboRumbleTime = playerObj.getInt("bestRoboRumbleTime");
        int bestTimeAsBigBrawler = playerObj.getInt("bestTimeAsBigBrawler");
        String clubTag = null;

        try (Connection conn = conexion.conectarMySQL()) {
            if (playerObj.has("club") && !playerObj.isNull("club")) {
                JSONObject clubObj = playerObj.getJSONObject("club");

                if (clubObj.has("tag") && !clubObj.isNull("tag")) {
                    clubTag = clubObj.getString("tag");
                    String clubName = clubObj.optString("name", "Unknown Club");

                    // Verificar e insertar el club si no existe
                    String insertClubQuery = """
                            INSERT INTO Clubs (tag, nombre, tipo, trophies, requiredTrophies)
                            VALUES (?, ?, 'unknown', 0, 0)
                            ON DUPLICATE KEY UPDATE nombre = VALUES(nombre)
                        """;

                    try (PreparedStatement pstmt = conn.prepareStatement(insertClubQuery)) {
                        pstmt.setString(1, clubTag);
                        pstmt.setString(2, clubName);
                        pstmt.executeUpdate();
                    }
                }
            }

            // Insertar datos del jugador
            String insertPlayerQuery = """
    INSERT INTO Players (tag, name, nameColor, iconId, trophies, highestTrophies, expLevel, expPoints,
                         isQualifiedFromChampChallenge, threeVsThreeVictories, soloVictories, duoVictories,
                         bestRoboRumbleTime, bestTimeAsBigBrawler, clubTag)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ON DUPLICATE KEY UPDATE
        name = VALUES(name), nameColor = VALUES(nameColor), iconId = VALUES(iconId),
        trophies = VALUES(trophies), highestTrophies = VALUES(highestTrophies),
        expLevel = VALUES(expLevel), expPoints = VALUES(expPoints),
        isQualifiedFromChampChallenge = VALUES(isQualifiedFromChampChallenge),
        threeVsThreeVictories = VALUES(threeVsThreeVictories),
        soloVictories = VALUES(soloVictories), duoVictories = VALUES(duoVictories),
        bestRoboRumbleTime = VALUES(bestRoboRumbleTime), bestTimeAsBigBrawler = VALUES(bestTimeAsBigBrawler),
        clubTag = VALUES(clubTag)
""";

            try (PreparedStatement pstmt = conn.prepareStatement(insertPlayerQuery)) {
                pstmt.setString(1, playerTag);
                pstmt.setString(2, playerName);
                pstmt.setString(3, nameColor);
                pstmt.setInt(4, iconId);
                pstmt.setInt(5, trophies);
                pstmt.setInt(6, highestTrophies);
                pstmt.setInt(7, expLevel);
                pstmt.setInt(8, expPoints);
                pstmt.setBoolean(9, isQualifiedFromChampChallenge);
                pstmt.setInt(10, victories3vs3);
                pstmt.setInt(11, soloVictories);
                pstmt.setInt(12, duoVictories);
                pstmt.setInt(13, bestRoboRumbleTime);
                pstmt.setInt(14, bestTimeAsBigBrawler);
                pstmt.setString(15, clubTag);
                pstmt.executeUpdate();
            }

            // Insertar datos de los brawlers, gears, star powers y gadgets (sin cambios en la lógica principal)
            JSONArray brawlersArray = playerObj.getJSONArray("brawlers");
            String insertBrawlerQuery = """
                        INSERT INTO Brawlers (playerTag, id, nameBrawler, power, nRank, trophies, highestTrophies)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        ON DUPLICATE KEY UPDATE
                            nameBrawler = VALUES(nameBrawler), power = VALUES(power),
                            nRank = VALUES(nRank), trophies = VALUES(trophies), highestTrophies = VALUES(highestTrophies)
                    """;

            String insertGearQuery = """
                        INSERT INTO Gears (id, name, level, playerTag, brawlerId)
                        VALUES (?, ?, ?, ?, ?)
                        ON DUPLICATE KEY UPDATE level = VALUES(level)
                    """;

            String insertStarPowerQuery = """
                        INSERT INTO StarPowers (id, name, brawlerId)
                        VALUES (?, ?, ?)
                        ON DUPLICATE KEY UPDATE name = VALUES(name)
                    """;

            String insertGadgetQuery = """
                        INSERT INTO Gadgets (id, name, brawlerId)
                        VALUES (?, ?, ?)
                        ON DUPLICATE KEY UPDATE name = VALUES(name)
                    """;

            for (int i = 0; i < brawlersArray.length(); i++) {
                JSONObject brawler = brawlersArray.getJSONObject(i);
                int brawlerId = brawler.getInt("id");
                String brawlerName = brawler.getString("name");
                int power = brawler.getInt("power");
                int rank = brawler.getInt("rank");
                int brawlerTrophies = brawler.getInt("trophies");
                int brawlerHighestTrophies = brawler.getInt("highestTrophies");

                try (PreparedStatement pstmt = conn.prepareStatement(insertBrawlerQuery)) {
                    pstmt.setString(1, playerTag);
                    pstmt.setInt(2, brawlerId);
                    pstmt.setString(3, brawlerName);
                    pstmt.setInt(4, power);
                    pstmt.setInt(5, rank);
                    pstmt.setInt(6, brawlerTrophies);
                    pstmt.setInt(7, brawlerHighestTrophies);
                    pstmt.executeUpdate();
                }

                // Insert gears
                JSONArray gearsArray = brawler.getJSONArray("gears");
                for (int j = 0; j < gearsArray.length(); j++) {
                    JSONObject gear = gearsArray.getJSONObject(j);
                    int gearId = gear.getInt("id");
                    String gearName = gear.getString("name");
                    int gearLevel = gear.getInt("level");

                    try (PreparedStatement pstmt = conn.prepareStatement(insertGearQuery)) {
                        pstmt.setInt(1, gearId);
                        pstmt.setString(2, gearName);
                        pstmt.setInt(3, gearLevel);
                        pstmt.setString(4, playerTag);
                        pstmt.setInt(5, brawlerId);
                        pstmt.executeUpdate();
                    }
                }

                // Insert star powers
                JSONArray starPowersArray = brawler.getJSONArray("starPowers");
                for (int j = 0; j < starPowersArray.length(); j++) {
                    JSONObject starPower = starPowersArray.getJSONObject(j);
                    int starPowerId = starPower.getInt("id");
                    String starPowerName = starPower.getString("name");

                    try (PreparedStatement pstmt = conn.prepareStatement(insertStarPowerQuery)) {
                        pstmt.setInt(1, starPowerId);
                        pstmt.setString(2, starPowerName);
                        pstmt.setInt(3, brawlerId);
                        pstmt.executeUpdate();
                    }
                }

                // Insert gadgets
                JSONArray gadgetsArray = brawler.getJSONArray("gadgets");
                for (int j = 0; j < gadgetsArray.length(); j++) {
                    JSONObject gadget = gadgetsArray.getJSONObject(j);
                    int gadgetId = gadget.getInt("id");
                    String gadgetName = gadget.getString("name");

                    try (PreparedStatement pstmt = conn.prepareStatement(insertGadgetQuery)) {
                        pstmt.setInt(1, gadgetId);
                        pstmt.setString(2, gadgetName);
                        pstmt.setInt(3, brawlerId);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //* =============== TRAER EL BATTLELOG DE LA API DEL JUGADOR  =============== *//
    public void fetchAndInsertBattleLog(String playerTag) {
        String formattedPlayerTag = playerTag.replace("#", "");

        String apiUrlBattleLog = "https://api.brawlstars.com/v1/players/%23" + formattedPlayerTag + "/battlelog";

        String jsonResponse = getApiResponse(apiUrlBattleLog);
        System.out.println("Respuesta de la API: " + jsonResponse);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.err.println("Error: La respuesta de la API está vacía o nula.");
            return;
        }

        try {
            parseAndInsertBattleLog(jsonResponse, playerTag);
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta de la API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void parseAndInsertBattleLog(String jsonResponse, String playerTag) {
        String playerTagFormatted = "#" + playerTag;
        try {
            // Intentar convertir la respuesta a un JSONObject
            JSONObject responseObject = new JSONObject(jsonResponse);

            // Maneja el caso de error, si es que hay uno
            if (responseObject.has("error")) {
                String errorMessage = responseObject.getString("error");
                System.err.println("Error de la API: " + errorMessage);
                return;
            }

            // Extraer el battle log
            if (responseObject.has("items")) {
                JSONArray battleLogArray = responseObject.getJSONArray("items");

                try (Connection conn = conexion.conectarMySQL()) {
                    String checkBattleLogQuery = "SELECT COUNT(*) FROM battles WHERE battleTime = ? AND playerTag = ?";
                    String insertBattleLogQuery = """
                INSERT INTO battles (battleTime, playerTag, battleMode, trophyChange, result, duration)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

                    for (int i = 0; i < battleLogArray.length(); i++) {
                        JSONObject battleLogObj = battleLogArray.getJSONObject(i);

                        // Extraer datos del objeto JSON
                        String battleTime = convertToMySQLDatetime(battleLogObj.getString("battleTime"));

                        // Acceder al objeto "battle" para obtener "mode", "result", "duration"
                        JSONObject battleObj = battleLogObj.getJSONObject("battle");
                        String battleMode = battleObj.getString("mode");

                        // Validar si la clave "result" existe antes de accederla
                        String result = "unknown"; // Valor por defecto
                        if (battleObj.has("result")) {
                            result = battleObj.getString("result");
                        }

                        // Validar si la clave "trophyChange" existe antes de accederla
                        int trophyChange = 0; // Valor por defecto
                        if (battleObj.has("trophyChange")) {
                            trophyChange = battleObj.getInt("trophyChange");
                        }

                        // Validar si la clave "duration" existe antes de accederla
                        int duration = 0; // Valor por defecto
                        if (battleObj.has("duration")) {
                            duration = battleObj.getInt("duration");
                        }

                        // Comprobar si el registro ya existe
                        try (PreparedStatement checkStmt = conn.prepareStatement(checkBattleLogQuery)) {
                            checkStmt.setString(1, battleTime);
                            checkStmt.setString(2, playerTagFormatted);
                            ResultSet rs = checkStmt.executeQuery();
                            rs.next();
                            int count = rs.getInt(1);

                            if (count == 0) {
                                // Insertar el registro si no existe
                                try (PreparedStatement insertStmt = conn.prepareStatement(insertBattleLogQuery)) {
                                    insertStmt.setString(1, battleTime);
                                    insertStmt.setString(2, playerTagFormatted);
                                    insertStmt.setString(3, battleMode);
                                    insertStmt.setInt(4, trophyChange);
                                    insertStmt.setString(5, result);
                                    insertStmt.setInt(6, duration);
                                    insertStmt.executeUpdate();
                                }
                            }
                        }
                    }
                }
            } else {
                System.err.println("No se encontró el campo 'items' en la respuesta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
