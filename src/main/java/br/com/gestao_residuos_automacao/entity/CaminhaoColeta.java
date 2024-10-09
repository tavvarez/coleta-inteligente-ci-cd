package br.com.gestao_residuos_automacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CAMINHOES_COLETA")
public class CaminhaoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caminhaoId;

    private String placa;
    private Integer capacidade;
    private Double latitude;
    private Double longitude;
    private java.sql.Date dataAtualizacao;

    // Construtores, getters e setters

    public CaminhaoColeta() {
    }

    public CaminhaoColeta(String placa, Integer capacidade, Double latitude, Double longitude, java.sql.Date dataAtualizacao) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public java.sql.Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(java.sql.Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
