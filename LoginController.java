package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
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


public class LoginController implements Initializable {


    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXCheckBox remermberMe;

    @FXML
    private JFXButton forgot;

    @FXML
    private JFXPasswordField password;

    private Connection connection;
    private DbHundler hundler;
    private PreparedStatement pst;




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		hundler = new DbHundler();
	}

	@FXML
    public void LoginAction() throws IOException {
		System.out.println("salam");

		//Retrieve data
		connection = hundler.getConnection();
		String get = "SELECT * from user where username=? and password=?";
		try {
			pst = (PreparedStatement) connection.prepareStatement(get);
			pst.setString(1, username.getText());
			pst.setString(2, password.getText());

			ResultSet rs = pst.executeQuery();

			int count =0;

			while(rs.next()) count++;

			if(count == 1) {
				login.getScene().getWindow().hide();
				Stage signUp = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));

		        Scene scene = new Scene(root);
		        signUp.setScene(scene);
		        signUp.show();
		        signUp.setResizable(false);
			}
			else System.out.println("login failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				connection.close();
			}catch (SQLException e1){
				e1.printStackTrace();
			}
		}
    }

	@FXML
	public void SignUpAction() throws IOException{
		login.getScene().getWindow().hide();
		Stage signUp = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));

        Scene scene = new Scene(root);
        signUp.setScene(scene);
        signUp.show();
        signUp.setResizable(false);
	}



}
