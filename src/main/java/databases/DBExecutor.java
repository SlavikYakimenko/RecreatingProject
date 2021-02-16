package databases;

import filehelpers.FileExecutor;
import model.Person;

import java.util.List;

public class DBExecutor implements FileExecutor{

    private final IDataBaseModel iDataBaseModel ;

      public DBExecutor(IDataBaseModel iDataBaseModel) {
          this.iDataBaseModel = iDataBaseModel;
      }

    @Override
    public void create(String fileName, List<Person> persons) {
        for (Person person : persons) {
            iDataBaseModel.writeToDB(person);
        }
    }

    @Override
    public List<Person> read(String fileName) {
        return iDataBaseModel.readFromDB();
    }


    @Override
    public void update(int id, String[] newValue, List<Person> personList, String fileName) {
        iDataBaseModel.updateInDB(id, newValue);

    }

    @Override
    public void delete(int id, List<Person> personList, String fileName) {
        iDataBaseModel.deleteFromDB(id);
    }

}
