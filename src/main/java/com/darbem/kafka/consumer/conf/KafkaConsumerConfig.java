package com.darbem.kafka.consumer.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${kafka.group.id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		// Configurando o kafka para nossa fila
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

		//Definindo o grupo de leitura
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

		// Configurando a estrategia de deserializacao da chave e valor do kafka
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//Definindo o auto-commit para false
		configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);

		return new DefaultKafkaConsumerFactory<>(configProps);

	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> listener = new ConcurrentKafkaListenerContainerFactory<>();

		listener.setConsumerFactory(consumerFactory());

		// Não falhar, caso ainda não existam os tópicos para consumo
		listener.getContainerProperties().setMissingTopicsFatal(Boolean.FALSE);

		// ### AQUI
		// Commit manual do offset
		listener.getContainerProperties().setAckMode(AckMode.MANUAL);

		// ### AQUI
		// Commits síncronos
		listener.getContainerProperties().setSyncCommits(Boolean.TRUE);

		return listener;
	}
}