package databases.impl.nosql;

import databases.IDataBaseModel;
import model.Person;

import java.util.List;

public class Redis implements IDataBaseModel {


    @Override
    public void writeToDB(Person person) {

    }

    @Override
    public List<Person> readFromDB() {
        return null;
    }

    @Override
    public void updateInDB(int id, String[] newValue) {

    }

    @Override
    public void deleteFromDB(int id) {

    }
}
