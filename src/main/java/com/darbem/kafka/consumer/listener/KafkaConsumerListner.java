package com.darbem.kafka.consumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.darbem.kafka.consumer.dto.PagamentoDTO;
import com.darbem.kafka.consumer.service.PagamentoService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumerListner {
	
	@Autowired
	private PagamentoService service;

	@KafkaListener(topics = "${kafka.topic.name}")
	public void consume(@Payload String json, Acknowledgment ack) {
		
		try {
			
			PagamentoDTO pgto = new ObjectMapper()
									 // ignorando as propriedades que nao achar no json
									 .setSerializationInclusion(Include.NON_NULL)
									 .reader()
									 .forType(PagamentoDTO.class)
									 .readValue(json);
			
			
			service.salvarPagamento(pgto);
			
			log.info("Pagamento: {}", pgto);
			
		} catch (JsonProcessingException e) {
			log.error("Erro ao salvar pagamento: {}", e);
		}
		finally {
			//commit manual
			ack.acknowledge();
		}
		

	}
}
