package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DBConnection.DbHundler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddStudentController implements Initializable {

	@FXML
    private JFXTextField nameInput;

    @FXML
    private JFXTextField surnameInput;

    @FXML
    private JFXTextField birthdayInput;

    @FXML
    private JFXTextField moyInput;

    @FXML
    private JFXButton addInput;

    private Connection connection;
    private DbHundler hundler;
    private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		hundler = new DbHundler();

	}

	@FXML
	public void addInputAction() throws IOException{
		//saving data
		
		if(nameInput.getText().equals("")||(surnameInput.getText().equals(""))||(birthdayInput.getText().equals(""))||(moyInput.getText().equals(""))){
			System.out.println("error");
			
			
		}else {
			String insert = "INSERT INTO student(name,surname,birthday,moy)"+"VALUES (?,?,?,?)";
			connection = hundler.getConnection();
			try {
				pst = (PreparedStatement) connection.prepareStatement(insert);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				pst.setString(1, nameInput.getText());
				pst.setString(2, surnameInput.getText());
				pst.setString(4,birthdayInput.getText() );
				pst.setString(3, moyInput.getText());


				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("done");

			addInput.getScene().getWindow().hide();
			Stage signUp = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));

	        Scene scene = new Scene(root);
	        signUp.setScene(scene);
	        signUp.show();
	        signUp.setResizable(false);
		}
		
	}

}
