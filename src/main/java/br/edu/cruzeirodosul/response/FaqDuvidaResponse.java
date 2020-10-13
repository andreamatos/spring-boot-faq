package br.edu.cruzeirodosul.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class FaqDuvidaResponse{
    private Long idFaqDuvidas;
    private Long status;
    private String resumo;
    private Long empresa;
    private Long setor;
    private Long usuario;
    private String pergunta;
    private String resposta;
    private Long frequencia;
	public Long getIdFaqDuvidas() {
		return idFaqDuvidas;
	}
	public void setIdFaqDuvidas(Long idFaqDuvidas) {
		this.idFaqDuvidas = idFaqDuvidas;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public Long getSetor() {
		return setor;
	}
	public void setSetor(Long setor) {
		this.setor = setor;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public Long getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Long frequencia) {
		this.frequencia = frequencia;
	}
    
}
