package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorApp {
    private static final String API_KEY = "dfd7692b33681afafabc8c50";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static double convert(String fromCurrency, String toCurrency, double amount) {
        try {
            String urlStr = API_URL + fromCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(new InputStreamReader(request.getInputStream()), JsonObject.class);

            JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

            if (conversionRates.has(toCurrency)) {
                double rate = conversionRates.get(toCurrency).getAsDouble();
                return rate * amount;
            } else {
                System.out.println("Moneda destino no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
        return 0.0;
    }
}
