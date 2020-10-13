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
@Table(name = "FAQ_DUVIDAS",
        schema = "ACD")
@ToString
@AllArgsConstructor
public class FaqDuvidas{
   
	@Id
    @Column(name = "ID_FAQ_DUVIDAS")
    @SequenceGenerator(name = "ACD.SEQ_FAQ_DUVIDAS", sequenceName = "ACD.SEQ_FAQ_DUVIDAS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACD.SEQ_FAQ_DUVIDAS")
	private Long idFaqDuvidas;

    @Column(name = "ID_SETOR")
    private Integer idSetor;    

    @Column(name = "COD_EMPR")
    private Integer codEmpr;    
    
    @Column(name = "COD_INST")
    private Integer codInst;

    @Column(name = "RESUMO")
    private String resumo;    
    
    @Column(name = "PERGUNTA")
    private String pergunta;    
    
    @Column(name = "RESPOSTA", length = 2000)
    private String resposta;
    
    @Column(name = "FREQUENCIA")
    private Integer frequencia;

    @Column(name = "ID_USUARIO")
    private String idUsuario;   

    @Column(name = "STATUS")
    private Integer status;    

	public FaqDuvidas() {
		super();
	}
	
    @Override
	public int hashCode() {
        return Objects.hash(idFaqDuvidas);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        FaqDuvidas that = (FaqDuvidas) o;
        return status.equals(that.status);
	}

}
