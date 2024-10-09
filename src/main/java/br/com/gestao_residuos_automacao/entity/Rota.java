package br.com.gestao_residuos_automacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "T_ROTAS")
public class Rota {

    @Id
    private Long rotaId;

    @NotBlank(message = "A descrição da rota não pode estar em branco")
    private String descricao;

    @NotNull(message = "O ID do caminhão não pode ser nulo")
    private Long caminhaoId;

    @NotNull(message = "A data da rota não pode ser nula")
    @PastOrPresent(message = "A data da rota deve ser no passado ou no presente")
    private Date data;

    @NotNull(message = "A latitude não pode ser nula")
    private Double latitude;

    @NotNull(message = "A longitude não pode ser nula")
    private Double longitude;

    @NotNull(message = "A capacidade atual não pode ser nula")
    private Double capacidadeAtual;

    // Construtores, getters e setters

    public Rota() {
    }

    public Rota(String descricao, Long caminhaoId, Date data, Double latitude, Double longitude, Double capacidadeAtual) {
        this.descricao = descricao;
        this.caminhaoId = caminhaoId;
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacidadeAtual = capacidadeAtual;
    }

    public Long getRotaId() {
        return rotaId;
    }

    public void setRotaId(Long rotaId) {
        this.rotaId = rotaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Double getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public void setCapacidadeAtual(Double capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }
}
