package com.start.CityVibe.domain.evento;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private Long id;

    @NotBlank
    private String nome;

    private Date data;

    private Date hora;

    private String descricao;

    private TipoEvento tipoEvento;

    private Long userId;

    @NotBlank
    private String capa;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    private String link;

    private String custo;

}
