package com.projet.voiture.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.projet.voiture.dto.VoitureDTO;
import com.projet.voiture.entities.Marque;
import com.projet.voiture.entities.Voiture;
public interface VoitureService {
	 VoitureDTO saveVoiture(VoitureDTO v);
	    VoitureDTO updateVoiture(VoitureDTO v);
	    void deleteVoiture(Voiture p);
	    void deleteVoitureById(Long id);
	    VoitureDTO getVoiture(Long id);
	    List<VoitureDTO> getAllVoitures();
		Page<Voiture> getAllVoituresParPage(int page, int size);
	    // Entity-specific methods that don't need to use DTOs
	    List<Voiture> findByNomVoiture(String nom);
	    List<Voiture> findByNomVoitureContains(String nom);
	    List<Voiture> findByNomPrix(String nom, Double prix);
	    List<Voiture> findByMarque(Marque marque);
	    List<Voiture> findByMarqueIdMar(Long id);
	    List<Voiture> findByOrderByNomVoitureAsc();
	    List<Voiture> trierVoituresNomsPrix();
	    List<Marque> getAllMarques();
	    // Conversion methods
	    VoitureDTO convertEntityToDto(Voiture v);
	    Voiture convertDtoToEntity(VoitureDTO voitureDto);
}
