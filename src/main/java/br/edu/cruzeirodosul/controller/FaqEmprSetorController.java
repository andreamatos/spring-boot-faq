package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cruzeirodosul.domain.FaqEmprSetor;
import br.edu.cruzeirodosul.dto.FaqEmprSetorDTO;
import br.edu.cruzeirodosul.response.FaqEmprSetorResponse;
import br.edu.cruzeirodosul.services.FaqEmprSetorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/faqEmprSetor")
@Api(value = "Este endpoint consiste no tratamento da parametrizacao", tags = { "FAQ" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
        @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
        @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
        @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
})

public class FaqEmprSetorController {
	
	@Autowired
	private FaqEmprSetorService faqEmprSetorService;

	@GetMapping("/geral")
    @ApiOperation(value = "Pesquisa geral", notes = "Retorna tabela FAQ_EMPR_SETOR",
    response = FaqEmprSetorResponse[].class)
    public ResponseEntity<List<FaqEmprSetor>> findSetorPorId(){
		return ResponseEntity.ok(faqEmprSetorService.findAllSetor());
	}
	
	@GetMapping("/geral/{idEmpresa}")
    @ApiOperation(value = "Pesquisa por empresa", notes = "Retorna tabela FAQ_EMPR_SETOR por Empresa",
    response = FaqEmprSetorResponse[].class)
    public ResponseEntity<List<FaqEmprSetor>> findSetorPorId(@PathVariable("idEmpresa") Long idEmpresa){
		return ResponseEntity.ok(faqEmprSetorService.findByCodEmpr(idEmpresa));
	}
	
	@GetMapping("/empresaSetor/{idSetor}")
    @ApiOperation(value = "Pesquisa por setor", notes = "Retorna tabela FAQ_EMPR_SETOR por Setor",
    response = FaqEmprSetorResponse[].class)
    public ResponseEntity<List<FaqEmprSetor>> findEmpresaPorSetor(@PathVariable("idSetor") Long idSetor){
		return ResponseEntity.ok(faqEmprSetorService.findEmpresaPorSetor(idSetor));
	}
	
	@GetMapping("/instituicaoEmpresa/{idEmpresa}/setor/{idSetor}")
    @ApiOperation(value = "Pesquisa por empresa e setor", notes = "Retorna tabela FAQ_EMPR_SETOR por empresa e setor",
    response = FaqEmprSetorResponse[].class)
    public ResponseEntity<List<FaqEmprSetorDTO>> findInstituicaoPorEmpresa(@PathVariable("idEmpresa") Long idEmpresa, 
    																	@PathVariable("idSetor") Long idSetor){
		return ResponseEntity.ok(faqEmprSetorService.findInstituicaoPorEmpresaeSetor(idEmpresa, idSetor));
	}
}
