package br.edu.cruzeirodosul.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "FAQ_EMPR_SETOR",
        schema = "ACD")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class FaqEmprSetor{
   
	@Id
    @Column(name = "ID_FAQ_EMPR_SETOR")
    @SequenceGenerator(name = "ACD.FAQ_SEQ_EMPR_SETOR", sequenceName = "ACD.FAQ_SEQ_EMPR_SETOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACD.FAQ_SEQ_EMPR_SETOR")
	private Long idFaqEmprSetor;
    @Column(name = "ID_SETOR")
    private Long idSetor;    
    
    @Column(name = "COD_EMPR")
    private Long codEmpr;    

    @Column(name = "DESC_EMPR")
    private String descEmpr;    
     
    @Column(name = "COD_INST")
    private Long codInst;   
    
	public FaqEmprSetor() {
		super();
	}    
    
    @Override
	public int hashCode() {
        return Objects.hash(idFaqEmprSetor);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        FaqEmprSetor that = (FaqEmprSetor) o;
        return idFaqEmprSetor.equals(that.idFaqEmprSetor);
	}

}
