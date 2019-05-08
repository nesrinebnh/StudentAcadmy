package Principle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.print("ok");
            // Load root layout from fxml file.
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
            Scene scene = new Scene(root,600,340);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
