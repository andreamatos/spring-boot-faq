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
@Getter
@Setter
@Table(name = "FAQ_INSTITUICAO",
        schema = "ACD")
@ToString
@AllArgsConstructor
public class FaqInstituicao{
   
	@Id
    @Column(name = "ID_FAQ_INSTITUICAO")
    @SequenceGenerator(name = "ACD.SEQ_FAQ_INSTITUICAO", sequenceName = "ACD.SEQ_FAQ_INSTITUICAO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACD.SEQ_FAQ_INSTITUICAO")
	private Long idFaqInstituicao;
 	
    @Column(name = "DESC_INST")
    private String descInst;    

	public FaqInstituicao() {
		super();
	}
    
    @Override
	public int hashCode() {
        return Objects.hash(idFaqInstituicao);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        FaqInstituicao that = (FaqInstituicao) o;
        return descInst.equals(that.descInst);
	}

}
