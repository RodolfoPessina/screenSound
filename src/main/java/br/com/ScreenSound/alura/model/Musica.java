package br.com.ScreenSound.alura.model;

import jakarta.persistence.*;


@Entity(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Artistas artista;

    public Musica() {
    }

    public Musica(String nomeMusica) {

        this.titulo = nomeMusica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artistas getArtistas() {
        return artista;
    }

    public void setArtistas(Artistas artistas) {
        this.artista = artistas;
    }

    @Override
    public String toString() {
        return
                "Música='" + titulo + '\'' +
                        ", artista=" + artista.getNome();
    }
}



