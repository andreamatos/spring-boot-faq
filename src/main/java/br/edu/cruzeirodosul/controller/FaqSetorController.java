package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cruzeirodosul.domain.FaqSetor;
import br.edu.cruzeirodosul.response.FaqSetorResponse;
import br.edu.cruzeirodosul.services.FaqSetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/faqSetor")
@Api(value = "Este endpoint consiste no tratamento das perguntas e respostas", tags = { "FAQ" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
        @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
        @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
        @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
})

public class FaqSetorController {
	
	@Autowired
	private FaqSetorService faqSetorService;
	
	@GetMapping()
	@ApiOperation(value = "Pesquisar Setores Cadastrados", notes = "Retorna dados da tabela FAQ_SETOR.",
			response = FaqSetorResponse[].class)
	public List<FaqSetor> getAll(){
		return faqSetorService.findAll();
	}
}
