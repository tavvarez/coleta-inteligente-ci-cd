package br.com.gestao_residuos_automacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "T_AGENDAMENTO_COLETA")
public class AgendamentoColeta {

    @Id
    @NotNull(message = "O agendamentoId deve ser fornecida")
    private Long agendamentoId;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    @NotNull(message = "A rota deve ser fornecida")
    private Rota rota;

    @NotNull(message = "A data agendada não pode ser nula")
    @PastOrPresent(message = "A data agendada deve ser no passado ou no presente")
    private Date dataAgendada;

    @NotBlank(message = "O status não pode estar em branco")
    private String status;

    // Construtores, getters e setters

    public AgendamentoColeta() {
    }

    public AgendamentoColeta(Long id, Rota rota, Date dataAgendada, String status) {
        this.agendamentoId = id;
        this.rota = rota;
        this.dataAgendada = dataAgendada;
        this.status = status;
    }

    public Long getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Long agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
