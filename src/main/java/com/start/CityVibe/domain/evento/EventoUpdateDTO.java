package com.start.CityVibe.domain.evento;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoUpdateDTO {

    private String nome;
    private String descricao;
    private String capa;
    private String cidade;
    private String endereco;
    private String link;
    private String custo;
    private Date data;
    private Date hora;

}
