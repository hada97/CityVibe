package com.start.CityVibe.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "eventos") // Nome da tabela no banco de dados
public class Evento {

    // Identificador único do evento
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

    // Tipo de evento (Pago ou Gratuito)
    @Column(name = "tipo_evento", nullable = false)
    private String tipoEvento;

    // Caminho para a capa (imagem)
    @Column(name = "capa", nullable = true)
    private String capa;

    // Construtor padrão
    public Evento() {}

    // Construtor com todos os parâmetros
    public Evento(String nome, Date data, Date hora, String descricao, String categoria, String tipoEvento, String capa) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.categoria = categoria;
        this.tipoEvento = tipoEvento;
        this.capa = capa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", hora=" + hora +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", capa='" + capa + '\'' +
                '}';
    }
}
