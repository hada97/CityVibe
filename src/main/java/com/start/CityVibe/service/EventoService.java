package com.start.CityVibe.service;

import com.start.CityVibe.domain.evento.Evento;
import com.start.CityVibe.domain.evento.EventoDTO;
import com.start.CityVibe.domain.user.User;
import com.start.CityVibe.repository.EventoRepository;
import com.start.CityVibe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    public Evento salvarEvento(EventoDTO eventoDTO) {
        Long userId = userService.getUserIdByEmailFromGoogle();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Evento evento = new Evento();
        evento.setNome(eventoDTO.getNome());
        evento.setData(eventoDTO.getData());
        evento.setHora(eventoDTO.getHora());
        evento.setDescricao(eventoDTO.getDescricao());
        evento.setCategoria(eventoDTO.getCategoria());
        evento.setTipoEvento(eventoDTO.getTipoEvento());
        evento.setCapa(eventoDTO.getCapa());
        evento.setUser(user);
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
