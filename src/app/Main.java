package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
