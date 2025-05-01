package com.projet.voiture;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.projet.voiture.entities.Voiture;
import com.projet.voiture.repos.VoitureRepository;
import org.springframework.boot.test.context.SpringBootTest;
import com.projet.voiture.entities.*;
@SpringBootTest
class VoituresApplicationTests {
	@Autowired
	private VoitureRepository voitureRepository;

	@Test
	public void  testCreateVoiture() {
		Voiture voit=new Voiture("BMW X3",300000.0,new Date(),"BMW");
		voitureRepository.save(voit);
	}
	@Test
	public void testFindVoiture()
	{
	Voiture v = voitureRepository.findById(1L).get(); 
	System.out.println(v);
	}
	@Test
	public void testUpdateVoiture()
	{
	Voiture v = voitureRepository.findById(1L).get();
	v.setPrixVoiture(45000.0);
	voitureRepository.save(v);
	}
	@Test
	public void testDeleteVoiture()
	{
	voitureRepository.deleteById(1L);;
	}
	 
	@Test
	public void testListerTousVoitures()
	{
	List<Voiture> prods = voitureRepository.findAll();
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	
	}
	@Test
	public void testFindVoitureByNom()
	{
	List<Voiture> v = voitureRepository.findByNomVoiture("Renault clio 4"); 
	for (Voiture p : v)
	{
	System.out.println(p);
	}
	
	}
	@Test
	public void testfindByNomPrix()
	{
	List<Voiture> prods = voitureRepository.findByNomPrix("Renault clio 4", 1000.0);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void findByMarqueIdMar()
	{
	List<Voiture> prods = voitureRepository.findByMarqueIdMar(1L);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	 }
	@Test
	public void testfindByMarque()
	{
	Marque cat = new Marque();
	cat.setIdMar(1L);
	List<Voiture> prods = voitureRepository.findByMarque(cat);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testfindByOrderByNomVoitureAsc()
	{
	List<Voiture> prods =
	voitureRepository.findByOrderByNomVoitureAsc();
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testTrierVoituresNomsPrix()
	{
	List<Voiture> prods = voitureRepository.trierVoituresNomsPrix();
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}

}
