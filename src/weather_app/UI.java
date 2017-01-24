package weather_app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        location.setAlignment(Pos.CENTER);
        location.setHgap(10);
        location.setVgap(10);
        location.setPadding(new Insets(25, 25, 25, 25));

        /* Creating the scene box with measurements */
        Scene box = new Scene(location, 325, 275);
        primary.setScene(box);

        /* Setting up action title */
        Text topTitle = new Text("Enter Country and City:");
        topTitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        location.add(topTitle, 1, 0, 2, 1);

        TextField userChoice = new TextField();
        location.add(userChoice, 1, 1);

        primary.show();
    }

}
