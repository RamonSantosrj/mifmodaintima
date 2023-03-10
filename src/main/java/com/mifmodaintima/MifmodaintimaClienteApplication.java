package com.mifmodaintima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableKafka
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Mifmodaintima API", version = "1.0", description = "Api Clientes mifmodaintima"))
public class MifmodaintimaClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MifmodaintimaClienteApplication.class, args);
	}

}
