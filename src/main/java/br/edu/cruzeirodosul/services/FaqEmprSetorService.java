package br.edu.cruzeirodosul.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.cruzeirodosul.domain.FaqEmprSetor;
import br.edu.cruzeirodosul.domain.FaqInstituicao;
import br.edu.cruzeirodosul.dto.FaqEmprSetorDTO;
import br.edu.cruzeirodosul.interfaces.IFaqEmprSetor;
import br.edu.cruzeirodosul.persistence.FaqEmprSetorRepository;
import br.edu.cruzeirodosul.persistence.FaqInstituicaoRepository;

@Service
public class FaqEmprSetorService implements IFaqEmprSetor{
	
	@Autowired
	private FaqEmprSetorRepository faqEmprSetorRepository;
	
	@Autowired
	private FaqInstituicaoRepository faqInstituicaoRepository;
	
	private static final int ZERO = 0;
	
	@Value(value = "")
	private String descricao;

		
	@Override
	public List<FaqEmprSetor> findByCodEmpr(Long idEmpresa) {
		return faqEmprSetorRepository.findByCodEmpr(idEmpresa);
	}

	@Override
	public  List<FaqEmprSetor> findAllSetor() {
		return faqEmprSetorRepository.findAll();
	}

	
	@Override
	public List<FaqEmprSetor> findEmpresaPorSetor(Long idSetor) {
		List<FaqEmprSetor> lista = faqEmprSetorRepository.findAllByIdSetorOrderByCodEmpr(idSetor);
		
		if (recuperarListaIgualZero(lista).isEmpty()) {
			return faqEmprSetorRepository.findAllByIdSetorOrderByCodEmpr(idSetor)
					.stream()
					.filter(distinctByKey(FaqEmprSetor::getCodEmpr))
					.collect(Collectors.toList());
		}else {
			return lista;
		}
	}

	public List<FaqEmprSetor> recuperarListaIgualZero(Collection<FaqEmprSetor> lista) {
		return lista.stream()
		.filter(listaSetor -> listaSetor.getCodEmpr() == ZERO)
		.filter(distinctByKey(FaqEmprSetor::getCodEmpr))
		.collect(Collectors.toList());
	}
	
	
	public List<FaqEmprSetorDTO> findInstituicaoPorEmpresaeSetor(Long idEmpresa, Long idSetor) {
		List<FaqEmprSetor> lista;

		List<FaqInstituicao> faqSetorList = faqInstituicaoRepository.findAll();
		List<FaqEmprSetorDTO> faqEmprSetorDTOList = new ArrayList<>();
		
		lista = findInstituicaoPorEmpresa(idEmpresa, idSetor);
		
		lista.stream().forEach((FaqEmprSetor listaPrincipal) -> buscarLista(faqEmprSetorDTOList, faqSetorList, listaPrincipal));
		
		return faqEmprSetorDTOList;
	}

	void buscarLista(List<FaqEmprSetorDTO> faqEmprSetorDTOList, List<FaqInstituicao> faqSetorList, FaqEmprSetor listaPrincipal) {
		criarLista(faqEmprSetorDTOList, faqSetorList, listaPrincipal);
	}

	public Collection<FaqEmprSetorDTO> criarLista(Collection<FaqEmprSetorDTO> faqEmprSetorDTOList, 
												Collection<FaqInstituicao> faqSetorList, 
													FaqEmprSetor listaPrincipal) {
		

		faqSetorList.stream().forEach((FaqInstituicao listaDescricao) -> {
				if (listaPrincipal.getCodInst().equals(listaDescricao.getIdFaqInstituicao())) {
					descricao = listaDescricao.getDescInst();
					faqEmprSetorDTOList.add(new FaqEmprSetorDTO(listaPrincipal.getIdFaqEmprSetor(), 
							listaPrincipal.getIdSetor(),  listaPrincipal.getCodEmpr(), listaPrincipal.getDescEmpr(), listaPrincipal.getCodInst(), descricao));				
				}
			});
		
		return faqEmprSetorDTOList;
	}
	
	@Override
	public List<FaqEmprSetor> findInstituicaoPorEmpresa(Long idEmpresa, Long idSetor) {
		List<FaqEmprSetor> lista = faqEmprSetorRepository
										.findAllByCodEmprAndIdSetorOrderByCodInst(idEmpresa, idSetor);
		
		lista = verificaListaComZeros(lista);
		
		if (lista.isEmpty()) {
			return verificaListaDiferenteZeros(idEmpresa, idSetor);
		}else {
			return lista;
		}
	}

	public List<FaqEmprSetor> verificaListaComZeros(Collection<FaqEmprSetor> lista) {
		return lista.stream()
				.filter(listaSetor -> listaSetor.getCodInst() == ZERO)
				.filter(distinctByKey(FaqEmprSetor::getCodInst))
				.collect(Collectors.toList());
	}
	
	public List<FaqEmprSetor> verificaListaDiferenteZeros(Long idEmpresa, Long idSetor) {
		return faqEmprSetorRepository.findAllByCodEmprAndIdSetorOrderByCodInst(idEmpresa, idSetor)
				.stream()
				.filter(distinctByKey(FaqEmprSetor::getCodInst))
				.collect(Collectors.toList());
	}
	
    public static <T> java.util.function.Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) { 
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
    } 

}
