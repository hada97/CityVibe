package com.start.CityVibe.domain.evento;

import jakarta.validation.constraints.Future;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoUpdateDTO {

    private String nome;  // Permitindo não atualizar, caso não seja fornecido.

    private String descricao;  // Permitindo não atualizar, caso não seja fornecido.

    private String capa;  // Permitindo não atualizar, caso não seja fornecido.

    private String cidade;  // Permitindo não atualizar, caso não seja fornecido.

    private String endereco;  // Permitindo não atualizar, caso não seja fornecido.

    private String link;  // Permitindo não atualizar, caso não seja fornecido.

    private String custo;  // Permitindo não atualizar, caso não seja fornecido.

    @Future(message = "A data do evento deve ser no futuro")
    private Date data;  // A data do evento deve ser no futuro, mas pode ser nula se não for fornecida.

    private Date hora;  // A hora é opcional para a atualização.
}
