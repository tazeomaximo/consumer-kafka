package com.darbem.kafka.consumer.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.darbem.kafka.consumer.dto.PagamentoDTO;
import com.darbem.kafka.consumer.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//https://springdoc.org/#migrating-from-springfox
@Tag(name = "Pagamento")
@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService service;

	@Operation(summary = "Get todos pagamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de pagamentos"),
			@ApiResponse(responseCode = "404", description = "Nenhum pagamento efetuado até o momento"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
		})
	@GetMapping(value = "/",produces = { MediaType.APPLICATION_JSON_VALUE})
	public List<PagamentoDTO> getAllPagamento() {
		
		var listaPgto = service.getAllPagamento();
		
		if(listaPgto.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum pagamento efetuado até o momento");
		}
		
		return listaPgto;

	}
}
