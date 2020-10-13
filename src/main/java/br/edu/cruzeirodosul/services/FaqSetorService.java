package br.edu.cruzeirodosul.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.cruzeirodosul.domain.FaqSetor;
import br.edu.cruzeirodosul.interfaces.IFaqSetor;
import br.edu.cruzeirodosul.persistence.FaqSetorRepository;

@Service
public class FaqSetorService implements IFaqSetor{
	
	@Autowired
	private FaqSetorRepository faqSetorRepository;

	@Override
	public List<FaqSetor> findAll() {
		return faqSetorRepository.findAll();
	}
}
