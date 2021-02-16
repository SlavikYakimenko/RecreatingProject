package filehelpers;

import model.Person;

import java.util.List;

public interface FileExecutor {
    void create(String fileName, List<Person> persons);

    List<Person> read(String fileName);

    void update(int id, String[] newValue, List<Person> personList, String fileName);

    void delete(int id, List<Person> personList, String fileName);
}
