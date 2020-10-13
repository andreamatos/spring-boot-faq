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
@Table(name = "FAQ_USU_SETOR",
        schema = "ACD")
@ToString
@AllArgsConstructor
public class FaqUsuSetor{
   
	@Id
    @Column(name = "ID_FAQ_USU_SETOR")
    private Integer idFaqUsuSetor;    
	
    @Column(name = "ID_SETOR")
    private Integer idSetor;

    @Column(name = "NOME_USU")
    private String nomeUsu;

    @Column(name = "SIT_CADA")
    private Integer sitCada;

	public FaqUsuSetor() {
		super();
	}   
	
    @Override
	public int hashCode() {
        return Objects.hash(idFaqUsuSetor);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        FaqUsuSetor that = (FaqUsuSetor) o;
        return idFaqUsuSetor.equals(that.idFaqUsuSetor);
	}

}
