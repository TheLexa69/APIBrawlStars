package com.example.apibrawlstars.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataApi {
    private Conexion conexion;

    public DataApi() {
        this.conexion = new Conexion();
    }

    // Método para obtener el token de la configuración
    private String getApiToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjIwOGRjYjBmLTg5NDQtNDQ5Yi04OGFmLTU4NzkzNjVmMDRiMyIsImlhdCI6MTczMjI2MTUwNCwic3ViIjoiZGV2ZWxvcGVyLzMxMzhlMGMzLTBjMGQtNjkwMS00MDEwLTQwMDBhNTc5NjdjOCIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTk1LjU3LjEwNC4xMjIiXSwidHlwZSI6ImNsaWVudCJ9XX0.zI9H4CeP4yeMC9qhPG4LWmUncuUEGGRmMqy3wTMZ-dV8qzJRQOzZsIrYJvd5-uJ4fYIfWK0ACbmwMihm8mfYzA";
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
    // - Los Star Powers y Gadgets de cada Brawler


    //* =============== HASTA AQUI SE AÑADIERON POR DEFECTO EN LA BDD =============== *//
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
}
