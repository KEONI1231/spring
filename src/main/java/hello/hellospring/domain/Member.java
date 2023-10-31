package hello.hellospring.domain;

//기본 유저객체
public class Member {
    private Long id; //임의의  값. 시스템이 저장하는 값.
    private String name; //유저 이름

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
