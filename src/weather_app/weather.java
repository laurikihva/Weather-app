package weather_app;

import lib.TextIO;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;
import java.net.*;

public class weather {

    /** Makes the API request to get String of selected city weather forecast **/
    private static String getWeatherJson(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /** Gets the city from UI TextField */
    private static String userCity(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static String cityW() throws Exception {
        String city = userCity("temp.txt");
        city = city.substring(0, city.length()-1);
        return city;
    }

    public static JSONObject getAPI() throws Exception {
        JSONObject weather = new JSONObject(getWeatherJson("http://api.openweathermap.org/data/2.5/weather?q=" + cityW() + "&units=metric&appid=3932495ab0dbe0868e6e779aa88af671"));
        return weather;
    }


    public static String deg() throws Exception {
        String name = getAPI().getJSONObject("main").getString("temp");
        return name;
    }

    public static String wind2() throws Exception {
        String speed = getAPI().getJSONObject("wind").getString("speed");
        return speed;
    }


    public static void main (String[] args) throws Exception {


        /* Getting city name, temperature and wind speed from JSON file **/
        JSONObject weather = new JSONObject(getWeatherJson("http://api.openweathermap.org/data/2.5/weather?q=tartu&units=metric&appid=3932495ab0dbe0868e6e779aa88af671"));
        System.out.println(weather);
        /* Getting information if it's snowing, raining or clear sky **/
        JSONArray weatherArray = weather.getJSONArray("weather");
        String sky = null;
        for (int i = 0; i < weatherArray.length(); i++) {
            sky = weatherArray.getJSONObject(i).getString("main");
        }


        System.out.println("Just a sec, looking outside..");

        /** if (!city.equals(name)) {
            System.out.println("Sorry, I couldn't find that place!");
        } else {
            System.out.println("Well what do you know! In " + city + " it's currently " + temp + "°C with wind speed " + speed + "mps!");
            assert sky != null;
            switch (sky) {
                case "Snow":
                    System.out.println("And it's also snowy outside!");
                    break;
                case "Rain":
                    System.out.println("And it's also raining outside!");
                    break;
                case "Cloud":
                    System.out.println("And it's also cloudy outside!");
                    break;
                default:
                    System.out.println("And also the sky is clear outside!");
                    break;
            }
        } */

    } // end of main
} // end of weather