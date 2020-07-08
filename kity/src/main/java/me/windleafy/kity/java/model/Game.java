package me.windleafy.kity.java.model;

public class Game {

    private int id;
    private String name;
    private boolean isVip;
    private Integer age;
    private Long money;
    private double level;
    private float smart;

    public Game() {
    }

    public Game(int id, String name, boolean isVip, Integer age) {
        this.id = id;
        this.name = name;
        this.isVip = isVip;
        this.age = age;
    }

    public Game(int id, String name, boolean isVip, Integer age, Long money, double level, float smart) {
        this.id = id;
        this.name = name;
        this.isVip = isVip;
        this.age = age;
        this.money = money;
        this.level = level;
        this.smart = smart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public float getSmart() {
        return smart;
    }

    public void setSmart(float smart) {
        this.smart = smart;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isVip=" + isVip +
                ", age=" + age +
                ", money=" + money +
                ", level=" + level +
                ", smart=" + smart +
                '}';
    }
}
