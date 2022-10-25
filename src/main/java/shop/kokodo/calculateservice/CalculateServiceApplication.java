package shop.kokodo.calculateservice;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableBatchProcessing
@SpringBootApplication
@EnableFeignClients
public class CalculateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculateServiceApplication.class, args);
    }

}
