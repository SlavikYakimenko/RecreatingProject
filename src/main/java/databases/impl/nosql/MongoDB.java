package databases.impl.nosql;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import databases.IDataBaseModel;
import model.Person;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoDB implements IDataBaseModel {

    private MongoClient getMongoClient() {
        return new MongoClient("localhost", 27017);
    }

    private MongoCollection<Document> getCollection() {
        return getMongoClient().getDatabase("MongoDB").getCollection("persons");
    }


    @Override
    public void writeToDB(Person person) {
        Document document = new Document();
        document.put("id", person.getId());
        document.put("Fname", person.getFname());
        document.put("Lname", person.getLname());
        document.put("Age", person.getAge());
        document.put("City", person.getCity());
        getCollection().insertOne(document);
        getMongoClient().close();
    }

    @Override
    public List<Person> readFromDB() {
        MongoCursor<Document> mongoCursor = getCollection().find().iterator();
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            person.setId(document.getInteger("id"));
            person.setFname(document.getString("Fname"));
            person.setLname(document.getString("Lname"));
            person.setAge(document.getInteger("Age"));
            person.setCity(document.getString("City"));
            personList.add(person);
        }
        getMongoClient().close();
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newValue) {
        Bson filter = eq("id", id);
        Bson updateOperation = and(
                set("id", newValue[1]),
                set("Fname", newValue[2]),
                set("Lname", newValue[3]),
                set("Age", newValue[4]),
                set("City", newValue[5])

        );
        getCollection().updateOne(filter, updateOperation);
        getMongoClient().close();
    }

    @Override
    public void deleteFromDB(int id) {
        Bson filter = eq("id", id);
        getCollection().deleteOne(filter);
        getMongoClient().close();
    }
}
