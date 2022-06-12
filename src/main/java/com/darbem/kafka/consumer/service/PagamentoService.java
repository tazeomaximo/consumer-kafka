package com.darbem.kafka.consumer.service;

import java.util.List;

import com.darbem.kafka.consumer.dto.PagamentoDTO;


public interface PagamentoService {
	
	void salvarPagamento(PagamentoDTO dto);
	
	List<PagamentoDTO> getAllPagamento();
	
}
