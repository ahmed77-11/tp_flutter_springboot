package org.isetn.repository;

import java.util.List;

import org.isetn.entities.Classe;
import org.isetn.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
	
	@Query("SELECT DISTINCT d FROM Departement d LEFT JOIN FETCH d.classes c WHERE c.departement.idDep = :idDep")
    List<Departement> findByClasses_DepartementIdDep(@Param("idDep") Long idDep);
}
