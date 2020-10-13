package br.edu.cruzeirodosul.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "FAQ_SETOR",
        schema = "ACD")
@ToString
@AllArgsConstructor
public class FaqSetor{
	
	@Id
    @Column(name = "ID_FAQ_SETOR")
    private Integer idFaqSetor;    
	
    @Column(name = "DESC_SETOR")
    private String descSetor;

    public FaqSetor() {
		super();
	}    
    
    @Override
	public int hashCode() {
        return Objects.hash(idFaqSetor);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        FaqSetor that = (FaqSetor) o;
        return idFaqSetor.equals(that.idFaqSetor);
	}

}
