package com.start.CityVibe.service;

import com.start.CityVibe.domain.evento.*;
import com.start.CityVibe.domain.user.User;
import com.start.CityVibe.repository.EventoRepository;
import com.start.CityVibe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    // Método para listar todos os eventos
    public Page<Evento> listarEventos(Pageable pageable) {
        //return eventoRepository.findUpcomingEvents(pageable);
        return eventoRepository.findAll(pageable);
    }

    // Método para obter evento por ID
    public Optional<Evento> obterEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // Método para listar eventos por ID do usuário
    public Page<Evento> getEventosByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventoRepository.findByUserId(userId, pageable);
    }

    // Método para buscar eventos por nome
    public Page<Evento> buscarEventosPorNome(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventoRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    // Método para buscar eventos por cidade
    public Page<Evento> buscarEventosPorCidade(String cidade, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventoRepository.findByCidade(cidade, pageable);
    }

    // Método para atualizar o evento de forma parcial
    @Transactional
    public EventoDetail atualizarEvento(Long id, EventoUpdateDTO eventoUpdateDTO) {
        Optional<Evento> eventoExistente = obterEventoPorId(id);
        if (eventoExistente.isPresent()) {
            Evento evento = eventoExistente.get();

            if (eventoUpdateDTO.getNome() != null) {
                evento.setNome(eventoUpdateDTO.getNome());
            }
            if (eventoUpdateDTO.getDescricao() != null) {
                evento.setDescricao(eventoUpdateDTO.getDescricao());
            }
            if (eventoUpdateDTO.getCapa() != null) {
                evento.setCapa(eventoUpdateDTO.getCapa());
            }
            if (eventoUpdateDTO.getCidade() != null) {
                evento.setCidade(eventoUpdateDTO.getCidade());
            }
            if (eventoUpdateDTO.getEndereco() != null) {
                evento.setEndereco(eventoUpdateDTO.getEndereco());
            }
            if (eventoUpdateDTO.getLink() != null) {
                evento.setLink(eventoUpdateDTO.getLink());
            }
            if (eventoUpdateDTO.getCusto() != null) {
                evento.setCusto(eventoUpdateDTO.getCusto());
            }
            if (eventoUpdateDTO.getData() != null) {
                evento.setData(eventoUpdateDTO.getData());
            }
            if (eventoUpdateDTO.getHora() != null) {
                evento.setHora(eventoUpdateDTO.getHora());
            }
            eventoRepository.save(evento);

            return new EventoDetail(evento);
        } else {
            return null;
        }
    }

    // Método para salvar um novo evento
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
        evento.setCidade(data.getCidade());
        evento.setEndereco(data.getEndereco());
        evento.setLink(data.getLink());
        evento.setCusto(data.getCusto());
        eventoRepository.save(evento);
        return new EventoDetail(evento);
    }

    // Método para deletar um evento
    @Transactional
    public boolean deletarEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
