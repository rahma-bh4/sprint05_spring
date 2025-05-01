package com.projet.voiture.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.projet.voiture.dto.VoitureDTO;
import com.projet.voiture.entities.Marque;
import com.projet.voiture.entities.Voiture;
import com.projet.voiture.repos.MarqueRepository;
import com.projet.voiture.repos.VoitureRepository;
@Service
public class VoitureServiceImpl  implements VoitureService{
	 @Autowired
	    VoitureRepository voitureRepository;
	    
	    @Autowired
	    ModelMapper modelMapper;
		@Autowired
		MarqueRepository marqueRepository;
		
	    @Override
	    public VoitureDTO saveVoiture(VoitureDTO v) {
	        Voiture voiture = convertDtoToEntity(v);
	        Voiture savedVoiture = voitureRepository.save(voiture);
	        return convertEntityToDto(savedVoiture);
	    }

	    @Override
	    public VoitureDTO updateVoiture(VoitureDTO v) {
	        Voiture voiture = convertDtoToEntity(v);
	        Voiture updatedVoiture = voitureRepository.save(voiture);
	        return convertEntityToDto(updatedVoiture);
	    }
	    @Override
		public List<Marque> getAllMarques() {
			
			return marqueRepository.findAll();
		}

	    @Override
	    public void deleteVoiture(Voiture v) {
	        voitureRepository.delete(v);
	    }
	    @Override
		public Page<Voiture> getAllVoituresParPage(int page, int size) {
			return voitureRepository.findAll(PageRequest.of(page, size));

		}

	    @Override
	    public void deleteVoitureById(Long id) {
	        voitureRepository.deleteById(id);
	    }

	    @Override
	    public VoitureDTO getVoiture(Long id) {
	        Voiture voiture = voitureRepository.findById(id).get();
	        return convertEntityToDto(voiture);
	    }

	    @Override
	    public List<VoitureDTO> getAllVoitures() {
	        List<Voiture> voitures = voitureRepository.findAll();
	        return voitures.stream()
	                .map(this::convertEntityToDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<Voiture> findByNomVoiture(String nom) {
	        return voitureRepository.findByNomVoiture(nom);
	    }

	    @Override
	    public List<Voiture> findByNomVoitureContains(String nom) {
	        return voitureRepository.findByNomVoitureContains(nom);
	    }

	    @Override
	    public List<Voiture> findByNomPrix(String nom, Double prix) {
	        return voitureRepository.findByNomPrix(nom, prix);
	    }

	    @Override
	    public List<Voiture> findByMarque(Marque marque) {
	        return voitureRepository.findByMarque(marque);
	    }

	    @Override
	    public List<Voiture> findByMarqueIdMar(Long id) {
	        return voitureRepository.findByMarqueIdMar(id);
	    }

	    @Override
	    public List<Voiture> findByOrderByNomVoitureAsc() {
	        return voitureRepository.findByOrderByNomVoitureAsc();
	    }

	    @Override
	    public List<Voiture> trierVoituresNomsPrix() {
	        return voitureRepository.trierVoituresNomsPrix();
	    }

	    @Override
	    public VoitureDTO convertEntityToDto(Voiture v) {
	        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	        VoitureDTO voitureDTO = modelMapper.map(v, VoitureDTO.class);
	        return voitureDTO;
	    }

	    @Override
	    public Voiture convertDtoToEntity(VoitureDTO voitureDto) {
	        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	        Voiture voiture = modelMapper.map(voitureDto, Voiture.class);
	        return voiture;
	    }

}