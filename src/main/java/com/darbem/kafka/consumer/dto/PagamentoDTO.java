package com.darbem.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Schema(name = "Pagamento")
public class PagamentoDTO {
	
	@Schema(name = "valor", description = "Valor pago",type = "double", defaultValue = "0.0")
	@JsonProperty("valor")
	private Double valor;
	
	@Schema(name = "observacao", description = "Colocar as resalvas se existir",type = "string", defaultValue = "N/A")
	@JsonProperty("observacao")
	private String observacao;
	
	@Schema(name = "cpf", description = "CPF do pagante",type = "string", defaultValue = "34243045810")
	@JsonProperty("cpf")
	private String cpf;
}
