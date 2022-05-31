package com.ketul;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class IntroductionKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroductionKafkaApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("my-topic")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@Bean
	public KafkaAdmin.NewTopics topics() {
		return new KafkaAdmin.NewTopics(
			TopicBuilder.name("topic-1")
						.partitions(2)
						.replicas(20)
						.build(),

			TopicBuilder.name("topic-2")
						.partitions(2)
						.replicas(0)
						.build(),

			TopicBuilder.name("topic-3")
						.partitions(14)
						.replicas(8)
						.build()
		);
	}

	@KafkaListener(id = "my-id", topics = "my-topic")
	public void listen1(String in) {
		System.out.println(in);
	}

	@KafkaListener(id = "ketul-id", topics = "ketul")
	public void listen2(String in) {
		System.out.println(in);
	}

/*
	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> template.send("my-topic", "test");
	}
*/
}
