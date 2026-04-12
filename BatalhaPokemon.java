package org.example;

import java.util.Random;
import java.util.Scanner;

import java.util.Random;
import java.util.Scanner;

public class BatalhaPokemon {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 1. Criando a Equipe do Jogador (2 Pokémons)
        Pokemon pikachu = new Pokemon("Pikachu", "Elétrico", 10, 100, 25, 10,
                new Ataque("Choque do Trovão", 20), new Ataque("Ataque Rápido", 15),
                new Ataque("Cauda de Ferro", 25), new Ataque("Investida", 10));

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Planta", 10, 110, 20, 15,
                new Ataque("Chicote de Vinha", 20), new Ataque("Semente Sanguesuga", 15),
                new Ataque("Investida", 10), new Ataque("Folha Navalha", 25));

        Pokemon[] equipeJogador = {pikachu, bulbasaur};
        Treinador jogador = new Treinador("Você (Ash)", equipeJogador, 2); // 2 poções

        // 2. Criando a Equipe do Inimigo (2 Pokémons)
        Pokemon charmander = new Pokemon("Charmander", "Fogo", 10, 100, 22, 12,
                new Ataque("Brasa", 20), new Ataque("Arranhão", 15),
                new Ataque("Lança-Chamas", 30), new Ataque("Encarar", 0));

        Pokemon squirtle = new Pokemon("Squirtle", "Água", 10, 120, 18, 20,
                new Ataque("Revólver de Água", 20), new Ataque("Mordida", 15),
                new Ataque("Cabeçada", 20), new Ataque("Bolhas", 10));

        Pokemon[] equipeInimigo = {charmander, squirtle};
        Treinador inimigo = new Treinador("Rival Gary", equipeInimigo, 1); // 1 poção

        System.out.println("=========================================");
        System.out.println(" Batalha: " + jogador.getNome() + " VS " + inimigo.getNome());
        System.out.println("=========================================");

        // 3. Loop do Jogo
        while (jogador.temPokemonVivo() && inimigo.temPokemonVivo()) {

            Pokemon meuPoke = jogador.getPokemonAtivo();
            Pokemon rivalPoke = inimigo.getPokemonAtivo();

            System.out.println("\n-------------------------");
            rivalPoke.mostrarStatus();
            meuPoke.mostrarStatus();
            System.out.println("-------------------------");

            // --- MENU DO JOGADOR ---
            System.out.println("Escolha uma ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Poção");
            System.out.println("3. Trocar Pokémon");
            System.out.print("Sua escolha: ");
            int acao = scanner.nextInt();

            if (acao == 1) {
                // ATACAR
                meuPoke.mostrarAtaques();
                System.out.print("Escolha o ataque (1-4): ");
                int escolhaAtq = scanner.nextInt();
                Ataque atq = meuPoke.escolherAtaque(escolhaAtq);

                if (atq != null) {
                    System.out.println("\n" + meuPoke.getNome() + " usou " + atq.getNome() + "!");
                    rivalPoke.receberDano(atq.getDano(), meuPoke.getForca());
                } else {
                    System.out.println("\nAtaque inválido! " + meuPoke.getNome() + " perdeu o turno.");
                }

            } else if (acao == 2) {
                // USAR POÇÃO
                jogador.usarPocao();

            } else if (acao == 3) {
                // TROCAR POKEMON
                jogador.mostrarEquipe();
                System.out.print("Escolha o número do Pokémon: ");
                int escolhaTroca = scanner.nextInt();
                jogador.trocarPokemon(escolhaTroca);
            } else {
                System.out.println("Ação inválida! Perdeu o turno.");
            }

            // Verifica se o inimigo morreu neste turno
            if (!inimigo.getPokemonAtivo().isVivo()) {
                System.out.println("O " + inimigo.getPokemonAtivo().getNome() + " inimigo desmaiou!");
                if (inimigo.temPokemonVivo()) {
                    inimigo.forcarTroca();
                } else {
                    break; // Fim de jogo, inimigo não tem mais pokémons
                }
            }

            // --- TURNO DO INIMIGO ---
            System.out.println("\nTurno do " + inimigo.getNome() + "...");
            Pokemon rivalPokeAtualizado = inimigo.getPokemonAtivo(); // Pega o atual caso ele tenha trocado

            // Inimigo sempre ataca de forma aleatória
            int escolhaInimigo = random.nextInt(4) + 1;
            Ataque atqInimigo = rivalPokeAtualizado.escolherAtaque(escolhaInimigo);

            System.out.println(rivalPokeAtualizado.getNome() + " inimigo usou " + atqInimigo.getNome() + "!");
            jogador.getPokemonAtivo().receberDano(atqInimigo.getDano(), rivalPokeAtualizado.getForca());

            // Verifica se o seu pokémon morreu
            if (!jogador.getPokemonAtivo().isVivo()) {
                System.out.println("Seu " + jogador.getPokemonAtivo().getNome() + " desmaiou!");
                if (jogador.temPokemonVivo()) {
                    System.out.println("Você precisa trocar de Pokémon!");
                    jogador.mostrarEquipe();
                    System.out.print("Escolha o número do Pokémon vivo: ");
                    int novaEscolha = scanner.nextInt();
                    // Loop simples para forçar o jogador a escolher um pokemon vivo
                    while (!jogador.trocarPokemon(novaEscolha)) {
                        System.out.print("Escolha um Pokémon VIVO: ");
                        novaEscolha = scanner.nextInt();
                    }
                }
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            scanner.nextLine();
        }

        // 4. Resultado
        System.out.println("\n=========================================");
        if (jogador.temPokemonVivo()) {
            System.out.println("  VITÓRIA! Você derrotou o " + inimigo.getNome() + "!");
        } else {
            System.out.println("  DERROTA! Você perdeu a batalha.");
        }
        System.out.println("=========================================");

        scanner.close();
    }
}