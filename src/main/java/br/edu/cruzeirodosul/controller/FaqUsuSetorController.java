package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cruzeirodosul.dto.FaqUsuSetorDTO;
import br.edu.cruzeirodosul.response.FaqUsuSetorResponse;
import br.edu.cruzeirodosul.services.FaqUsuSetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/faqUsuSetor")
@Api(value = "Este endpoint consiste no tratamento das perguntas e respostas", tags = { "FAQ" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
        @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
        @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
        @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
})

public class FaqUsuSetorController {
	
	@Autowired
	private FaqUsuSetorService faqUsuSetorService;
	

	@GetMapping("/setor/nome/{userName}")
    @ApiOperation(value = "Pesquisa por nome de usuario", notes = "Retorna dados da tabela FAQ_USU_SETOR pelo nome de usuario",
    response = FaqUsuSetorResponse[].class)
    public ResponseEntity<List<FaqUsuSetorDTO>> getAllByUserName(@PathVariable("userName") String userName){
		return ResponseEntity.ok().body(faqUsuSetorService.findAllByNomeUsu(userName));
	}
}
