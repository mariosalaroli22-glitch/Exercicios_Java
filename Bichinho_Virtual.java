public class Bichinho_Virtual {
    // Requisitos Privados
    private String nome;
    private int fome;
    private int energia;
    private int felicidade;
    private boolean doente;

    // Construtor
    public Bichinho_Virtual(String nome) {
        this.nome = nome;
        this.fome = 50;       // Valores iniciais equilibrados
        this.energia = 80;
        this.felicidade = 60;
        this.doente = false;
    }

    // Método auxiliar para garantir os limites entre 0 e 100 (Anti-Bug)
    private int travarValor(int valor) {
        return Math.max(0, Math.min(100, valor));
    }

    // Método para atualizar o estado de saúde baseado na fome
    private void verificarSaude() {
        if (this.fome >= 100) {
            this.doente = true;
            System.out.println("⚠️ " + nome + " ficou doente de tanta fome e precisa comer!");
        } else if (this.fome < 100 && this.doente) {
            this.doente = false;
            System.out.println("❤️ " + nome + " se recuperou da doença!");
        }
    }

    // Ações do Bichinho
    public void alimentar() {
        System.out.println("\n🍛 Alimentando " + nome + "...");
        this.fome = travarValor(this.fome - 30);
        this.felicidade = travarValor(this.felicidade + 5);
        verificarSaude();
    }

    public void brincar() {
        // Bônus: Se estiver doente (fome = 100), se recusa a brincar
        if (this.doente) {
            System.out.println("\n❌ " + nome + " está doente com muita fome e se recusou a brincar!");
            return;
        }

        System.out.println("\n⚽ Brincando com " + nome + "...");
        this.felicidade = travarValor(this.felicidade + 20);
        this.energia = travarValor(this.energia - 15);
        this.fome = travarValor(this.fome + 10);
        verificarSaude();
    }

    public void dormir() {
        System.out.println("\n💤 " + nome + " foi dormir...");
        this.energia = 100; // Volta diretamente a 100
        this.fome = travarValor(this.fome + 20);
        verificarSaude();
    }

    public boolean estaFeliz() {
        return this.felicidade >= 70;
    }

    public void status() {
        System.out.println("---------------------------------");
        System.out.println("Nome: " + this.nome);
        System.out.println("Fome: " + this.fome + "/100");
        System.out.println("Energia: " + this.energia + "/100");
        System.out.println("Felicidade: " + this.felicidade + "/100");
        System.out.println("Está Feliz? " + (estaFeliz() ? "Sim! 😄" : "Não. 😐"));
        System.out.println("Está Doente? " + (this.doente ? "Sim 🤒" : "Não 😎"));
        System.out.println("---------------------------------");
    }

    // Main: Um dia na vida do bichinho
    public static void main(String[] args) {
        Bichinho_Virtual pet = new Bichinho_Virtual("Tamagotchi");

        System.out.println("--- Começo do Dia ---");
        pet.status();

        // Bichinho acorda com energia, mas quer brincar
        pet.brincar();
        pet.status();

        // Brinca de novo até gastar energia e aumentar a fome
        pet.brincar();
        pet.brincar();
        pet.status();

        // Forçando o bônus: vamos fazê-lo dormir para aumentar a fome até o limite
        pet.dormir();
        pet.dormir();
        pet.status(); // Aqui a fome deve bater 100 e ele ficará doente

        // Tentando brincar com ele doente
        pet.brincar();

        // Curando o bichinho com comida
        pet.alimentar();
        pet.status();

        // Deixando ele feliz no final do dia
        pet.brincar();
        pet.status();
    }
}