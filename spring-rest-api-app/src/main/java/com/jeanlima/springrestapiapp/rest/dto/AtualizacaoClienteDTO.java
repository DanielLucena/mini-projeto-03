package com.jeanlima.springrestapiapp.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizacaoClienteDTO {
    private String novoNome;
    private String novoCpf;
}
