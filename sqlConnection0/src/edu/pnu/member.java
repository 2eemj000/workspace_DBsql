package edu.pnu;
public class member {
    private int id;
    private String pass;
    private String name;

    // 기본 생성자
    public member() {
    }

    // 파라미터가 있는 생성자
    public member(int id, String pass, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    // Getter와 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}