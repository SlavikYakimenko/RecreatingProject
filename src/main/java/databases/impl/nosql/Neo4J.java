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
            params.put("Id", person.getId());
            params.put("Fname", person.getFname());
            params.put("Lname", person.getLname());
            params.put("Age", person.getAge());
            params.put("City", person.getCity());

            session.run("CREATE (n:persons{id:$Id,fname:$Fname,lname:$Lname,age:$Age,city:$City})", params);
        }
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
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newValue) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("Id", Integer.parseInt(newValue[0]));
            params.put("Fname", newValue[1]);
            params.put("Lname", newValue[2]);
            params.put("Age", Integer.parseInt(newValue[3]));
            params.put("City", newValue[4]);

            session.run("MATCH (n{fname:$Fname}) SET n.id=$Id,n.fname=$Fname, n.lname=$Lname, n.age=$Age, n.city=$City,", params);
        }
    }

    @Override
    public void deleteFromDB(int id) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("Id", id);
            session.run("MATCH (n{id:$id}) DETACH DELETE n");
        }

    }
}
