package com.prem.SnakeAndLadderAPi;

import com.prem.SnakeAndLadderAPi.Service.SendAndResponseService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@ComponentScan
public class SnakeAndLadderAPiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(SnakeAndLadderAPiApplication.class, args);

	}

}
