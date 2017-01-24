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

public class UI extends Application {

    public static void main(String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage primary) {
        primary.setTitle("Weather Application");
        GridPane location = new GridPane();
        location.setAlignment(Pos.TOP_LEFT);
        location.setHgap(10);
        location.setVgap(10);
        location.setPadding(new Insets(15, 25, 25, 25));

        /* Creating the scene box with measurements */
        Scene box = new Scene(location, 350, 275);
        primary.setScene(box);

        /* Setting up action title */
        Text topTitle = new Text("Enter Country and City:");
        topTitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        location.add(topTitle, 1, 0, 2, 1);

        /* Text field for the Country and City */
        TextField userChoice = new TextField();
        location.add(userChoice, 1, 1);

        /* The button to submit the choice */
        Button btn = new Button("Submit");
        location.add(btn, 2, 1);

        /* Setting up the action for the button */
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                /* Label for the city */
                Label city = new Label("Tallinn:");
                city.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
                location.add(city, 1, 2);

                /* Main degree position */
                Label degree = new Label("20°C");
                degree.setFont(Font.font("Calibri", FontWeight.NORMAL, 30));
                location.add(degree, 1, 3);

                /* Wind speed */
                Label wind = new Label("1,3 mps");
                wind.setFont(Font.font("Calibri", FontWeight.NORMAL, 30));
                location.add(wind, 2, 3);

            }
        });


        primary.show();
    }

}
