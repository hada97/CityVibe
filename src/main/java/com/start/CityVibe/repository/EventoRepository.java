package com.start.CityVibe.repository;

import com.start.CityVibe.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Aqui podemos adicionar consultas personalizadas, se necessário.
}
