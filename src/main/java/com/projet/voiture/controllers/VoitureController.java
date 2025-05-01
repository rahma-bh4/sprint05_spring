package com.projet.voiture.controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.voiture.dto.VoitureDTO;
import com.projet.voiture.entities.Marque;
import com.projet.voiture.entities.Voiture;
import com.projet.voiture.service.VoitureService;

import jakarta.validation.Valid;

@Controller
public class VoitureController {

	@Autowired
	VoitureService voitureService;

	/*@RequestMapping("/ListeVoitures")
	public String listeVoitures(ModelMap modelMap) {
		List<Voiture> voits = voitureService.getAllVoitures();
		modelMap.addAttribute("voitures", voits);
		return "listeVoitures";
	}*/
	@RequestMapping("/ListeVoitures")
	public String listeVoitures(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		modelMap.addAttribute("voitures", voits);
		modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		
		return "listeVoitures";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		List<Marque> mars=voitureService.getAllMarques();
		modelMap.addAttribute("voiture", new Voiture());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("marques",mars);
		return "formVoiture";
	}
/*
	@RequestMapping("/saveVoiture")
	public String saveVoiture(@ModelAttribute("voiture") Voiture voiture, @RequestParam("dateCreation") String date,
			ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) throws ParseException {
		int currentPage;
		if(voiture.getIdVoiture()==null) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voiture.setDateCreation(dateCreation);

		Voiture saveVoiture = voitureService.saveVoiture(voiture);
		String msg = "Voiture enregistré avec Id " + saveVoiture.getIdVoiture();
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		currentPage = voits.getTotalPages()-1;
		modelMap.addAttribute("msg", msg);
		modelMap.addAttribute("voitures", voits);
		modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
		modelMap.addAttribute("page", currentPage);
		modelMap.addAttribute("size", size);
		}else 
			{
			currentPage=page;
			}
		return ("redirect:/ListeVoitures?page="+currentPage+"&size="+size);
	}*/
	
	@RequestMapping("/saveVoiture")
	public String saveVoiture(@Valid Voiture voiture,
			 BindingResult bindingResult, 
			 @RequestParam (name="page",defaultValue = "0") int page,
				@RequestParam (name="size", defaultValue = "2") int size)
			{
		int currentPage;
		boolean isNew=false;
			if (bindingResult.hasErrors()) return "formVoiture";
			if(voiture.getIdVoiture()==null) {
				isNew=true;
			}
			voitureService.saveVoiture(voitureService.convertEntityToDto(voiture));
			if(isNew) {
				Page<Voiture> voits=voitureService.getAllVoituresParPage(page,size);
				currentPage=voits.getTotalPages()-1;
			}
			else 
				currentPage=page;
			//return "formVoiture";
			return ("redirect:/ListeVoitures?page="+currentPage+"&size="+size);

			}


	@RequestMapping("/supprimerVoiture")
	public String supprimerVoiture(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		voitureService.deleteVoitureById(id);
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page,
				size);
				modelMap.addAttribute("voitures", voits);
				modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);
				return "listevoitures";
	}
	
	
	
	@RequestMapping("/modifierVoiture")
	public String editerVoiture(@RequestParam("id") Long id,ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size)
	{
	Voiture v= voitureService.convertDtoToEntity(voitureService.getVoiture(id));
	List<Marque> mars=voitureService.getAllMarques();
	modelMap.addAttribute("voiture", v);
	modelMap.addAttribute("mode", "edit");
	modelMap.addAttribute("marques", mars);
	modelMap.addAttribute("page", page);
	modelMap.addAttribute("size", size);
	return "formVoiture";
	}
	
	@RequestMapping("/updateVoiture")
	public String updateProduit(@ModelAttribute("voiture") Voiture voiture, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voiture.setDateCreation(dateCreation);

		voitureService.updateVoiture(voitureService.convertEntityToDto(voiture));
		List<VoitureDTO> voits = voitureService.getAllVoitures();
		modelMap.addAttribute("voitures", voits);
		return "listeVoitures";
	}

	@GetMapping(value = "/")
	public String welcome() {
	 return "index";
	}


	/*@RequestMapping("/modifierVoiture")
	public String editerVoiture(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		Voiture v = voitureService.getVoiture(id);
		modelMap.addAttribute("voiture", v);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		return "formVoiture";
	}*/
	

	/*@RequestMapping("/updateVoiture")
	public String updateVoiture(@ModelAttribute("voiture") Voiture voiture, @RequestParam("dateCreation") String date, ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voiture.setDateCreation(dateCreation);

		voitureService.updateVoiture(voiture);
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		modelMap.addAttribute("voitures", voits);
		return "listeVoitures";
	}*/
}
