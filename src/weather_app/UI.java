package weather_app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static weather_app.weather.*;

public class UI extends Application {

    @Override
    public void start(Stage primary) {
        primary.setTitle("Weather Application");
        GridPane location = new GridPane();
        location.setAlignment(Pos.TOP_LEFT);
        location.setHgap(5);
        location.setVgap(5);
        location.setPadding(new Insets(30, 25, 25, 25));

        /* Creating the scene box with measurements and background */
        Scene box = new Scene(location, 600, 275);
        box.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primary.setScene(box);

        /* Setting up action title */
        Text topTitle = new Text("Enter the name of City:");
        topTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        location.add(topTitle, 1, 0, 2, 1);

        /* Text field for the Country and City */
        final TextField userChoice = new TextField();
        userChoice.setPromptText("City");
        userChoice.getText();
        GridPane.setConstraints(userChoice, 1, 1);
        location.getChildren().add(userChoice);

        /* The button to submit the choice */
        Button btn = new Button("Submit");
        GridPane.setConstraints(btn, 2, 1);
        location.getChildren().add(btn);


        /* Setting up the action for the button */
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try (PrintStream out = new PrintStream(new FileOutputStream("temp.txt"))) {
                    out.print(userChoice.getText());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                /* String to make equal check work */
                String test = userChoice.getText();
                test = test.substring(0, 1).toUpperCase() + test.substring(1);

                try {
                    /* Giving an error, if user enters wrong city name or it contains numbers */
                    if (!test.equals(cityA())) {
                        /* Error label, if wrong city name entered */
                        Label error = new Label("Sorry, couldn't find that city! Try again!");
                        error.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
                        location.add(error, 1, 2);

                        /* Button to clear out error message */
                        Button clear = new Button("Clear");
                        GridPane.setConstraints(clear, 3, 1);
                        location.getChildren().add(clear);

                        clear.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                error.setText(null);
                            }
                        });
                    } else {
                        /* Label for the city */
                        Label city = new Label();
                        String cityText = userChoice.getText();
                        city.setText("Weather in " + cityText.substring(0, 1).toUpperCase() + cityText.substring(1) + ":");
                        city.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
                        location.add(city, 1, 2);

                        /* Main degree position */
                        Label degree = new Label();
                        try {
                            degree.setText(deg() + "°C");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        degree.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
                        location.add(degree, 1, 3);

                        /* Wind speed */
                        Label wind = new Label();
                        try {
                            wind.setText("Wind: " + wind2() + " mps");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        wind.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
                        location.add(wind, 2, 3);

                        Label sky = new Label();
                        try {
                            sky.setText(weather.sky());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        sky.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                        location.add(sky, 1, 4);

                        /** Button to clear the weather field */
                        Button clear = new Button("Clear");
                        GridPane.setConstraints(clear, 3, 1);
                        location.getChildren().add(clear);

                        clear.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {

                                city.setText(null);
                                degree.setText(null);
                                wind.setText(null);
                                sky.setText(null);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        primary.show();
    }

    public static void main(String[] args ) {

        launch(args);
    }

}
