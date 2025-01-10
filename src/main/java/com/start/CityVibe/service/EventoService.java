package com.start.CityVibe.service;

import com.start.CityVibe.domain.Evento;
import com.start.CityVibe.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Salvar um evento
    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Listar todos os eventos
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    // Obter um evento por ID
    public Optional<Evento> obterEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // Deletar um evento
    public boolean deletarEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Evento> getEventosByUserId(Long id, int page, int size) {
        return null;
    }
}
