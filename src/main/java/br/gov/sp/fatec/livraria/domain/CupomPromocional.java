package br.gov.sp.fatec.livraria.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class CupomPromocional extends Cupom {

	private static final long serialVersionUID = 1L;


}
