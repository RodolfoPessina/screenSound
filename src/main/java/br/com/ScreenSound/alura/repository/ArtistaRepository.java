package br.com.ScreenSound.alura.repository;

import br.com.ScreenSound.alura.model.Artistas;
import br.com.ScreenSound.alura.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artistas, Long> {
    Optional<Artistas> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artistas a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscarMusicasPorArtista(String nome);
}
