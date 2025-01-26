package com.start.CityVibe.domain.evento;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    @NotBlank
    private String nome;

    @Future
    private Date data;

    private Date hora;

    @NotNull
    private String descricao;

    @NotNull
    private TipoEvento tipoEvento;

    private Long userId;

    @NotBlank
    private String capa;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    @NotNull
    private String link;

    @NotBlank
    private String custo;

}
