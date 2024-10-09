package br.com.gestao_residuos_automacao.repository;

import br.com.gestao_residuos_automacao.model.AgendamentoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoColetaRepository extends JpaRepository<AgendamentoColeta, Long> {

    // Esta interface já herda os métodos CRUD básicos do JpaRepository:
    // - save(): Salva uma entidade no banco de dados
    // - findById(): Procura uma entidade por ID
    // - findAll(): Recupera todas as entidades
    // - deleteById(): Exclui uma entidade por ID

    // Se necessário, você pode adicionar métodos de consulta personalizados aqui
}
