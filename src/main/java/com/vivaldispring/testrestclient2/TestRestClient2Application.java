package com.vivaldispring.testrestclient2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class TestRestClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(TestRestClient2Application.class, args);
	}

	@Bean
	RestClient restclient(RestClient.Builder builder){
		return builder.baseUrl("https://jsonplaceholder.typicode.com/todos").build();
	}
	@Bean
	ApplicationRunner applicationRunner(RestClient restClient){
		return  args -> {
			Todo body = restClient.get().uri("/{id}", 10).
					retrieve().body(Todo.class);
			System.out.println(body);
		};
	}

	record Todo(Long id, Long userid, String title) {}
}
