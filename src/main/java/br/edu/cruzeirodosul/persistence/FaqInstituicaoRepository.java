package br.edu.cruzeirodosul.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cruzeirodosul.domain.FaqInstituicao;

@Repository
public interface FaqInstituicaoRepository extends JpaRepository<FaqInstituicao, Long> {
}
