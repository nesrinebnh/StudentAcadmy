package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;

public class MyStudentController implements Initializable {

	@FXML
    private TableView<Student> StudentTable;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, String> surnameStudent;

    @FXML
    private TableColumn<Student, String> birthdayStudent;

    @FXML
    private TableColumn<Student, String> moyStudent;

    @FXML
    private JFXButton deleteStudent;

    @FXML
    private JFXButton editStudent;

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

		connection = hundler.getConnection();
		String get = "SELECT * from student";
		try {
			pst = (PreparedStatement) connection.prepareStatement(get);


			ResultSet rs = pst.executeQuery();

			int count =0;

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

		studentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		surnameStudent.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		birthdayStudent.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
		moyStudent.setCellValueFactory(cellData -> cellData.getValue().moyProperty());

		StudentTable.setItems(studentData);
	}

	@FXML
	public void deleteAction(){
		int selectedIndex = StudentTable.getSelectionModel().getSelectedIndex();
		StudentTable.getItems().remove(selectedIndex);
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
