package databases.impl.sql.executor;

import databases.IDataBaseModel;

import model.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class BasicSQLExecutor implements IDataBaseModel {

    public abstract Connection connectionSettings();


    @Override
    public void writeToDB(Person person) {
        try {
            String sql = "INSERT INTO persons (id,fname,lname,age,city)" + " VALUES (?,?,?,?,?)"; //Писать название таблиц одинаково!!
            PreparedStatement preparedStatement = connectionSettings().prepareStatement(sql); //CONST CONNECTION
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFname());
            preparedStatement.setString(3, person.getLname());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setString(5, person.getCity());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Add new user");
            }
            connectionSettings().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readFromDB() {
        List<Person> personList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM persons";
            Statement statement = connectionSettings().createStatement(); //CONST CONNECTION
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Person person = new Person();
                int id = result.getInt(1);
                String fname = result.getString(2);
                String lname = result.getString(3);
                int age = result.getInt(4);
                String city = result.getString(5);
                System.out.println("Id: '" + id + "', First name: '" + fname + "', Last name: '" + lname + "', Age: '" + age + "', City: '" + city);
                personList.add(person);
            }
            connectionSettings().close(); //CONST CONNECTION
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newArray) {
        try {
            String sql = "UPDATE users SET id=?,fname=?,lname=?,age=?,city=? WHERE id=?";
            PreparedStatement preparedStatement = connectionSettings().prepareStatement(sql); //CONST CONNECTION
            preparedStatement.setInt(1, Integer.parseInt(newArray[0]));
            preparedStatement.setString(2, newArray[1]);
            preparedStatement.setString(3, newArray[2]);
            preparedStatement.setInt(4, Integer.parseInt(newArray[3]));
            preparedStatement.setString(5, newArray[4]);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("User info was updated!");
            }
            connectionSettings().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromDB(int id) {
        try {
            String sql = "DELETE FROM persons WHERE id=?";
            PreparedStatement preparedStatement = connectionSettings().prepareStatement(sql); //CONST CONNECTION
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("User info was deleted !");
            }
            connectionSettings().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
