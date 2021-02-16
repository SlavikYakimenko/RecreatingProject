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
        document.put("fname", person.getFname());
        document.put("lname", person.getLname());
        document.put("age", person.getAge());
        document.put("city", person.getCity());
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
            person.setFname(document.getString("fname"));
            person.setLname(document.getString("lname"));
            person.setAge(document.getInteger("age"));
            person.setCity(document.getString("city"));
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
                set("fname", newValue[2]),
                set("lname", newValue[3]),
                set("age", newValue[4]),
                set("city", newValue[5])

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
