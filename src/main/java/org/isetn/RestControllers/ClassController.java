package org.isetn.RestControllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.isetn.entities.Classe;
import org.isetn.entities.Etudiant;
import org.isetn.entities.Matiere;
import org.isetn.repository.ClasseRepository;
import org.isetn.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("class")

public class ClassController {
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private MatiereRepository matiereRepository;

	@PostMapping("/add")
	public Classe add(@RequestBody Classe classe) {
		System.out.println(classe.toString());
		return classeRepository.save(classe);
	}

	@GetMapping("/all")
	public List<Classe> getAll() {
		return classeRepository.findAll();
	}
	
	@DeleteMapping("/delete")
	public void delete(@Param("id") Long id)
	{
		Classe c =  classeRepository.findById(id).get();
		classeRepository.delete(c);
	}
	
	@PutMapping("/update")
	public Classe update(@RequestBody Classe classe) {
		return classeRepository.save(classe);
	}
	
	
	
	@GetMapping("/getByDepId/{idDep}")
	public List<Classe> findByDepartementIdDep(@PathVariable("iddDep") Long idDep) {
	    return classeRepository.findByDepartementIdDep(idDep);
	}
	
	
	@PostMapping("/addMatiereToClasse")
    public ResponseEntity<String> addMatiereToClasse(
            @RequestParam("classeId") Long classeId,
            @RequestParam("matiereId") Long matiereId) {

        try {
            // Retrieve the classe and matiere from their respective repositories
            Classe classe = classeRepository.findById(classeId).orElse(null);
            Matiere matiere = matiereRepository.findById(matiereId).orElse(null);

            if (classe != null && matiere != null) {
                // Add the matiere to the classe's matieres list
                classe.getMatieres().add(matiere);
                // Update the classe in the database
                classeRepository.save(classe);

                return ResponseEntity.ok("Matiere added to Classe successfully.");
            } else {
                return ResponseEntity.badRequest().body("Classe or Matiere not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
	
	@GetMapping("/getMatieresByClassId/{classId}")
    public List<Matiere> getMatieresByClassId(@PathVariable("classId") Long classId) {
        Classe classe = classeRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        return classe.getMatieres();
    }
	
	
	 

}
