package databases.impl.nosql;

import databases.IDataBaseModel;
import model.Person;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class Redis implements IDataBaseModel {


    private final Jedis jedis = new Jedis("localhost");

    @Override
    public void writeToDB(Person person) {
        jedis.lpush("persons",
                person.getId() + ".->."
                        + person.getFname() + ".->."
                        + person.getLname() + ".->."
                        + person.getAge() + ".->."
                        + person.getCity());
        jedis.close();
    }

    @Override
    public List<Person> readFromDB() {
        List<Person> personList = new ArrayList<>();

        Person person;
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {

            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("<-->");

            person = new Person(
                    Integer.parseInt(arrayRead[0]),
                    arrayRead[1],
                    arrayRead[2],
                    Byte.parseByte(arrayRead[3]),
                    arrayRead[4]);
            personList.add(person);
        }
        jedis.close();
        return personList;
    }

    @Override
    public void updateInDB(int id, String[] newArr) {
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {

            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("<-_-_->");

            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lset("persons", count,
                        newArr[0] + ".---->."
                                + newArr[1] + ".---->."
                                + newArr[2] + ".---->."
                                + newArr[3] + ".---->."
                                + newArr[4]
                );
            }
        }
        jedis.close();
    }

    @Override
    public void deleteFromDB(int id) {
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {
            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("<-_-_->");
            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lrem("persons", count, read);
                break;
            }
        }
        jedis.close();
    }
}
