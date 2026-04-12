package org.example;

public class Pokemon {
    private String nome;
    private String tipo;
    private int nivel;
    private int vidaAtual;
    private int vidaMaxima;
    private int forca;
    private int defesa;
    private Ataque[] ataques;

    // Construtor com as novas características
    public Pokemon(String nome, String tipo, int nivel, int vidaMaxima, int forca, int defesa,
                   Ataque atq1, Ataque atq2, Ataque atq3, Ataque atq4) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.forca = forca;
        this.defesa = defesa;
        this.ataques = new Ataque[]{atq1, atq2, atq3, atq4};
    }

    public void mostrarAtaques() {
        System.out.println("Ataques disponíveis:");
        for (int i = 0; i < ataques.length; i++) {
            System.out.println((i + 1) + ". " + ataques[i].getNome() + " (Poder: " + ataques[i].getDano() + ")");
        }
    }

    public Ataque escolherAtaque(int indice) {
        if (indice >= 1 && indice <= 4) { return ataques[indice - 1]; }
        return null;
    }

    // O dano agora considera a Força do inimigo e a Defesa deste Pokémon
    public void receberDano(int danoAtaque, int forcaInimiga) {
        int danoReal = (danoAtaque + forcaInimiga) - this.defesa;
        if (danoReal <= 0) danoReal = 1; // Garante mínimo de 1 de dano

        this.vidaAtual -= danoReal;
        if (this.vidaAtual < 0) this.vidaAtual = 0;

        System.out.println("=> " + this.nome + " recebeu " + danoReal + " de dano!");
    }

    // Método para a poção
    public void curar(int quantidade) {
        this.vidaAtual += quantidade;
        if (this.vidaAtual > this.vidaMaxima) {
            this.vidaAtual = this.vidaMaxima;
        }
        System.out.println("=> " + this.nome + " recuperou vida! HP atual: " + this.vidaAtual);
    }

    public boolean isVivo() { return this.vidaAtual > 0; }

    public void mostrarStatus() {
        System.out.println("Lv" + nivel + " " + nome + " (" + tipo + ") [HP: " + vidaAtual + "/" + vidaMaxima + "]");
    }

    public String getNome() { return nome; }
    public int getForca() { return forca; }
}
