package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* config spring mvc using java code config instead xml config
* */
@Configuration
public class ApplicationContextConfig {

    //create bean class String
    @Bean
    public String myBean() {
        return new String("tran huu hong son");
    }


}
