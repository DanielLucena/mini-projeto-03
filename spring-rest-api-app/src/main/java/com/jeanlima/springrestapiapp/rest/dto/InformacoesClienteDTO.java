package com.jeanlima.springrestapiapp.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesClienteDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private List<InformacoesPedidoDTO> pedidos;
}
