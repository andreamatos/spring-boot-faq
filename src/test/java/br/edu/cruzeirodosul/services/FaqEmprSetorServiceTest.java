package br.edu.cruzeirodosul.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.edu.cruzeirodosul.domain.FaqEmprSetor;
import br.edu.cruzeirodosul.domain.FaqInstituicao;
import br.edu.cruzeirodosul.domain.FaqSetor;
import br.edu.cruzeirodosul.dto.FaqEmprSetorDTO;
import br.edu.cruzeirodosul.exception.CustomRestExceptionHandler;
import br.edu.cruzeirodosul.persistence.FaqEmprSetorRepository;
import br.edu.cruzeirodosul.persistence.FaqInstituicaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class FaqEmprSetorServiceTest {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	FaqEmprSetorService service;
	
	@Mock
	FaqInstituicaoRepository instituicaoRepository;
	
	@Mock
	FaqEmprSetorRepository repository;
	
	@Mock
	List<FaqSetor> faqSetorList;

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
	@DisplayName("Teste retorno dados por empresa")
	public void testFindByCodEmpr() throws Exception{
		List<FaqEmprSetor> retornoDto = new ArrayList<>();

		given(this.repository.findByCodEmpr(13L)).
				willReturn(retornoDto);

		List<FaqEmprSetor> entity = service.findByCodEmpr(13L);
		assertThat(entity).isNotNull();
		then(this.repository).should().findByCodEmpr(13L);
	}
	
	@Test
	@DisplayName("Teste retorno dados geral")
	public void testFindAll() throws Exception{
		List<FaqEmprSetor> retornoDto = new ArrayList<>();

		given(this.repository.findAll()).willReturn(retornoDto);

		List<FaqEmprSetor> entity = service.findAllSetor();
		assertThat(entity).isNotNull();
		then(this.repository).should().findAll();
	}
	
	@Test
	@DisplayName("Teste retorno por setor 1")
	public void findEmpresaPorSetor() throws Exception{
		List<FaqEmprSetor> entity = service.findEmpresaPorSetor(1L);
		assertThat(entity).isNullOrEmpty();
	}
	
	@Test
	@DisplayName("Teste retorno por setor 2")
	public void findEmpresaPorSetorDifZero() throws Exception{
		List<FaqEmprSetor> entity = service.findEmpresaPorSetor(2L);
		assertThat(entity).isNullOrEmpty();
	}
	
	@Test
	@DisplayName("Teste retorno por empresa 2 e setor 2")
	public void findInstituicaoPorEmpresaeSetor() throws Exception{
		List<FaqEmprSetorDTO> entity = service.findInstituicaoPorEmpresaeSetor(2L, 2L);
		assertThat(entity).isNullOrEmpty();
	}

	@Test
	@DisplayName("Teste inclusão de descrição na instituicao")
	public void buscarLista() throws Exception{
		
		FaqEmprSetor listaPrincipal = new FaqEmprSetor();
		List<FaqInstituicao> faqSetorList = new ArrayList<>();
		Collection<FaqEmprSetorDTO> faqEmprSetorDTOList = new ArrayList<>();
		
		//------------------FaqEmprSetor------------------------		
		listaPrincipal = new FaqEmprSetor(5L, 2L, 2L, "COLEGIO", 2L);
//		lista.add(new FaqEmprSetor(7L, 29L, 1L, "FSG-CAXIAS", 2L));
//		lista.add(new FaqEmprSetor(8L, 29L, 1L, "COLEGIO", 2L));
//		lista.add(new FaqEmprSetor(14L, 0L, 2L, "COLEGIO", 1L));
		//------------------------------------------------------	

		//------------------FaqInstituicao------------------------		
//		faqSetorList.add(new FaqInstituicao(0L, "GERAL"));
		faqSetorList.add(new FaqInstituicao(2L, "COLEGIO CRUZEIRO DO SUL"));
//		faqSetorList.add(new FaqInstituicao(10L, "UNICID"));
//		faqSetorList.add(new FaqInstituicao(30L, "COLEGIO MERE"));
//		faqSetorList.add(new FaqInstituicao(18L, "UDF"));
//		faqSetorList.add(new FaqInstituicao(1L, "UNIVERSIDADE CRUZEIRO DO SUL"));
		//------------------------------------------------------	
		
		Collection<FaqEmprSetorDTO> retornoLista = service.criarLista(faqEmprSetorDTOList, faqSetorList, listaPrincipal);
		assertThat(retornoLista).isNotNull();
	}
	
	@Test
	@DisplayName("Teste descrição da instituicao não encontrada")
	public void buscarListaDiferente() throws Exception{
		
		FaqEmprSetor listaPrincipal = new FaqEmprSetor();
		List<FaqInstituicao> faqSetorList = new ArrayList<>();
		Collection<FaqEmprSetorDTO> faqEmprSetorDTOList = new ArrayList<>();
		
		//------------------FaqEmprSetor------------------------		
		listaPrincipal = new FaqEmprSetor(5L, 2L, 2L, "COLEGIO", 3L);
//		lista.add(new FaqEmprSetor(7L, 29L, 1L, "FSG-CAXIAS", 2L));
//		lista.add(new FaqEmprSetor(8L, 29L, 1L, "COLEGIO", 2L));
//		lista.add(new FaqEmprSetor(14L, 0L, 2L, "COLEGIO", 1L));
		//------------------------------------------------------	

		//------------------FaqInstituicao------------------------		
//		faqSetorList.add(new FaqInstituicao(0L, "GERAL"));
		faqSetorList.add(new FaqInstituicao(2L, "COLEGIO CRUZEIRO DO SUL"));
//		faqSetorList.add(new FaqInstituicao(10L, "UNICID"));
//		faqSetorList.add(new FaqInstituicao(30L, "COLEGIO MERE"));
//		faqSetorList.add(new FaqInstituicao(18L, "UDF"));
//		faqSetorList.add(new FaqInstituicao(1L, "UNIVERSIDADE CRUZEIRO DO SUL"));
		//------------------------------------------------------	
		
		Collection<FaqEmprSetorDTO> retornoLista = service.criarLista(faqEmprSetorDTOList, faqSetorList, listaPrincipal);
		assertThat(retornoLista).isNotNull();
	}
	
	@Test
	@DisplayName("Teste retorno dados por empresa")
	public void faqEmprSetorDTOList() throws Exception{
		
		FaqEmprSetor listaPrincipal = new FaqEmprSetor();
		List<FaqInstituicao> faqSetorList = new ArrayList<>();
		List<FaqEmprSetorDTO> faqEmprSetorDTOList = new ArrayList<>();
		
		//------------------FaqEmprSetor------------------------		
		listaPrincipal = new FaqEmprSetor(5L, 2L, 2L, "COLEGIO", 2L);
//		lista.add(new FaqEmprSetor(7L, 29L, 1L, "FSG-CAXIAS", 2L));
//		lista.add(new FaqEmprSetor(8L, 29L, 1L, "COLEGIO", 2L));
//		lista.add(new FaqEmprSetor(14L, 0L, 2L, "COLEGIO", 1L));
		//------------------------------------------------------	

		//------------------FaqInstituicao------------------------		
//		faqSetorList.add(new FaqInstituicao(0L, "GERAL"));
		faqSetorList.add(new FaqInstituicao(2L, "COLEGIO CRUZEIRO DO SUL"));
//		faqSetorList.add(new FaqInstituicao(10L, "UNICID"));
//		faqSetorList.add(new FaqInstituicao(30L, "COLEGIO MERE"));
//		faqSetorList.add(new FaqInstituicao(18L, "UDF"));
//		faqSetorList.add(new FaqInstituicao(1L, "UNIVERSIDADE CRUZEIRO DO SUL"));
		//------------------------------------------------------	
		
		service.buscarLista(faqEmprSetorDTOList, faqSetorList, listaPrincipal);
	}
	
	@Test
	@DisplayName("Teste lista instituicao zerada")
	public void verificaListaComZeros() throws Exception{
		List<FaqEmprSetor> listaPrincipal = new ArrayList<>();
		List<FaqEmprSetor> listaRetorno = new ArrayList<>();

		//------------------FaqEmprSetor------------------------		
		listaPrincipal.add(new FaqEmprSetor(0L, 0L, 0L, "COLEGIO", 0L));

		listaRetorno = service.verificaListaComZeros(listaPrincipal);
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao != zerada")
	public void verificaListaComZerosPassandoValorDiferenteDeZeros() throws Exception{
		List<FaqEmprSetor> listaPrincipal = new ArrayList<>();
		List<FaqEmprSetor> listaRetorno = new ArrayList<>();

		//------------------FaqEmprSetor------------------------		
		listaPrincipal.add(new FaqEmprSetor(1L, 1L, 1L, "COLEGIO", 1L));

		listaRetorno = service.verificaListaComZeros(listaPrincipal);
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao != zerada")
	public void recuperarListaIgualZero() throws Exception{
		List<FaqEmprSetor> listaPrincipal = new ArrayList<>();
		List<FaqEmprSetor> listaRetorno = new ArrayList<>();

		//------------------FaqEmprSetor------------------------		
		listaPrincipal.add(new FaqEmprSetor(1L, 1L, 1L, "COLEGIO", 1L));

		listaRetorno = service.recuperarListaIgualZero(listaPrincipal);
		assertThat(listaRetorno).isNotNull();
	}
	
	@Test
	@DisplayName("Teste lista instituicao diferente de zeros")
	public void verificaListaDiferenteZeros() throws Exception{
		service.verificaListaDiferenteZeros(29L, 2L);
	}
}