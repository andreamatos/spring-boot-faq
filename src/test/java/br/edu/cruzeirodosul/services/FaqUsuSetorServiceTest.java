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

import br.edu.cruzeirodosul.domain.FaqSetor;
import br.edu.cruzeirodosul.domain.FaqUsuSetor;
import br.edu.cruzeirodosul.dto.FaqUsuSetorDTO;
import br.edu.cruzeirodosul.exception.CustomRestExceptionHandler;
import br.edu.cruzeirodosul.persistence.FaqSetorRepository;
import br.edu.cruzeirodosul.persistence.FaqUsuSetorRepository;

@RunWith(MockitoJUnitRunner.class)
public class FaqUsuSetorServiceTest {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	FaqUsuSetorService service;
	@Mock
	FaqUsuSetorService serviceMock;
	@Mock
	FaqUsuSetorRepository repository;
	@Mock
	private FaqSetorRepository faqSetorRepository;
	@Mock
	List<FaqSetor> faqSetorList;
	@Mock
	Collection<FaqUsuSetor> faqUsuSetorList;
	@Mock
	List<FaqUsuSetorDTO> faqUsuSetorDtoList;
	
	String userName = "jenildo";
	
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
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void testfindAllByOrderByFrequenciaDesc() throws Exception{
		List<FaqUsuSetor> RetornoDto = new ArrayList<>();

		given(this.repository.findAllByNomeUsuOrderByIdSetor("jenildo")).willReturn(RetornoDto);

		List<FaqUsuSetorDTO> entity = service.findAllByNomeUsu("jenildo");
		
		assertThat(entity).isNotNull();
		then(this.repository).should().findAllByNomeUsuOrderByIdSetor("jenildo");
	}
	
	@Test
	@DisplayName("Teste retorno dados ordenados por frequencia")
	public void inserirDescricao() throws Exception{
		List<FaqSetor> faqSetorList = new ArrayList<>();
		List<FaqUsuSetorDTO> faqUsuSetorDtoListInstancia = new ArrayList<>();
		FaqUsuSetor listSetorUsu = new FaqUsuSetor();
		//------------------FaqSetor------------------------		
		faqSetorList.add(new FaqSetor(1, "Biblioteca"));
		faqSetorList.add(new FaqSetor(2, "Administrativo"));
		faqSetorList.add(new FaqSetor(3, "CAA"));
		//---------------------------------------------------		

		//------------------FaqUsuSetor----------------------		
		listSetorUsu = new FaqUsuSetor(1, 1, "jenildo", 1);
		//---------------------------------------------------		
		
		List<FaqUsuSetorDTO> entity = service.criarLista(faqSetorList, faqUsuSetorDtoListInstancia, listSetorUsu);
		assertThat(entity).isNotNull();
	}

}