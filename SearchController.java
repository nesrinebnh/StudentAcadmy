package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DBConnection.DbHundler;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SearchController implements Initializable {

	@FXML
    private JFXTextField surnameSearch;

    @FXML
    private JFXTextField nameSearch;

    @FXML
    private JFXButton searchInput;

    @FXML
    private TableView<Student> SearchTable;

    @FXML
    private TableColumn<Student, String> nameTableSearch;

    @FXML
    private TableColumn<Student, String> surnameTableSearch;

    @FXML
    private TableColumn<Student, String> moyTableSearch;

    @FXML
    private TableColumn<Student, String> birthdayTableSearch;

    @FXML
    private JFXButton backarrow;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    private Connection connection;
    private DbHundler hundler;
    private PreparedStatement pst;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		hundler = new DbHundler();

		SearchTable.setVisible(false);



	}

	@FXML
	public void searchAction(){
		int count =0;
		connection = hundler.getConnection();
		String get = "SELECT * from student where name=? and surname=?";
		try {
			pst = (PreparedStatement) connection.prepareStatement(get);
			pst.setString(1, nameSearch.getText());
			pst.setString(2, surnameSearch.getText());

			ResultSet rs = pst.executeQuery();



			while(rs.next()){
				count++;
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String moy = rs.getString("moy");
				String birthday = rs.getString("birthday");

				studentData.add(new Student(name,surname,birthday,moy));
			}


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

		nameTableSearch.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		surnameTableSearch.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		birthdayTableSearch.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
		moyTableSearch.setCellValueFactory(cellData -> cellData.getValue().moyProperty());
		if(count != 0){
			SearchTable.setVisible(true);
			SearchTable.setItems(studentData);
		}

	}

	@FXML
	public void backAction() throws IOException{
		backarrow.getScene().getWindow().hide();
		Stage signUp = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));

        Scene scene = new Scene(root);
        signUp.setScene(scene);
        signUp.show();
        signUp.setResizable(false);
	}



}
