package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* CONFIG SPRING MVC USING JAVA CODE CONFIG INSTEAD XML CONFIG
* */
@Configuration
public class ApplicationContextConfig {

    //create bean class String/////
    @Bean
    public String myBean() {
        return new String("tran huu hong son");
    }


}
