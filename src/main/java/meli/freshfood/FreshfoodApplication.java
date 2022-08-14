package meli.freshfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FreshfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshfoodApplication.class, args);
    }

}
