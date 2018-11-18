package dao;

import model.Person;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonDao {

    /**
     * 新增Person
     * @param person
     * @throws SQLException
     */
    public void addPerson(Person person) throws SQLException {
        Connection connection = DbUtil.getConnection();

        String sql = "insert person (name,age) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.execute();
    }


    public void delPerson(int id) throws SQLException {
        Connection connection = DbUtil.getConnection();

        String sql = "delete from person where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    /**
     * 查询Person
     * @return
     * @throws SQLException
     */
    public List<Person> queryPerson(List<Map<String,Object>> params) throws SQLException {
        Connection connection = DbUtil.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from person where 1 = 1 ");

        if (params!=null && params.size()>0){
            for (int i = 0; i < params.size(); i++) {
                Map<String, Object> map = params.get(i);
                sql.append(" and "+map.get("key")+" "+map.get("rela")+" "+map.get("value"));

            }
        }

        System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(sql.toString());

        ArrayList<Person> persons = new ArrayList<>();
        Person person = null;
        while (resultSet.next()){
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            persons.add(person);
        }

        return persons;
    }

    /**
     * 更新Person
     */
    public void updatePerson(int id,Person person) throws SQLException {
        Connection connection = DbUtil.getConnection();

        String sql = "update person set name=?,age=? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setInt(3,id);
        preparedStatement.execute();
    }
}
