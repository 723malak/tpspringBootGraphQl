package ma.projet.graph.controllers;

import lombok.AllArgsConstructor;
import ma.projet.graph.entities.Compte;
import ma.projet.graph.entities.TypeCompte;
import ma.projet.graph.repositories.CompteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CompteController {
    private CompteRepository compteRepository;
    @QueryMapping
    public Page<Compte> getallComptesPagination(
            @Argument("type") TypeCompte type,
            @Argument("minSolde") Double minSolde,
            @Argument("first") int first , // Valeur par défaut
            @Argument("offset") int offset
    ) {
        Pageable pageable =  PageRequest.of(first, offset);
        if (type != null) {
            if (minSolde != null) {
                return compteRepository.findByTypeAndSoldeGreaterThanEqual(type, minSolde, pageable);
            } else {
                return compteRepository.findByType(type, pageable);
            }
        } else {
            return compteRepository.findAll(pageable);
        }
    }
    @QueryMapping
    public List<Compte> getComptes(
            @Argument("type") TypeCompte type,
            @Argument("minSolde") Double minSolde
    ) {
        if (type != null) {
            if (minSolde != null) {
                return compteRepository.findByTypeAndSoldeGreaterThanEqual(type, minSolde);
            } else {
                return compteRepository.findByType(type);
            }
        } else {
            return compteRepository.findAll();
        }
    }
    @QueryMapping
    public List<Compte> compteType(
            @Argument("type") TypeCompte type
    ) {
        return compteRepository.findByType(type);
    }

    @QueryMapping
    public Compte getCompteById(@Argument Long id){
        Compte compte =  compteRepository.findById(id).orElse(null);
        if(compte == null) throw new RuntimeException(String.format("Compte %s not found", id));
        else return compte;
    }

    @MutationMapping
    public Compte createCompte(@Argument Compte compte){
        return compteRepository.save(compte);
    }

    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count(); // Nombre total de comptes
        double sum = compteRepository.sumSoldes(); // Somme totale des soldes
        double average = count > 0 ? sum / count : 0; // Moyenne des soldes

        return Map.of(
                "count", count,
                "sum", sum,
                "average", average
        );
    }
    @MutationMapping
    public Boolean deleteCompte(@Argument Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException(String.format("Compte %s not found for deletion", id));
        }
    }
}
