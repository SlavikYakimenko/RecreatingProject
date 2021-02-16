package databases;

import model.Person;

import java.util.List;

public interface IDataBaseModel {

    void writeToDB(Person person);

    List<Person> readFromDB();

    void updateInDB(int id, String[] newValue);

    void deleteFromDB(int id);
}
