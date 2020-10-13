package br.edu.cruzeirodosul.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cruzeirodosul.domain.FaqEmprSetor;

@Repository
public interface FaqEmprSetorRepository extends JpaRepository<FaqEmprSetor, Long> {
	List<FaqEmprSetor> findByCodEmpr(Long idEmpresa);

	List<FaqEmprSetor> findAllByIdSetorOrderByCodEmpr(Long idSetor);

	List<FaqEmprSetor> findAllByCodEmpr(Long idEmpresa);

	List<FaqEmprSetor> findAllByCodEmprAndIdSetorOrderByCodInst(Long idEmpresa, Long idSetor);
}
