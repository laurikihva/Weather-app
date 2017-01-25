package weather_app;

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
    private static String userCity(String nameOfFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("temp.txt"));
        try {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                builder.append("\n");
                line = reader.readLine();
            }
            return builder.toString();
        } finally {
            reader.close();
        }
    }

    /** Getting the city information from the UI TextFile */
    public static String cityW() throws Exception {
        String city = userCity("temp.txt");
        city = city.substring(0, city.length()-1);
        return city;
    }

    /** Getting live weather API from the site */
    public static JSONObject getAPI() throws Exception {
        JSONObject weather = new JSONObject(getWeatherJson("http://api.openweathermap.org/data/2.5/weather?q=" + cityW() + "&units=metric&appid=3932495ab0dbe0868e6e779aa88af671"));
        return weather;
    }

    /** Getting the degree of the chosen city */
    public static String deg() throws Exception {
        String name = getAPI().getJSONObject("main").getString("temp");
        return name;
    }

    /** Information for the wind speed */
    public static String wind2() throws Exception {
        String speed = getAPI().getJSONObject("wind").getString("speed");
        return speed;
    }

    /** Getting information if it's snowing, raining or clear sky */
    public static String sky() throws Exception {
        JSONArray weatherArray = getAPI().getJSONArray("weather");
        String sky2 = null;
        for (int i = 0; i < weatherArray.length(); i++) {
            sky2 = weatherArray.getJSONObject(i).getString("main");
        }
        return sky2;
    }


    public static void main (String[] args) throws Exception {

        System.out.println(sky());

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