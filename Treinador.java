package org.example;

public class Treinador {
    private String nome;
    private Pokemon[] equipe;
    private int indiceAtivo; // Diz qual pokemon está lutando
    private int pocoes;

    public Treinador(String nome, Pokemon[] equipe, int pocoes) {
        this.nome = nome;
        this.equipe = equipe;
        this.pocoes = pocoes;
        this.indiceAtivo = 0; // Começa com o primeiro Pokémon (índice 0)
    }

    public String getNome() { return nome; }

    public Pokemon getPokemonAtivo() {
        return equipe[indiceAtivo];
    }

    // Verifica se o treinador ainda tem algum Pokémon vivo na equipe
    public boolean temPokemonVivo() {
        for (int i = 0; i < equipe.length; i++) {
            if (equipe[i].isVivo()) return true;
        }
        return false;
    }

    public void usarPocao() {
        if (pocoes > 0) {
            System.out.println("\n" + this.nome + " usou uma Poção!");
            getPokemonAtivo().curar(50); // Poção cura 50 de HP
            pocoes--;
            System.out.println("Poções restantes: " + pocoes);
        } else {
            System.out.println("\n" + this.nome + " não tem mais poções! Perdeu o turno procurando na mochila.");
        }
    }

    public void mostrarEquipe() {
        System.out.println("\nEquipe de " + nome + ":");
        for (int i = 0; i < equipe.length; i++) {
            String status = equipe[i].isVivo() ? "Vivo" : "Desmaiado";
            System.out.println((i + 1) + ". " + equipe[i].getNome() + " - " + status);
        }
    }

    public boolean trocarPokemon(int escolha) {
        int indice = escolha - 1;
        if (indice >= 0 && indice < equipe.length && equipe[indice].isVivo()) {
            this.indiceAtivo = indice;
            System.out.println("\n" + this.nome + " trocou para " + equipe[indiceAtivo].getNome() + "!");
            return true; // Troca feita com sucesso
        } else {
            System.out.println("\nEscolha inválida ou Pokémon desmaiado!");
            return false; // Falhou na troca
        }
    }

    // Troca automática quando o Pokémon morre
    public void forcarTroca() {
        for (int i = 0; i < equipe.length; i++) {
            if (equipe[i].isVivo()) {
                this.indiceAtivo = i;
                System.out.println(this.nome + " enviou " + equipe[indiceAtivo].getNome() + " para a batalha!");
                break;
            }
        }
    }
}
