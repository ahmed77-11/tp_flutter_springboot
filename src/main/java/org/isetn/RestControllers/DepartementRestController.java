package org.isetn.RestControllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.isetn.entities.Classe;
import org.isetn.entities.Departement;
import org.isetn.repository.ClasseRepository;
import org.isetn.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dep")

public class DepartementRestController {
	
	@Autowired
	private DepartementRepository departementRepository;

	@PostMapping("/add")
	public Departement add(@RequestBody Departement dep) {
		System.out.println(dep.toString());
		return departementRepository.save(dep);
	}

	@GetMapping("/all")
	public List<Departement> getAll() {
		return departementRepository.findAll();
	}
	
	@DeleteMapping("/delete")
	public void delete(@Param("id") Long id)
	{
		Departement d =  departementRepository.findById(id).get();
		departementRepository.delete(d);
	}
	
	@PutMapping("/update")
	public Departement update(@RequestBody Departement dep) {
		return departementRepository.save(dep);
	}
	
	@GetMapping("/getByIdDepartement/{idDep}")
	public List<Classe> findByDepartementIdDep(@PathVariable("idDep") Long idDep) {
	    Departement departement = departementRepository.findById(idDep).orElse(null);

	    if (departement != null) {
	        return departement.getClasses();
	    } else {
	        return Collections.emptyList();
	    }
	}

}
