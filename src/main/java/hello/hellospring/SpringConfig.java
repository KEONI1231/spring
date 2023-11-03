package hello.hellospring;

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

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());

    }
    @Bean
    public MemberRepository memberRepository() {

        //return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
