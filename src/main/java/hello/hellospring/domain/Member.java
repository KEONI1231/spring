package hello.hellospring.domain;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

//기본 유저객체
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //임의의  값. 시스템이 저장하는 값.
    //@Column(name = "userName")
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
