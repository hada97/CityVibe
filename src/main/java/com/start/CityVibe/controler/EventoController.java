package com.start.CityVibe.controler;

import com.start.CityVibe.domain.evento.*;
import com.start.CityVibe.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @PostMapping
    public ResponseEntity<EventoDetail> criarEvento(@RequestBody @Valid EventoDTO eventoDTO) {
        var event = eventoService.salvarEvento(eventoDTO);
        return ResponseEntity.status(201).body(event);
    }


    @GetMapping
    public ResponseEntity<Page<Evento>> listarEventos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Evento> eventos = eventoService.listarEventos(pageable);

        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Evento> obterEvento(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.obterEventoPorId(id);
        return evento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscar")
    public Page<Evento> buscarEventosPorNome(
            @RequestParam("nome") String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return eventoService.buscarEventosPorNome(nome, page, size);
    }

    @GetMapping("/cidade/{cidade}")
    public Page<Evento> buscarEventosPorCidade(
            @PathVariable String cidade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return eventoService.buscarEventosPorCidade(cidade, page, size);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventoDetail> atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Optional<Evento> eventoExistente = eventoService.obterEventoPorId(id);

        if (eventoExistente.isPresent()) {
            evento.setId(id);
            EventoDetail eventoAtualizado = eventoService.salvarEvento(evento.toDTO());
            return new ResponseEntity<EventoDetail>(eventoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventoDetail> atualizarEvento(@PathVariable Long id, @RequestBody @Valid EventoUpdateDTO eventoUpdateDTO) {
        EventoDetail eventoAtualizado = eventoService.atualizarEvento(id, eventoUpdateDTO);
        if (eventoAtualizado != null) {
            return new ResponseEntity<>(eventoAtualizado, HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

}
