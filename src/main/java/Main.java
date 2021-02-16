import databases.DBExecutor;
import databases.impl.nosql.MongoDB;
import databases.impl.nosql.Neo4J;
import databases.impl.sql.H2;
import databases.impl.sql.MySQL;
import databases.impl.sql.PostgresSQL;
import model.Person;
import ui.MainMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        MainMenu mainMenu = new MainMenu();

        Neo4J neo4J = new Neo4J();
        PostgresSQL postgresSQL = new PostgresSQL();
        MySQL mySQL = new MySQL();
        H2 h2 = new H2();
        Person person = new Person(1, "Kharkiv", "Jopov", 12, "Kharkiv");
//        List<Person> personList = new ArrayList<>();
        mySQL.writeToDB(person);
    }
}
