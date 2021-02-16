package databases.impl.nosql;

import com.datastax.driver.core.*;
import databases.IDataBaseModel;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Cassandra implements IDataBaseModel {


    //root
    //
    //Йцукен1337
    /*CREATE KEYSPACE cassandra WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = 'true';*/
    private Session session;
    private Cluster cluster;

    private Session getSession() {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("cassandra");
        return session;
    }

    @Override
    public void writeToDB(Person person) {
        PreparedStatement preparedStatement = getSession().prepare("INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        session.execute(
                boundStatement.bind(
                        (int) person.getId(),
                        person.getFname(),
                        person.getLname(),
                        (int) person.getAge(),
                        person.getCity()
                )
        );
        cluster.close();
    }

    @Override
    public List<Person> readFromDB() {
        List<Person> personList = new ArrayList<>();
        Person person;
//        ResultSet resultset = getSession().execute();

        cluster.close();
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newValue) {

/*        PreparedStatement preparedStatement = getSession().prepare();
        BoundStatement boundStatement = new BoundStatement(preparedStatement);*/
        cluster.close();
    }

    @Override
    public void deleteFromDB(int id) {
/*        PreparedStatement preparedStatement = getSession().prepare();
        BoundStatement boundStatement = new BoundStatement(preparedStatement);*/
        cluster.close();
    }
}
