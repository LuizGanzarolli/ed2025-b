import java.util.Random;

public class JogoDaVelha {
    protected static final int JOGADOR_X = 1, JOGADOR_O = -1;
    protected static final int CELULA_VAZIA = 0;
    protected int[][] tabuleiro;
    protected int jogadorAtual;
    protected int tamanhoTabuleiro;

    public JogoDaVelha(int tamanho) {
        this.tamanhoTabuleiro = tamanho;
        this.tabuleiro = new int[tamanho][tamanho];
    }

    public void limparTabuleiro() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = CELULA_VAZIA;
            }
        }

        jogadorAtual = JOGADOR_X;
    }

    public int[] obterPosicaoAleatoria() {
        Random random = new Random();
        int[] posicao = new int[2];

        posicao[0] = random.nextInt(tamanhoTabuleiro);
        posicao[1] = random.nextInt(tamanhoTabuleiro);

        return posicao;
    }

    public void preencherTabuleiro() {
        int vencedor = 2;
        jogadorAtual = JOGADOR_X;

        while (vencedor == 2) {
            int[] posicao = obterPosicaoAleatoria();
            vencedor = verificarVencedor();

            if (vencedor == 2) {
                if (tabuleiro[posicao[0]][posicao[1]] == CELULA_VAZIA) {
                    tabuleiro[posicao[0]][posicao[1]] = jogadorAtual;
                    String tabuleiroStr = toString();
                    System.out.println(tabuleiroStr);
                    System.out.println();
                    jogadorAtual *= -1;
                } else {
                    posicao = obterPosicaoAleatoria();
                }
            }
        }
    }

    public boolean verificarVencedor(int jogador) {
        // Verifica linhas
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            int somaLinha = 0;
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                somaLinha += tabuleiro[i][j];
            }

            if (somaLinha == jogador * tamanhoTabuleiro) {
                return true;
            }
        }

        // Verifica colunas
        for (int j = 0; j < tamanhoTabuleiro; j++) {
            int somaColuna = 0;
            for (int i = 0; i < tamanhoTabuleiro; i++) {
                somaColuna += tabuleiro[i][j];
            }

            if (somaColuna == jogador * tamanhoTabuleiro) {
                return true;
            }
        }

        // Verifica diagonal principal
        int somaDiagonalPrincipal = 0;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            somaDiagonalPrincipal += tabuleiro[i][i];
        }

        if (somaDiagonalPrincipal == jogador * tamanhoTabuleiro) {
            return true;
        }

        // Verifica diagonal secundária
        int somaDiagonalSecundaria = 0;
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            somaDiagonalSecundaria += tabuleiro[i][tamanhoTabuleiro - 1 - i];
        }

        if (somaDiagonalSecundaria == jogador * tamanhoTabuleiro) {
            return true;
        }

        return false;
    }

    public int verificarVencedor() {
        if (verificarVencedor(JOGADOR_X)) {
            return JOGADOR_X;
        } else if (verificarVencedor(JOGADOR_O)) {
            return JOGADOR_O;
        }

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j] == CELULA_VAZIA) {
                    return 2;
                }
            }
        }

        return 0;
    }

    public String toString() {
        StringBuilder tabuleiroStr = new StringBuilder();

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                if (tabuleiro[i][j] == JOGADOR_X) {
                    tabuleiroStr.append(" X ");
                } else if (tabuleiro[i][j] == JOGADOR_O) {
                    tabuleiroStr.append(" O ");
                } else {
                    tabuleiroStr.append("   ");
                }

                if (j < tamanhoTabuleiro - 1) {
                    tabuleiroStr.append(" | ");
                }
            }

            if (i < tamanhoTabuleiro - 1) {
                tabuleiroStr.append("\n---|---|---\n");
            }
        }

        return tabuleiroStr.toString();
    }
}