
package br.com.david.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.david.model.Posse;

import java.util.List;

@Repository
public interface PosseRepository extends JpaRepository<Posse, Long> {
    List<Posse> findByPessoaId(Long pessoaId);
}
