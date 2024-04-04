package br.com.ScreenSound.alura;

import br.com.ScreenSound.alura.principal.Principal;
import br.com.ScreenSound.alura.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;

	public AluraApplication(ArtistaRepository repositorio) {
		this.repositorio = repositorio;
	}

	public static void main(String[] args) {

		SpringApplication.run(AluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibirMenu();

	}
}
