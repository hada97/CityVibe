package com.start.CityVibe.domain.evento;

import java.util.Date;

public record EventoDetail(
        Long id,
        String nome,
        Date data,
        Date hora,
        String descricao,
        TipoEvento tipoEvento,
        Long userId,
        String capa
) {

    public EventoDetail(Evento evento) {
        this(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getHora(),
                evento.getDescricao(),
                evento.getTipoEvento(),
                evento.getUser() != null ? evento.getUser().getId() : null,
                evento.getCapa()
        );
    }
}
