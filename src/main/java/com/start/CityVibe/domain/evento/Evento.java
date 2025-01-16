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

    // Usando o enum TipoEvento
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "capa", nullable = true)
    private String capa;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "link", nullable = true)
    private String link;

    @Column(name = "custo", nullable = false)
    private String custo;


    public EventoDTO toDTO() {
        return new EventoDTO(
                this.id,
                this.nome,
                this.data,
                this.hora,
                this.descricao,
                this.tipoEvento,
                this.user != null ? this.user.getId() : null,
                this.capa,
                this.cidade,
                this.endereco,
                this.link,
                this.custo
        );
    }


}
