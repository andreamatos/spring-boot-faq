package br.edu.cruzeirodosul.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.cruzeirodosul.domain.FaqDuvidas;
import br.edu.cruzeirodosul.dto.PageRequestDTO;
import br.edu.cruzeirodosul.interfaces.IFaqDuvidas;
import br.edu.cruzeirodosul.persistence.FaqDuvidasRepository;

@Service
public class FaqDuvidasService implements IFaqDuvidas{
	
	public static final int PAGE_SLICE = 5;
	private static final int ZERO = 0;
	public static final int UM = 1;
	
	@Autowired
	private FaqDuvidasRepository faqDuvidasRepository;
	
	@Override
	public FaqDuvidas save(FaqDuvidas faqDuvidas) {
        return faqDuvidasRepository.save(faqDuvidas);
	}
	
	@Override
	public PageRequestDTO<FaqDuvidas> findByIdUsuario(Integer page, String idUsuario) {
		Pageable pageable = PageRequest.of(page, PAGE_SLICE, Sort.by("idFaqDuvidas").ascending());
		return faqPage(faqDuvidasRepository.findByIdUsuarioAndStatus(idUsuario, UM, pageable));

	}
	
	PageRequestDTO<FaqDuvidas> faqPage(Page<FaqDuvidas> page) {
		PageRequestDTO<FaqDuvidas> pageFaqBoard = new PageRequestDTO<>();
        pageFaqBoard.setContent(page.getContent());
        pageFaqBoard.setTotalPages(page.getTotalPages());
        pageFaqBoard.setTotalElements(page.getNumber());
        pageFaqBoard.setNumberOfElements(page.getNumberOfElements());
        pageFaqBoard.setFirst(page.isFirst());
        pageFaqBoard.setLast(page.isLast());
        pageFaqBoard.setSize(page.getSize());
        pageFaqBoard.setNumber(page.getNumber());
		return pageFaqBoard;
	}

	@Override
	public FaqDuvidas findByIdFaqDuvidas(Long idFaqDuvidas) {
		return faqDuvidasRepository.findByIdFaqDuvidas(idFaqDuvidas);

	}

	public FaqDuvidas update(FaqDuvidas faqDuvidas) {
        return faqDuvidasRepository.save(faqDuvidas);
	}

	public List<FaqDuvidas> findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(Integer codEmpr, Integer idSetor, 
			Integer idFrequencia, Integer status, Integer codInst) {
		return faqDuvidasRepository.findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(codEmpr, idSetor, idFrequencia, status, codInst);
	}

	@Override
	public List<FaqDuvidas> findAllByOrderByFrequenciaDesc() {
		return faqDuvidasRepository.findAllByOrderByFrequenciaDesc();
	}

	public List<FaqDuvidas> findFaqDuvidasPorSetorEmpresaInstituicao(Integer idSetor, Integer idEmpresa, Integer idInstituicao) {
		List<FaqDuvidas> listaEmpresaComZeros = verificaEmpresaComZeros(idSetor);
		List<FaqDuvidas> listaInstituicaoComZeros = verificaInstituicaoComZeros(idSetor, idEmpresa);
		
		if(verificaListasPreenchidas(listaEmpresaComZeros, listaInstituicaoComZeros)) {
			return retornaTodosZero(idSetor);
		}else{
			return verificaQueryERetornaResultado(idSetor, idEmpresa, idInstituicao, listaEmpresaComZeros, listaInstituicaoComZeros);
		}
	}
	
	public boolean verificaListasPreenchidas(Collection<FaqDuvidas> listaEmpresaComZeros, 
			Collection<FaqDuvidas> listaInstituicaoComZeros) {
		boolean condicao= false;
		
		if (!listaEmpresaComZeros.isEmpty() && !listaInstituicaoComZeros.isEmpty()) {
			condicao = true;
		}else {
			condicao = false;
		}
		
		return condicao;
	}
	
	

	public List<FaqDuvidas> verificaQueryERetornaResultado(Integer idSetor, Integer idEmpresa, Integer idInstituicao, 
			Collection<FaqDuvidas> listaEmpresaComZeros, Collection<FaqDuvidas> listaInstituicaoComZeros) {
		if(listaEmpresaComZeros.isEmpty() && !listaInstituicaoComZeros.isEmpty()){
			return retornaTodasAsInstituicoes(idSetor, idEmpresa);
		}else if(!listaEmpresaComZeros.isEmpty() && listaInstituicaoComZeros.isEmpty()){
			return retornaTodasAsEmpresas(idSetor, idInstituicao);
		}else {
			return retornaSetorEmpresaInstituicaoPelaChave(idSetor, idEmpresa, idInstituicao);
		}
	}

	public List<FaqDuvidas> retornaSetorEmpresaInstituicaoPelaChave(Integer idSetor, Integer idEmpresa, Integer idInstituicao) {
		return faqDuvidasRepository.findAllByIdSetorAndCodInstAndCodEmpr(idSetor, idInstituicao, idEmpresa).stream().collect(Collectors.toList());
	}

	public List<FaqDuvidas> retornaTodasAsEmpresas(Integer idSetor, Integer idInstituicao) {
		return faqDuvidasRepository.findAllByIdSetorAndCodInst(idSetor, idInstituicao).stream().collect(Collectors.toList());
	}

	public List<FaqDuvidas> retornaTodasAsInstituicoes(Integer idSetor, Integer idEmpresa) {
		return faqDuvidasRepository.findAllByCodEmprAndIdSetorOrderByCodInst(idEmpresa, idSetor).stream().collect(Collectors.toList());
	}

	public List<FaqDuvidas> retornaTodosZero(Integer idSetor) {
		return faqDuvidasRepository.findAllByIdSetor(idSetor).stream().collect(Collectors.toList());
	}
	
	public  List<FaqDuvidas> verificaEmpresaComZeros(Integer idSetor) {
		return faqDuvidasRepository.findAllByIdSetorOrderByCodInst(idSetor)
			.stream()
			.filter(listaSetor -> listaSetor.getCodEmpr() == ZERO)
			.collect(Collectors.toList());
	}
	
	public List<FaqDuvidas> verificaInstituicaoComZeros(Integer idSetor, Integer idEmpresa) {
		return faqDuvidasRepository.findAllByCodEmprAndIdSetorOrderByCodInst(idEmpresa, idSetor)
		.stream()
		.filter(listaSetor -> listaSetor.getCodInst() == ZERO)
		.collect(Collectors.toList());
	}	

}
