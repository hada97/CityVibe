package com.start.CityVibe.controler;

import com.start.CityVibe.domain.evento.Evento;
import com.start.CityVibe.domain.evento.EventoDTO;
import com.start.CityVibe.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoDTO> criarEvento(@RequestBody EventoDTO eventoDTO) {
        // Converte o DTO para a entidade Evento
        Evento novoEvento = eventoService.salvarEvento(eventoDTO);

        // Converte a entidade Evento para o DTO para retornar
        EventoDTO novoEventoDTO = novoEvento.toDTO();

        return new ResponseEntity<>(novoEventoDTO, HttpStatus.CREATED);
    }


    // Obter todos os eventos
    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    // Obter um evento pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obterEvento(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.obterEventoPorId(id);
        return evento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar um evento
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Optional<Evento> eventoExistente = eventoService.obterEventoPorId(id);

        if (eventoExistente.isPresent()) {
            evento.setId(id);  // Mantém o ID do evento para a atualização
            Evento eventoAtualizado = eventoService.salvarEvento(evento.toDTO());
            return new ResponseEntity<>(eventoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar um evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        boolean removido = eventoService.deletarEvento(id);

        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
