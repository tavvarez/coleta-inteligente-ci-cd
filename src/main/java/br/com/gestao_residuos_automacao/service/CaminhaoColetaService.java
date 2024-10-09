package br.com.gestao_residuos_automacao.service;

import br.com.gestao_residuos_automacao.model.CaminhaoColeta;
import br.com.gestao_residuos_automacao.repository.CaminhaoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoColetaService {

    private final CaminhaoColetaRepository caminhaoColetaRepository;

    @Autowired
    public CaminhaoColetaService(CaminhaoColetaRepository caminhaoColetaRepository) {
        this.caminhaoColetaRepository = caminhaoColetaRepository;
    }

    // Método para buscar todos os caminhões de coleta
    public List<CaminhaoColeta> getAllCaminhoes() {
        return caminhaoColetaRepository.findAll();
    }

    // Método para buscar um caminhão de coleta por ID
    public Optional<CaminhaoColeta> getCaminhaoById(Long id) {
        return caminhaoColetaRepository.findById(id);
    }

    // Método para salvar um novo caminhão de coleta
    public CaminhaoColeta salvarCaminhao(CaminhaoColeta caminhaoColeta) {
        return caminhaoColetaRepository.save(caminhaoColeta);
    }

    // Método para atualizar um caminhão de coleta existente
    public CaminhaoColeta atualizarCaminhao(Long id, CaminhaoColeta novoCaminhaoColeta) {
        if (caminhaoColetaRepository.existsById(id)) {
            novoCaminhaoColeta.setCaminhaoId(id);
            return caminhaoColetaRepository.save(novoCaminhaoColeta);
        }
        return null; // Retorna null se o caminhão de coleta não existir
    }

    // Método para excluir um caminhão de coleta
    public void excluirCaminhao(Long id) {
        caminhaoColetaRepository.deleteById(id);
    }
}
