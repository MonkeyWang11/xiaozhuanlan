package view;

import control.PersonAction;
import model.Person;
import java.util.*;

public class Main {

    private static final String HINT = "欢迎进入用户管理界面\n" +
            "[UPDATE]:更新命令\n" +
            "[QUERY]:查询命令\n" +
            "[DEL]:删除命令\n" +
            "[ADD]:添加命令\n" +
            "[EXIT]:退出管理界面";

    private static final String EXIT = "EXIT";
    private static final String ADD = "ADD";
    private static final String DEL = "DEL";
    private static final String UPDATE = "UPDATE";
    private static final String QUERY = "QUERY";


    public static void main(String[] args) throws Exception {

        Person person = new Person();
        PersonAction personAction = new PersonAction();


        System.out.println(HINT);



        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.next();

            if (EXIT.equals(input)){
                System.out.println("成功退出管理界面");
                break;
            }else if (ADD.equals(input)){

                System.out.println("请输入姓名");
                String name = scanner.next();
                System.out.println("请输入年龄");
                int age = scanner.nextInt();
                person.setName(name);
                person.setAge(age);
                personAction.add(person);
                System.out.println("添加成功，请重新输入命令");
            }else if (UPDATE.equals(input)){

                System.out.println("请输入要更新用户的id");
                int id = scanner.nextInt();

                System.out.println("请输入要更新的用户姓名");
                String name = scanner.next();

                System.out.println("请输入要更新用户年龄");
                int age = scanner.nextInt();

                person.setName(name);
                person.setAge(age);

                personAction.update(id,person);
                System.out.println("更新成功，请重新输入命令");

            }else if (DEL.equals(input)){
                System.out.println("请输入要删除用户的id");
                int id = scanner.nextInt();
                personAction.del(id);
                System.out.println("删除成功，请重新输入命令");

            }else if (QUERY.equals(input)){
                ArrayList<Map<String,Object>> lists = new ArrayList<>();

                System.out.println("请输入要查询的用户姓名(null则不查询)");
                String name = scanner.next();
                if (!"null".equals(name)){
                    HashMap<String, Object> param1 = new HashMap<>();
                    param1.put("key","name");
                    param1.put("rela","like");
                    param1.put("value","'%"+name+"%'");
                    lists.add(param1);
                }

                System.out.println("请输入要查询的用户年龄");
                String age = scanner.next();
                if (!"null".equals(name)){
                    HashMap<String, Object> param2 = new HashMap<>();
                    param2.put("key","age");
                    param2.put("rela","=");
                    param2.put("value",age);
                    lists.add(param2);
                }

                List<Person> query = personAction.query(lists);
                for (Person p : query) {
                    System.out.println(p.toString());
                }
                System.out.println("查询成功，请重新输入命令");
            }
        }
    }
}
