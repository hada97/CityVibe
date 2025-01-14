package com.start.CityVibe.repository;


import com.start.CityVibe.domain.evento.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Evento> findAll(Pageable pageable);

    Page<Evento> findByUserId(Long userId, Pageable pageable);

}
