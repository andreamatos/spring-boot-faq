package br.edu.cruzeirodosul.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.cruzeirodosul.domain.FaqSetor;
import br.edu.cruzeirodosul.domain.FaqUsuSetor;
import br.edu.cruzeirodosul.dto.FaqUsuSetorDTO;
import br.edu.cruzeirodosul.interfaces.IFaqUsuSetor;
import br.edu.cruzeirodosul.persistence.FaqSetorRepository;
import br.edu.cruzeirodosul.persistence.FaqUsuSetorRepository;

@Service
public class FaqUsuSetorService implements IFaqUsuSetor{
	
	@Autowired
	private FaqUsuSetorRepository faqUsuSetorRepository;
	@Autowired
	private FaqSetorRepository faqSetorRepository;
	
	@Override
	public List<FaqUsuSetorDTO> findAllByNomeUsu(String userName) {
		List<FaqSetor> faqSetorList = faqSetorRepository.findAll();
		List<FaqUsuSetor> faqUsuSetorList = faqUsuSetorRepository.findAllByNomeUsuOrderByIdSetor(userName);
		List<FaqUsuSetorDTO> faqUsuSetorDtoList = new ArrayList<>();
		
		faqUsuSetorList.stream().forEach((FaqUsuSetor listSetorUsu) -> buscaDescricao(faqSetorList, faqUsuSetorDtoList, listSetorUsu));
		
		return faqUsuSetorDtoList;
	}

	void buscaDescricao(List<FaqSetor> faqSetorList, List<FaqUsuSetorDTO> faqUsuSetorDtoList, FaqUsuSetor listSetorUsu) {
		criarLista(faqSetorList, faqUsuSetorDtoList, listSetorUsu);
	}

	public List<FaqUsuSetorDTO> criarLista(List<FaqSetor> faqSetorList, List<FaqUsuSetorDTO> faqUsuSetorDtoList,FaqUsuSetor listSetorUsu) {
		for(int i = 0; i < faqSetorList.size(); i++) {
			if(faqSetorList.get(i).getIdFaqSetor().equals(listSetorUsu.getIdSetor())) {
				faqUsuSetorDtoList.add(new FaqUsuSetorDTO(listSetorUsu.getIdFaqUsuSetor(),listSetorUsu.getIdSetor(), 
					faqSetorList.get(i).getDescSetor(),listSetorUsu.getNomeUsu(), listSetorUsu.getSitCada()));
			}
		}
		return faqUsuSetorDtoList;
	}

}
