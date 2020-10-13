package br.edu.cruzeirodosul.interfaces;

import java.util.List;

import br.edu.cruzeirodosul.dto.FaqUsuSetorDTO;

public interface IFaqUsuSetor {

	List<FaqUsuSetorDTO> findAllByNomeUsu(String userName);

}
