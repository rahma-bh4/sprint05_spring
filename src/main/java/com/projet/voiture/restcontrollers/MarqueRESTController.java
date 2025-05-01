package com.projet.voiture.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.voiture.entities.Marque;
import com.projet.voiture.repos.MarqueRepository;
@RestController
@RequestMapping("/api/mar")
@CrossOrigin("*")
public class MarqueRESTController {

	@Autowired
	MarqueRepository marqueRepository;
	@RequestMapping(method=RequestMethod.GET)
	public List<Marque> getAllMarques()
	{
	return marqueRepository.findAll();
	}
	@RequestMapping(value="/mar/{id}",method = RequestMethod.GET)
	public Marque getMarqueById(@PathVariable("id") Long id) {
		return marqueRepository.findById(id).get();
	}
	
	
	
}
