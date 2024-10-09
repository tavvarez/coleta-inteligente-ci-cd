package br.com.gestao_residuos_automacao.service;

import br.com.gestao_residuos_automacao.model.Rota;
import br.com.gestao_residuos_automacao.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    private final RotaRepository rotaRepository;

    @Autowired
    public RotaService(RotaRepository rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    // Método para buscar todas as rotas
    public List<Rota> getAllRotas() {
        return rotaRepository.findAll();
    }

    // Método para buscar uma rota por ID
    public Optional<Rota> getRotaById(Long id) {
        return rotaRepository.findById(id);
    }

    // Método para salvar uma nova rota
    public Rota salvarRota(Rota rota) {
        return rotaRepository.save(rota);
    }

    // Método para atualizar uma rota existente
    public Rota atualizarRota(Long id, Rota novaRota) {
        if (rotaRepository.existsById(id)) {
            novaRota.setRotaId(id);
            return rotaRepository.save(novaRota);
        }
        return null; // Retorna null se a rota não existir
    }

    // Método para excluir uma rota
    public void excluirRota(Long id) {
        rotaRepository.deleteById(id);
    }
}
