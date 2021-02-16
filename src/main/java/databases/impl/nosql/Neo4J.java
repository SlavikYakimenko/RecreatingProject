package databases.impl.nosql;

import databases.IDataBaseModel;
import model.Person;
import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neo4J implements IDataBaseModel {

    private Driver driver;

    private Driver getDriver() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "root"));
        return driver;
    }

    @Override
    public void writeToDB(Person person) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", person.getId());
            params.put("fname", person.getFname());
            params.put("lname", person.getLname());
            params.put("age", person.getAge());
            params.put("city", person.getCity());

            session.run("CREATE (n:persons{id:$id,fname:$fname,lname:$lname,age:$age,city:$city})", params);
        }
        driver.close();
    }


    @Override
    public List<Person> readFromDB() {
        List<Person> personList = new ArrayList<>();
        Person person1;
        try (Session session = getDriver().session()) {
            Result result = session.run("MATCH (n) RETURN n.id, n.fname,n.lname,n.age,n.city");
            while (result.hasNext()) {
                Record record = result.next();
                person1 = new Person(record.values().get(0).asInt(),
                        record.values().get(1).asString(),
                        record.values().get(2).asString(),
                        record.values().get(3).asInt(),
                        record.values().get(4).asString());
                personList.add(person1);
            }
        }
        driver.close();
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newValue) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", Integer.parseInt(newValue[0]));
            params.put("fname", newValue[1]);
            params.put("lname", newValue[2]);
            params.put("age", Integer.parseInt(newValue[3]));
            params.put("city", newValue[4]);

            session.run("MATCH (n{fname:$fname}) SET n.id=$id,n.fname=$fname, n.lname=$lname, n.age=$age, n.city=$city,", params);
        }
        driver.close();
    }

    @Override
    public void deleteFromDB(int id) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id:", id);
            session.run("MATCH (n{id:$id}) DETACH DELETE n");
        }
        driver.close();

    }
}
