package com.start.CityVibe.domain.evento;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private Long id;            // ID do evento
    private String nome;        // Nome do evento
    private Date data;          // Data do evento
    private Date hora;          // Hora do evento
    private String descricao;   // Descrição do evento
    private TipoEvento tipoEvento; // Tipo do evento (enum)
    private Long userId;        // ID do usuário, ao invés do objeto User
    private String capa;        // URL da capa do evento
}
