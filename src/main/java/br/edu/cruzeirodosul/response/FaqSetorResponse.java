package br.edu.cruzeirodosul.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class FaqSetorResponse{
   
    private Integer idFaqSetor;    
    private String descSetor;

	public Integer getIdFaqSetor() {
		return idFaqSetor;
	}

	public void setIdFaqSetor(Integer idFaqSetor) {
		this.idFaqSetor = idFaqSetor;
	}

	public String getDescSetor() {
		return descSetor;
	}

	public void setDescSetor(String descSetor) {
		this.descSetor = descSetor;
	}



}
