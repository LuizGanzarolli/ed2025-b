import java.util.Scanner;

public class JogaJogoDaVelha {
    public static void main(String[] args) {

        
        Scanner scanner = new Scanner(System.in);

        int resp = 1;

        System.out.println("Bem-vindo ao Jogo da Velha!");

        while (resp == 1) {

            int dimensao = 0;

            
            while (dimensao < 3 || dimensao % 2 == 0) {
                System.out.println("Digite o número da dimensão do tabuleiro (mínimo 3x3): ");
                dimensao = scanner.nextInt();

                if (dimensao < 3) {
                    System.out.println("O tamanho informado é muito pequeno, por favor, informe um valor maior.");
                } else if (dimensao % 2 == 0) {
                    System.out.println("Por favor, informe um valor ímpar.");
                }
            }

            JogoDaVelha jogo = new JogoDaVelha(dimensao); 
            jogo.limparTabuleiro();

            
            int vencedor = 2;  
            while (vencedor == 2) {
                
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println(jogo.toString());
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                
                int linha, coluna;
                do {
                    System.out.println("Jogador " + (jogo.jogadorAtual == JogoDaVelha.JOGADOR_X ? "X" : "O") + ", informe a linha e a coluna da sua jogada:");
                    System.out.print("Linha (0 a " + (dimensao - 1) + "): ");
                    linha = scanner.nextInt();
                    System.out.print("Coluna (0 a " + (dimensao - 1) + "): ");
                    coluna = scanner.nextInt();
                } while (linha < 0 || linha >= dimensao || coluna < 0 || coluna >= dimensao || jogo.tabuleiro[linha][coluna] != JogoDaVelha.CELULA_VAZIA);

                
                jogo.tabuleiro[linha][coluna] = jogo.jogadorAtual;

                
                vencedor = jogo.verificarVencedor();

                if (vencedor == JogoDaVelha.JOGADOR_X) {
                    System.out.println("Jogador X venceu o jogo!");
                } else if (vencedor == JogoDaVelha.JOGADOR_O) {
                    System.out.println("Jogador O venceu o jogo!");
                } else if (vencedor == 0) {
                    System.out.println("O jogo deu empate!");
                }

               
                jogo.jogadorAtual *= -1;
            }

           
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(jogo.toString());
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

           
            System.out.println("Você deseja jogar novamente?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            resp = scanner.nextInt();

            while (resp != 1 && resp != 2) {
                System.out.println("Por favor, digite um número válido.");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                resp = scanner.nextInt();
            }
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }
}
