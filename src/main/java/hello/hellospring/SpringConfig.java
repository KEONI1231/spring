package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;



@Configuration
public class SpringConfig {
    private DataSource dataSource;
    private final MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        //this.em = em;
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);

    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop ();
    }
/*
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcMemberRepository(dataSource);
        //return new JpaMemberRepository(em);


    }*/
}
