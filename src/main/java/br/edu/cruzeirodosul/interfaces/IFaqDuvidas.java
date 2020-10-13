package br.edu.cruzeirodosul.interfaces;

import br.edu.cruzeirodosul.domain.FaqDuvidas;
import br.edu.cruzeirodosul.dto.PageRequestDTO;

import java.util.List;

public interface IFaqDuvidas {

	FaqDuvidas save(FaqDuvidas faqDuvidas);

	PageRequestDTO<FaqDuvidas> findByIdUsuario(Integer page, String idUsuario);

	FaqDuvidas findByIdFaqDuvidas(Long idFaqDuvidas);

	List<FaqDuvidas>findAllByOrderByFrequenciaDesc();
}
