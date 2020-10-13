package br.edu.cruzeirodosul.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cruzeirodosul.domain.FaqSetor;

@Repository
public interface FaqSetorRepository extends JpaRepository<FaqSetor, Long> {
}
