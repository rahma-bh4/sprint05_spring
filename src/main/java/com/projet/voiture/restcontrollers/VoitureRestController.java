package com.projet.voiture.restcontrollers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.voiture.dto.VoitureDTO;
import com.projet.voiture.entities.Voiture;
import com.projet.voiture.service.VoitureService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class VoitureRestController {

	 @Autowired
	    VoitureService voitureService;
	    
	    @RequestMapping(path="all", method = RequestMethod.GET)
	    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	    public List<VoitureDTO> getAllVoiture() {
	        return voitureService.getAllVoitures();
	    }
	    
	    @RequestMapping(value="/getbyid/{id}", method = RequestMethod.GET)
	    public VoitureDTO getVoitureById(@PathVariable("id") Long id) {
	        return voitureService.getVoiture(id);
	    }
	    
	    @RequestMapping(value="/addvoit", method = RequestMethod.POST)
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public VoitureDTO createVoiture(@RequestBody VoitureDTO voiture) {
	        return voitureService.saveVoiture(voiture);
	    }
	    
	    @RequestMapping(value="/updatevoit", method = RequestMethod.PUT)
	    public VoitureDTO updateVoiture(@RequestBody VoitureDTO voiture) {
	        return voitureService.updateVoiture(voiture);
	    }
	    
	    @RequestMapping(value="/delvoit/{id}", method = RequestMethod.DELETE)
	    public void deleteVoiture(@PathVariable("id") Long id) {
	        voitureService.deleteVoitureById(id);
	    }
	    
	    @RequestMapping(value="/voitsmar/{idMar}", method = RequestMethod.GET)
	    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	    public List<Voiture> getVoituresByMarId(@PathVariable("idMar") Long idMar) {
	        return voitureService.findByMarqueIdMar(idMar);
	    }

	
}
