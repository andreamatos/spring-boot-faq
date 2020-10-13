package br.edu.cruzeirodosul.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class FaqUsuSetorResponse{
   
    private Integer ifFaqUsuSetor;    
    private Integer idSetor;
    private String nomeUsu;
    private Integer sitCada;
	public Integer getIfFaqUsuSetor() {
		return ifFaqUsuSetor;
	}
	public void setIfFaqUsuSetor(Integer ifFaqUsuSetor) {
		this.ifFaqUsuSetor = ifFaqUsuSetor;
	}
	public Integer getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}
	public String getNomeUsu() {
		return nomeUsu;
	}
	public void setNomeUsu(String nomeUsu) {
		this.nomeUsu = nomeUsu;
	}
	public Integer getSitCada() {
		return sitCada;
	}
	public void setSitCada(Integer sitCada) {
		this.sitCada = sitCada;
	}


}
