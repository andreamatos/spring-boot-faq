package br.edu.cruzeirodosul.interfaces;

import java.util.List;

import br.edu.cruzeirodosul.domain.FaqEmprSetor;

public interface IFaqEmprSetor {

	List<FaqEmprSetor> findByCodEmpr(Long idEmpresa);

	List<FaqEmprSetor> findAllSetor();

	List<FaqEmprSetor> findEmpresaPorSetor(Long idSetor);

	List<FaqEmprSetor> findInstituicaoPorEmpresa(Long idEmpresa, Long idSetor);

}
