package com.start.CityVibe.service;

import com.start.CityVibe.domain.evento.*;
import com.start.CityVibe.domain.user.User;
import com.start.CityVibe.repository.EventoRepository;
import com.start.CityVibe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public EventoDetail salvarEvento(EventoDTO data) {
        Long userId = userService.getUserIdByEmailFromGoogle();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Evento evento = new Evento();
        evento.setNome(data.getNome());
        evento.setData(data.getData());
        evento.setHora(data.getHora());
        evento.setDescricao(data.getDescricao());
        evento.setTipoEvento(data.getTipoEvento());
        evento.setCapa(data.getCapa());
        evento.setUser(user);

        eventoRepository.save(evento);
        return new EventoDetail(evento);
    }

    // Listar todos os eventos
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    // Obter um evento por ID
    public Optional<Evento> obterEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    @Transactional
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
