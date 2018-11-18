package control;

import dao.PersonDao;
import model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PersonAction {

    public void add(Person person) throws SQLException {
        PersonDao personDao = new PersonDao();
        personDao.addPerson(person);
    }

    public void del(Integer id) throws SQLException {
        PersonDao personDao = new PersonDao();
        personDao.delPerson(id);
    }

    public void update(Integer id,Person person) throws SQLException {
        PersonDao personDao = new PersonDao();
        personDao.updatePerson(id,person);
    }

    public List<Person> query(List<Map<String,Object>> params) throws SQLException {
        PersonDao personDao = new PersonDao();
        return personDao.queryPerson(params);
    }
}
