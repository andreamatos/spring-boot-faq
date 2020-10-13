package br.edu.cruzeirodosul.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class FaqEmprSetorDTO{
   
	private Long idFaqEmprSetor;
    private Long idSetor;    
    private Long codEmpr;    
    private String descEmpr;    
    private Long codInst; 
    private String descInst;
    
	public Long getIdFaqEmprSetor() {
		return idFaqEmprSetor;
	}
	public void setIdFaqEmprSetor(Long idFaqEmprSetor) {
		this.idFaqEmprSetor = idFaqEmprSetor;
	}
	public Long getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}
	public Long getCodEmpr() {
		return codEmpr;
	}
	public void setCodEmpr(Long codEmpr) {
		this.codEmpr = codEmpr;
	}
	public String getDescEmpr() {
		return descEmpr;
	}
	public void setDescEmpr(String descEmpr) {
		this.descEmpr = descEmpr;
	}
	public Long getCodInst() {
		return codInst;
	}
	public void setCodInst(Long codInst) {
		this.codInst = codInst;
	}
	public String getDescInst() {
		return descInst;
	}
	public void setDescInst(String descInst) {
		this.descInst = descInst;
	}

}
