package br.com.gestao_residuos_automacao.service;

import br.com.gestao_residuos_automacao.model.AgendamentoColeta;
import br.com.gestao_residuos_automacao.model.Rota;
import br.com.gestao_residuos_automacao.repository.AgendamentoColetaRepository;
import br.com.gestao_residuos_automacao.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.gestao_residuos_automacao.exception.AgendamentoExistenteException;
import br.com.gestao_residuos_automacao.exception.RotaNotFoundException;
import javax.persistence.EntityNotFoundException;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AgendamentoColetaService {

    private final AgendamentoColetaRepository agendamentoColetaRepository;
    private final RotaRepository rotaRepository;

    @Autowired
    public AgendamentoColetaService(AgendamentoColetaRepository agendamentoColetaRepository, RotaRepository rotaRepository) {
        this.agendamentoColetaRepository = agendamentoColetaRepository;
        this.rotaRepository = rotaRepository;
    }

    // Método para buscar todos os agendamentos de coleta
    public List<AgendamentoColeta> getAllAgendamentos() {
        return agendamentoColetaRepository.findAll();
    }

    // Método para buscar um agendamento de coleta por ID
    public Optional<AgendamentoColeta> getAgendamentoById(Long id) {
        return agendamentoColetaRepository.findById(id);
    }

    // Método para salvar um novo agendamento de coleta
    public AgendamentoColeta salvarAgendamento(@Valid AgendamentoColeta agendamentoColeta) {
        try {
            // Verifica se a rota existe pelo ID
            Optional<Rota> rotaOptional = rotaRepository.findById(agendamentoColeta.getRota().getRotaId());
            if (rotaOptional.isEmpty()) {
                throw new RotaNotFoundException("A rota especificada não foi encontrada.");
            }

            // Restante da lógica para salvar o agendamento...
        } catch (EntityNotFoundException ex) {
            throw new RotaNotFoundException("A rota especificada não foi encontrada.");
        }

        // Verifica se o agendamento já existe pelo ID
        Optional<AgendamentoColeta> agendamentoExistente = agendamentoColetaRepository.findById(agendamentoColeta.getAgendamentoId());
        if (agendamentoExistente.isPresent()) {
            // Se o agendamento já existe, lança uma exceção ou trata o caso conforme necessário
            throw new AgendamentoExistenteException("Já existe um agendamento com o ID fornecido.");
        }

        // Se o agendamento não existe, salva-o no banco de dados
        return agendamentoColetaRepository.save(agendamentoColeta);
    }

    // Método para atualizar um agendamento de coleta existente
    public AgendamentoColeta atualizarAgendamento(Long id, @Valid AgendamentoColeta novoAgendamentoColeta) {
        try {
            // Verifica se a rota existe pelo ID
            Optional<Rota> rotaOptional = rotaRepository.findById(novoAgendamentoColeta.getRota().getRotaId());
            if (rotaOptional.isEmpty()) {
                throw new RotaNotFoundException("A rota especificada não foi encontrada.");
            }

            // Restante da lógica para atualizar o agendamento...
        } catch (EntityNotFoundException ex) {
            throw new RotaNotFoundException("A rota especificada não foi encontrada.");
        }

        if (agendamentoColetaRepository.existsById(id)) {
            novoAgendamentoColeta.setAgendamentoId(id);
            return agendamentoColetaRepository.save(novoAgendamentoColeta);
        }
        return null; // Retorna null se o agendamento não existir
    }

    // Método para cancelar um agendamento de coleta
    public void cancelarAgendamento(Long id) {
        agendamentoColetaRepository.deleteById(id);
    }
}
