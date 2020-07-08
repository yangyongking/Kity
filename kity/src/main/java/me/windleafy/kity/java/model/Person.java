package me.windleafy.kity.java.model;

public class Person implements InterFace {

    private Integer id;

    private String name;

    public String age;

    //构造函数1
    public Person() {

    }

    //构造函数2
    public Person(Integer id) {
        this.id = id;
    }

    //构造函数3
    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //构造函数3
    public Person(Integer id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 静态方法
     */
    public static void update() {

    }


    @Override
    public void read() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}