package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cruzeirodosul.domain.FaqDuvidas;
import br.edu.cruzeirodosul.dto.PageRequestDTO;
import br.edu.cruzeirodosul.response.FaqDuvidaResponse;
import br.edu.cruzeirodosul.services.FaqDuvidasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/faqDuvidas")
@Api(value = "Este endpoint consiste no tratamento das perguntas e respostas", tags = { "FAQ" })
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
        @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
        @ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido"),
        @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
})

public class FaqDuvidasController {
	
	@Autowired
	private FaqDuvidasService faqDuvidasService;
	
	private static final int UM = 1;
	
	@PostMapping
    @ApiOperation(value = "Salvar Duvidas.", notes = "Salva dados na tabela FAQ_DUVIDAS", response = FaqDuvidas.class)
    public FaqDuvidas save(@RequestBody FaqDuvidas faqDuvidas) {
		return faqDuvidasService.save(faqDuvidas);
	}
	
	@GetMapping("/geral/pagina/{page}/usuario/{idUsuario}")
    @ApiOperation(value = "Pesquisar por pagina e usuario", notes = "Retorna dados da tabela FAQ_DUVIDAS por pagina e usuario.",
    response = FaqDuvidaResponse[].class)
    public ResponseEntity<PageRequestDTO<FaqDuvidas>> findByIdUsuario(
    														@PathVariable("page") Integer page,
															@PathVariable("idUsuario") String idUsuario){
		return ResponseEntity.ok().body(faqDuvidasService.findByIdUsuario(page,idUsuario));
	}
	
	@GetMapping("/{idFaqDuvidas}")
    @ApiOperation(value = "Pesquisar por idFaqDuvidas", notes = "Retorna dados da tabela FAQ_DUVIDAS por idFaqDuvidas.",
    response = FaqDuvidaResponse[].class)
    public ResponseEntity<FaqDuvidas> findSetorPorId(@PathVariable("idFaqDuvidas") Long idFaqDuvidas){
		return ResponseEntity.ok().body(faqDuvidasService.findByIdFaqDuvidas(idFaqDuvidas));
	}
	
	@PutMapping
    @ApiOperation(value = "Atualizar Duvidas.", notes = "Atualiza dados na tabela FAQ_DUVIDAS", response = FaqDuvidas.class)
	public FaqDuvidas updateFaq(@RequestBody FaqDuvidas faqDuvidas) {
		return faqDuvidasService.update(faqDuvidas);
	}
	
	@GetMapping("/empresa/{codEmpr}/setor/{idSetor}/frequencia/{idFrequencia}/codInst/{codInst}")
    @ApiOperation(value = "Pesquisar por empresa, setor e frequencia", notes = "Retorna dados da tabela FAQ_DUVIDAS por empresa, setor e frequencia.",
    response = FaqDuvidaResponse[].class)
    public ResponseEntity<List<FaqDuvidas>> findAllByCodEmprAndIdSetorAndIdFrequenciaAndCodInst(@PathVariable("codEmpr") Integer codEmpr,
																	@PathVariable("idSetor") Integer idSetor,
																	@PathVariable("idFrequencia") Integer idFrequencia,
																	@PathVariable("codInst") Integer codInst){
		return ResponseEntity.ok().body(faqDuvidasService.
				findAllByCodEmprAndIdSetorAndFrequenciaAndStatusAndCodInst(codEmpr, idSetor, idFrequencia, UM, codInst));
	}

	@GetMapping()
	@ApiOperation(value = "Pesquisar Order By Frequencia", notes = "Retorna dados da tabela FAQ_DUVIDAS Ordenada por Frequencia.",
			response = FaqDuvidaResponse[].class)
	public List<FaqDuvidas> getAll(){
		return faqDuvidasService.findAllByOrderByFrequenciaDesc();
	}
	
	@GetMapping("/instituicaoEmpresa/setor/{idSetor}/empresa/{idEmpresa}/instituicao/{idInstituicao}")
    @ApiOperation(value = "Pesquisa por empresa e setor", notes = "Retorna tabela FAQ_EMPR_SETOR por empresa e setor e instituicao",
    response = FaqDuvidaResponse[].class)
    public ResponseEntity<List<FaqDuvidas>> findInstituicaoBySetorAndEmpresaAndInstituicao
    																	(@PathVariable("idSetor") Integer idSetor, 
    																	 @PathVariable("idEmpresa") Integer idEmpresa,
   																	 	 @PathVariable("idInstituicao") Integer idInstituicao){
		return ResponseEntity.ok(faqDuvidasService.findFaqDuvidasPorSetorEmpresaInstituicao(idSetor, idEmpresa, idInstituicao));
	}
}
