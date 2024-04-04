package br.com.ScreenSound.alura.principal;

import br.com.ScreenSound.alura.model.Artistas;
import br.com.ScreenSound.alura.model.Musica;
import br.com.ScreenSound.alura.model.TipoArtista;
import br.com.ScreenSound.alura.repository.ArtistaRepository;
import br.com.ScreenSound.alura.service.ConsultaChatGPT;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;
    Scanner sc = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibirMenu() {


        var opcao = -1;

        
        while(opcao != 6){
            System.out.println("digite\n 1-Pesquisar dados Artistas\n 2-Buscar musicas artistas \n 3-Listar Musicas 4-Cadastar Musicas\n 5- Cadastrar Artistas \n 6- sair");
            opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    PesquisarDadosArtistas();
                    break;
                case 2:
                    buscaMusicasPorArtista();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    CadastrarMusicas();
                    break;
                case 5:
                   CadastrarArtistas();
                    break;
                case 6:
                    System.out.println("encerrando aplicação");
                    break;
            }
        }
        
        
}

    private void PesquisarDadosArtistas() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = sc.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());
    }

    private void buscaMusicasPorArtista() {
        System.out.println("Buscar músicas de que artista? ");
        var nome = sc.nextLine();
        List<Musica> musicas = repositorio.buscarMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artistas> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void CadastrarMusicas() {
        System.out.println("Qual musica deseja cadastrar?");
        var nome = sc.nextLine();
        Optional<Artistas> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()){
            System.out.println("informe o titulo da musica: ");
            var nomeMusica = sc.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtistas(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado");
        }

    }

    private void CadastrarArtistas() {
        var opcao = "s";

            while(opcao.equalsIgnoreCase("s")){
            System.out.println("Qual artista deseja Cadastrar?");
            sc.nextLine();
            var nome = sc.nextLine();
            System.out.println("Qual o tipo? solo, dupla ou banda?");
            var tipo = sc.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artistas artistas = new Artistas(nome, tipoArtista);
            repositorio.save(artistas);
            System.out.println("deseja cadrastar novamente? S/N");
            opcao = sc.nextLine();
        } {
            System.out.println("end");
        }

    }
    }
