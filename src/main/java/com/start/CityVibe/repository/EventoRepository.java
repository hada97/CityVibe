package com.start.CityVibe.repository;


import com.start.CityVibe.domain.evento.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {


    @Query("SELECT e FROM Evento e WHERE CONCAT(e.data, ' ', e.hora) > CURRENT_TIMESTAMP")
    Page<Evento> findUpcomingEvents(Pageable pageable); // Método que retorna apenas os eventos futuros

    Page<Evento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Evento> findByCidade(String cidade, Pageable pageable);

    Page<Evento> findAll(Pageable pageable);

    Page<Evento> findByUserId(Long userId, Pageable pageable);

}
