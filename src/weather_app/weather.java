package weather_app;

import lib.TextIO;

import org.json.*;

import java.io.*;
import java.net.*;

public class weather {

    private static String userCountry() {
        String countryToUpperCase;
        while (true) {
            System.out.print("Enter Country: ");
            countryToUpperCase = TextIO.getlnString();
            if (countryToUpperCase.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("I don't think that's a country! Try again!");
            }
        }
        return countryToUpperCase.substring(0, 1).toUpperCase() + countryToUpperCase.substring(1);
    }

    private static String userCity() {
        String cityToUpperCase;
        while (true) {
            System.out.print("Enter City: ");
            cityToUpperCase = TextIO.getlnString();
            if (cityToUpperCase.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("I don't think that's a city! Try again!");
            }
        }
        return cityToUpperCase.substring(0, 1).toUpperCase() + cityToUpperCase.substring(1);
    }

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

    public static void main (String[] args) throws Exception {

        String country = userCountry();
        String city = userCity();

        /* Getting city name, temperature and wind speed from JSON file **/
        JSONObject weather = new JSONObject(getWeatherJson("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units=metric&appid=3932495ab0dbe0868e6e779aa88af671"));
        String name = weather.getString("name");
        String temp = weather.getJSONObject("main").getString("temp");
        String speed = weather.getJSONObject("wind").getString("speed");

        /* Getting information if it's snowing, raining or clear sky **/
        JSONArray weatherArray = weather.getJSONArray("weather");
        String sky = null;
        for (int i = 0; i < weatherArray.length(); i++) {
            sky = weatherArray.getJSONObject(i).getString("main");
        }

        System.out.println("Just a sec, looking outside..");

        if (!city.equals(name)) {
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
        }

    } // end of main
} // end of weather