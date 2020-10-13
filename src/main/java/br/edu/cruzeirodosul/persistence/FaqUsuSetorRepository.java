package br.edu.cruzeirodosul.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cruzeirodosul.domain.FaqUsuSetor;

@Repository
public interface FaqUsuSetorRepository extends JpaRepository<FaqUsuSetor, Long> {
	List<FaqUsuSetor> findAllByNomeUsuOrderByIdSetor(String userName);
}
