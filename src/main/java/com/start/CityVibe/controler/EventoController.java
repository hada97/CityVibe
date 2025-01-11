package com.start.CityVibe.controler;

import com.start.CityVibe.domain.evento.*;
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
    public ResponseEntity<EventoDetail> criarEvento(@RequestBody EventoDTO eventoDTO) {
        var event = eventoService.salvarEvento(eventoDTO);
        return ResponseEntity.ok(event);
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


    @PutMapping("/{id}")
    public ResponseEntity<EventoDetail> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Optional<Evento> eventoExistente = eventoService.obterEventoPorId(id);

        if (eventoExistente.isPresent()) {
            evento.setId(id);
            EventoDetail eventoAtualizado = eventoService.salvarEvento(evento.toDTO());
            return new ResponseEntity<EventoDetail>(eventoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        boolean removal = eventoService.deletarEvento(id);

        if (removal) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
