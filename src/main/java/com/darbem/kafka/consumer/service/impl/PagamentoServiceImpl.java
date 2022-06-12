package com.darbem.kafka.consumer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.darbem.kafka.consumer.dto.PagamentoDTO;
import com.darbem.kafka.consumer.service.PagamentoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService{
	
	
	private List<PagamentoDTO> list = new ArrayList<PagamentoDTO>();
	
	@Override
	public void salvarPagamento(PagamentoDTO dto) {
		log.info("Salvando pagamento: {}", dto);
		list.add(dto);
		log.info("Total de pagamento: {}", list.size());
		
		
	}

	@Override
	public List<PagamentoDTO> getAllPagamento() {
		log.info("Recuperando todos pagamento.");
		return this.list;
	}
	
	

}
