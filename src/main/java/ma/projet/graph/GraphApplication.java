package ma.projet.graph;

import ma.projet.graph.entities.Compte;
import ma.projet.graph.entities.TypeCompte;
import ma.projet.graph.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class GraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			// Création de comptes
			compteRepository.save(new Compte(null, 1000.0, LocalDate.of(2022, 2, 15), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 2500.0, LocalDate.of(2022, 3, 10), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 3200.0, LocalDate.of(2023, 4, 5), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 4200.0, LocalDate.of(2022, 4, 20), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 1500.0, LocalDate.of(2022, 5, 25), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 5400.0, LocalDate.of(2023, 6, 10), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 6300.0, LocalDate.of(2023, 7, 15), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 7200.0, LocalDate.of(2023, 8, 20), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 8200.0, LocalDate.of(2023, 9, 5), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 9100.0, LocalDate.of(2023, 10, 30), TypeCompte.COURANT));
		};
	}
}
