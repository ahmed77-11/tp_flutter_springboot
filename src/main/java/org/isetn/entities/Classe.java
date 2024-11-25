package org.isetn.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codClass;
    private String nomClass;
    private int nbreEtud;

    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;

    @ManyToOne
    private Departement departement;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "matiere_classe",
        joinColumns = @JoinColumn(name = "classe_id"),
        inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private List<Matiere> matieres;

    
}
