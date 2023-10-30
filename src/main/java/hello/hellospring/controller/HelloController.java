package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
        //template에 있는 hello.html을 찾아라, 즉 html이름 바꾸면 get안됨
        //기본적으로 resources의 templates의 파일을 찾음
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name" , name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http에서 헤더부와 바디부가 있는데 바디 부분에 리턴 값을 바로 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // ex) hello string
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        //System.out.println(hello.name);
        return hello;
    }
    static class Hello
    {
        private String name;

        //getter, setter : 자바 빈 규약
        public String getName() {
           return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
