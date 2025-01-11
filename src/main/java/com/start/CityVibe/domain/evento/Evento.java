package com.start.CityVibe.domain.evento;

import com.start.CityVibe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "eventos") // Nome da tabela no banco de dados
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Nome do evento
    @Column(name = "nome", nullable = false)
    private String nome;

    // Data do evento
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    // Hora do evento
    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;

    // Descrição do evento
    @Column(name = "descricao", nullable = true, length = 1000)
    private String descricao;

    // Categoria do evento (Show, Stand-Up, Gospel, etc.)
    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Usando o enum TipoEvento
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Caminho para a capa (imagem)
    @Column(name = "capa", nullable = true)
    private String capa;


    // Método para converter Evento para EventoDTO
    public EventoDTO toDTO() {
        return new EventoDTO(
                this.id,
                this.nome,
                this.data,
                this.hora,
                this.descricao,
                this.categoria,
                this.tipoEvento,
                this.user != null ? this.user.getId() : null,  // Pega apenas o ID do usuário
                this.capa
        );
    }



}
