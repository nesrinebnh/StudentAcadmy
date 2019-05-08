package FXML;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import DBConnection.DbHundler;

public class SignUpController implements Initializable {

	@FXML
    private JFXTextField usernameSign;

    @FXML
    private JFXTextField passSign;

    @FXML
    private JFXCheckBox female;

    @FXML
    private JFXCheckBox male;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXButton signupSign;

    @FXML
    private JFXButton loginSign;

    private Connection connection;
    private DbHundler hundler;
    private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		hundler = new DbHundler();

	}

	@FXML
	public void LoginSignAction() throws IOException{
		signupSign.getScene().getWindow().hide();
		Stage LoginStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));

        Scene scene = new Scene(root);
        LoginStage.setScene(scene);
        LoginStage.show();
        LoginStage.setResizable(false);
	}

	@FXML
	public void SignUpSignAction(){
		System.out.println("signed");

		//saving data
		String insert = "INSERT INTO user(username,password,gen,location)"+"VALUES (?,?,?,?)";
		connection = hundler.getConnection();
		try {
			pst = (PreparedStatement) connection.prepareStatement(insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pst.setString(1, usernameSign.getText());
			pst.setString(2, passSign.getText());
			pst.setString(3,getGender() );
			pst.setString(4, location.getText());


			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getGender(){
		String gen = "";

		if(male.isSelected()) gen = "Male";
		else gen = "female";

		return gen;
	}

}
