package br.edu.cruzeirodosul.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.edu.cruzeirodosul.domain.FaqDuvidas;
import br.edu.cruzeirodosul.dto.PageRequestDTO;
import br.edu.cruzeirodosul.exception.CustomRestExceptionHandler;
import br.edu.cruzeirodosul.persistence.FaqDuvidasRepository;

@RunWith(MockitoJUnitRunner.class)
public class FaqDuvidasServiceTest {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	FaqDuvidasService service;
	
	@Mock
	FaqDuvidasRepository repository;

	private final Integer ACTUAL_PAGE = 0;
	public static final int PAGE_SLICE = 5;
	public static final int UM = 1;
	
	@Before
	public void testBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(service).setControllerAdvice(new CustomRestExceptionHandler()).build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void testContextLoads() {
		assertThat(this.service).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@DisplayName("Teste para salvar um FAQ")
	public void testSaveAndReturnOk() throws Exception{
		FaqDuvidas dto = new FaqDuvidas();
		dto.setIdFaqDuvidas(0L);
		dto.setStatus(1);
		dto.setResumo("Resumo teste");
		dto.setCodEmpr(13);
		dto.setIdSetor(1);
		dto.setIdUsuario("jenildo");
		dto.setPergunta("Pergunta teste");
		dto.setResposta("Reposta teste");
		dto.setFrequencia(1);

		given(this.repository.save(any(FaqDuvidas.class))).willReturn(dto);
		
		FaqDuvidas entity = service.save(dto);
		then(this.repository).should().save(entity);
	}
	
	@Test
	@DisplayName("Deve retornar Status 200")
	public void testFindByIdFaqDuvidasAndReturnOk() throws Exception{
		FaqDuvidas dto = new FaqDuvidas();
		dto.setIdFaqDuvidas(161L);
		given(this.repository.findByIdFaqDuvidas(dto.getIdFaqDuvidas())).willReturn(dto);
		FaqDuvidas entity = service.findByIdFaqDuvidas(dto.getIdFaqDuvidas());
		assertThat(entity.getIdFaqDuvidas()).isNotNull();
		then(this.repository).should().findByIdFaqDuvidas(entity.getIdFaqDuvidas());
	}
	
	@Test
	@DisplayName("Teste para atualizar um FAQ")
	public void testUpdate() throws Exception{
		FaqDuvidas dto = new FaqDuvidas();
		dto.setIdFaqDuvidas(181L);
		dto.setStatus(1);
		dto.setResumo("Resumo teste");
		dto.setCodEmpr(13);
		dto.setIdSetor(1);
		dto.setIdUsuario("jenildo");
		dto.setPergunta("Pergunta teste");
		dto.setResposta("Reposta teste");
		dto.setFrequencia(1);
		
		given(this.service.update(any(FaqDuvidas.class))).willReturn(dto);
		
		FaqDuvidas entity = service.update(dto);
		assertThat(entity).isNotNull();
		then(this.repository).should().save(entity);
	}
	
	@Test
	@DisplayName("Deve retornar Status 200")
	public void testFindAllByCodEmprAndIdSetorAndFrequencia() throws Exception{
		FaqDuvidas dto = new FaqDuvidas();
		List<FaqDuvidas> retornoDto = new ArrayList<FaqDuvidas>();
		dto.setIdFaqDuvidas(181L);
		dto.setStatus(1);
		dto.setResumo("Resumo teste");
		dto.setCodInst(1);
		dto.setCodEmpr(13);
		dto.setIdSetor(1);
		dto.setIdUsuario("jenildo");
		dto.setPergunta("Pergunta teste");
		dto.setResposta("Reposta teste");
		dto.setFrequencia(1);
		
		given(this.repository.findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(dto.getCodEmpr(), dto.getIdSetor(), dto.getFrequencia(), dto.getStatus(), dto.getCodInst())).
			willReturn(retornoDto);
		
		List<FaqDuvidas> entity = service.findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(dto.getCodEmpr(), dto.getIdSetor(), dto.getFrequencia(), dto.getStatus(), dto.getCodInst());
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(dto.getCodEmpr(), dto.getIdSetor(), dto.getFrequencia(), dto.getStatus(), dto.getCodInst());
	}
	
	
	@Test
	@DisplayName("Deve retornar dados da paginacao")
	public void testFaqDuvidasPageable() throws Exception{
		FaqDuvidas dto = new FaqDuvidas();
		
		List<FaqDuvidas> list = Collections.emptyList();
		Page<FaqDuvidas> paginacaoFaq = new PageImpl<>(list);
		
		dto.setCodEmpr(13);
		dto.setIdSetor(1);
		dto.setIdUsuario("jenildo");
		
		Pageable pageable = PageRequest.of(ACTUAL_PAGE, PAGE_SLICE, Sort.by("idFaqDuvidas").ascending());
		
		given(this.repository.findByIdUsuarioAndStatus(dto.getIdUsuario(), UM, pageable))
			.willReturn(paginacaoFaq);
		
		PageRequestDTO<FaqDuvidas>  entity = service.findByIdUsuario(ACTUAL_PAGE, dto.getIdUsuario());
		
		assertThat(entity).isNotNull();
		then(this.repository).should().findByIdUsuarioAndStatus(dto.getIdUsuario(), UM, pageable);
	
	}
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void testfindAllByOrderByFrequenciaDesc() throws Exception{
		List<FaqDuvidas> RetornoDto = new ArrayList<>();

		given(this.repository.findAllByOrderByFrequenciaDesc()).
				willReturn(RetornoDto);

		List<FaqDuvidas> entity = service.findAllByOrderByFrequenciaDesc();
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByOrderByFrequenciaDesc();
	}
	
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void retornaSetorEmpresaInstituicaoPelaChave() throws Exception{
		List<FaqDuvidas> RetornoDto = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodEmpr(2);
		dto.setIdSetor(1);
		dto.setCodInst(1);

		given(this.repository.findAllByIdSetorAndCodInstAndCodEmpr(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst())).willReturn(RetornoDto);

		List<FaqDuvidas> entity = service.retornaSetorEmpresaInstituicaoPelaChave(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst());
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByIdSetorAndCodInstAndCodEmpr(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst());
	}
	
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void retornaTodasAsEmpresas() throws Exception{
		List<FaqDuvidas> RetornoDto = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodEmpr(2);
		dto.setIdSetor(1);
		dto.setCodInst(1);

		given(this.repository.findAllByIdSetorAndCodInst(dto.getIdSetor(), dto.getCodInst())).willReturn(RetornoDto);

		List<FaqDuvidas> entity = service.retornaTodasAsEmpresas(dto.getIdSetor(), dto.getCodInst());
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByIdSetorAndCodInst(dto.getIdSetor(), dto.getCodInst());
	}
	
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void retornaTodasAsInstituicoes() throws Exception{
		List<FaqDuvidas> RetornoDto = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodEmpr(2);
		dto.setIdSetor(1);
		dto.setCodInst(1);

		given(this.repository.findAllByCodEmprAndIdSetorOrderByCodInst(dto.getCodEmpr(), dto.getCodInst())).willReturn(RetornoDto);

		List<FaqDuvidas> entity = service.retornaTodasAsInstituicoes(dto.getIdSetor(), dto.getCodEmpr());
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByCodEmprAndIdSetorOrderByCodInst(dto.getCodEmpr(), dto.getCodInst());
	}
	
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void retornaTodosZero() throws Exception{
		List<FaqDuvidas> RetornoDto = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodEmpr(2);
		dto.setIdSetor(1);
		dto.setCodInst(1);

		given(this.repository.findAllByIdSetor(dto.getIdSetor())).willReturn(RetornoDto);

		List<FaqDuvidas> entity = service.retornaTodosZero(dto.getIdSetor());
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByIdSetor(dto.getIdSetor());
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaEmpresaComZeros() throws Exception{
		List<FaqDuvidas> listaRetorno = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);

		listaRetorno = service.verificaEmpresaComZeros(dto.getCodInst());
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaInstituicaoComZeros() throws Exception{
		List<FaqDuvidas> listaRetorno = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);

		listaRetorno = service.verificaInstituicaoComZeros(dto.getCodInst(), dto.getCodEmpr());
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void findFaqDuvidasPorSetorEmpresaInstituicao() throws Exception{
		List<FaqDuvidas> listaRetorno = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);
		dto.setIdSetor(2);
		
		listaRetorno = service.findFaqDuvidasPorSetorEmpresaInstituicao(dto.getIdSetor(), dto.getCodEmpr(), dto.getCodInst());
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaQueryERetornaResultado() throws Exception{
		List<FaqDuvidas> listaEmpresaComZeros = new ArrayList<>();
		List<FaqDuvidas> listaInstituicaoComZeros = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);
		dto.setIdSetor(2);
		
		listaEmpresaComZeros.add(new FaqDuvidas());
		listaInstituicaoComZeros.add(new FaqDuvidas());
		
		service.verificaQueryERetornaResultado(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst(), listaEmpresaComZeros, listaInstituicaoComZeros);
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaQueryERetornaResultado1() throws Exception{
		List<FaqDuvidas> listaEmpresaComZeros = new ArrayList<>();
		List<FaqDuvidas> listaInstituicaoComZeros = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);
		dto.setIdSetor(2);
		
		listaInstituicaoComZeros.add(new FaqDuvidas());
		
		service.verificaQueryERetornaResultado(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst(), listaEmpresaComZeros, listaInstituicaoComZeros);
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaQueryERetornaResultado2() throws Exception{
		List<FaqDuvidas> listaEmpresaComZeros = new ArrayList<>();
		List<FaqDuvidas> listaInstituicaoComZeros = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);
		dto.setIdSetor(2);
		
		listaEmpresaComZeros.add(new FaqDuvidas());
		
		service.verificaQueryERetornaResultado(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst(), listaEmpresaComZeros, listaInstituicaoComZeros);
	}

	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaQueryERetornaResultado3() throws Exception{
		List<FaqDuvidas> listaEmpresaComZeros = new ArrayList<>();
		List<FaqDuvidas> listaInstituicaoComZeros = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();

		dto.setCodInst(2);
		dto.setCodEmpr(1);
		dto.setIdSetor(2);

		listaEmpresaComZeros.add(new FaqDuvidas(0L, dto.getIdSetor(), dto.getCodEmpr(), dto.getIdSetor(), 
				"resumo", "pergunta", "resposta", 1, "jenildo", 1));
		listaInstituicaoComZeros.add(new FaqDuvidas(0L, dto.getIdSetor(), dto.getCodEmpr(), dto.getIdSetor(), 
				"resumo", "pergunta", "resposta", 1, "jenildo", 1));
		
		service.verificaQueryERetornaResultado(dto.getCodEmpr(), dto.getIdSetor(), dto.getCodInst(), listaEmpresaComZeros, listaInstituicaoComZeros);
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaListasPreenchidas() throws Exception{
		List<FaqDuvidas> listaEmpresaComZeros = new ArrayList<>();
		List<FaqDuvidas> listaInstituicaoComZeros = new ArrayList<>();
		FaqDuvidas dto = new FaqDuvidas();
		listaEmpresaComZeros.add(new FaqDuvidas(0L, dto.getIdSetor(), dto.getCodEmpr(), dto.getIdSetor(), 
				"resumo", "pergunta", "resposta", 1, "jenildo", 1));
		listaInstituicaoComZeros.add(new FaqDuvidas(0L, dto.getIdSetor(), dto.getCodEmpr(), dto.getIdSetor(), 
				"resumo", "pergunta", "resposta", 1, "jenildo", 1));
		
		service.verificaListasPreenchidas(listaEmpresaComZeros, listaInstituicaoComZeros);
	}

}