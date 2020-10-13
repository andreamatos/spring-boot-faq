package br.edu.cruzeirodosul.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cruzeirodosul.domain.FaqDuvidas;

@Repository
public interface FaqDuvidasRepository extends JpaRepository<FaqDuvidas, Long> {
	Page<FaqDuvidas> findByIdUsuarioAndStatus(String idUsuario,Integer status,  Pageable pageable);
	
	FaqDuvidas findByIdFaqDuvidas(Long idFaqDuvidas);

	List<FaqDuvidas> findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(Integer codEmpr, Integer idSetor, 
			Integer idFrequencia, Integer status, Integer codInst);

	List<FaqDuvidas>findAllByOrderByFrequenciaDesc();

	List<FaqDuvidas> findAllByIdSetor(Integer idSetor);

	List<FaqDuvidas> findAllByCodEmprAndIdSetorOrderByCodInst(Integer idEmpresa, Integer idSetor);

	List<FaqDuvidas> findAllByIdSetorAndCodInst(Integer idSetor, Integer idInstituicao);

	List<FaqDuvidas> findAllByIdSetorOrderByCodInst(Integer idSetor);

	List<FaqDuvidas> findAllByIdSetorAndCodInstAndCodEmpr(Integer idSetor, Integer idInstituicao, Integer idEmpresa);
}
