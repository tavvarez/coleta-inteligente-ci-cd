package br.com.gestao_residuos_automacao.repository;

import br.com.gestao_residuos_automacao.model.CaminhaoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminhaoColetaRepository extends JpaRepository<CaminhaoColeta, Long> {
    // Se necessário, você pode adicionar métodos de consulta personalizados aqui
}
