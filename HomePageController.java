package FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

	@FXML
    private JFXButton myStudent;

    @FXML
    private JFXButton SearchStudent;

    @FXML
    private JFXButton contactus;

    @FXML
    private JFXButton addStudent;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@FXML
	public void myStudentAction() throws IOException{
		System.out.println("delete");
		myStudent.getScene().getWindow().hide();
		Stage signUp = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/myStudent.fxml"));

        Scene scene = new Scene(root);
        signUp.setScene(scene);
        signUp.show();
        signUp.setResizable(false);
	}

	@FXML
	public void addStudent() throws IOException{
		System.out.println("add student");
		addStudent.getScene().getWindow().hide();
		Stage addStudentStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/addStudent.fxml"));

        Scene scene = new Scene(root);
        addStudentStage.setScene(scene);
        addStudentStage.show();
        addStudentStage.setResizable(false);
	}

	@FXML
	public void searchStudentAction() throws IOException{
		System.out.println("search student");
		SearchStudent.getScene().getWindow().hide();
		Stage addStudentStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/searchStudent.fxml"));

        Scene scene = new Scene(root);
        addStudentStage.setScene(scene);
        addStudentStage.show();
        addStudentStage.setResizable(false);
	}

	@FXML
	public void contactAction(){
		System.out.println("contact us");
	}

}
