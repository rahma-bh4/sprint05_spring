package com.projet.voiture.dto;
import java.util.Date;
import com.projet.voiture.entities.Marque;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoitureDTO {

	 private Long idVoiture;
	    private String nomVoiture;
	    private Double prixVoiture;
	    private Date dateCreation;
	    private String email;
	    private Marque marque;
}
