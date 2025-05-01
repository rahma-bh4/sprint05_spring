package com.projet.voiture.entities;
import org.springframework.data.rest.core.config.Projection;
@Projection(name = "nomVoit", types = { Voiture.class })
public interface VoitureProjection {

	public String getNomVoiture();
}
