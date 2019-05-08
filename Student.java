package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

	private StringProperty name,surname,birthday;
	private StringProperty moy;
	public Student(StringProperty name, StringProperty surname, StringProperty birthday, StringProperty moy) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.moy = moy;
	}

	public Student(String name, String surname, String birthday, String moy) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);

        // Some initial dummy data, just for convenient testing.
        this.birthday = new SimpleStringProperty(birthday);
        this.moy = new SimpleStringProperty(moy);

    }

	public Student() {
		super();
	}

	public String getName() {
        return name.get();
    }

    public void setName(String firstName) {
        this.name.set(firstName);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String firstName) {
        this.surname.set(firstName);
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getMoy() {
        return moy.get();
    }

    public void setMoy(String firstName) {
        this.moy.set(firstName);
    }

    public StringProperty moyProperty() {
        return moy;
    }

    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String firstName) {
        this.birthday.set(firstName);
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }




}
