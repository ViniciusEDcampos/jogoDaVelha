package com.example.jogodavelha;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // declarações de variaveis
    // totaliza jogadas realizas
    private int qtde;

    // identifica jogar atual
    private int jogador;
    // matriz para mapear as marcações
    private int matriz[][] = new int[3][3];
    // jogadores
    private Button b[] = new Button[9];

    //jogadores

    private String vencedor, jogador1, jogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        // iniciação de variaveis

        qtde = 1;
        jogador = 1;

        b[0] = findViewById(R.id.btn1);
        b[1] = findViewById(R.id.btn2);
        b[2] = findViewById(R.id.btn3);
        b[3] = findViewById(R.id.btn4);
        b[4] = findViewById(R.id.btn5);
        b[5] = findViewById(R.id.btn6);
        b[6] = findViewById(R.id.btn7);
        b[7] = findViewById(R.id.btn8);
        b[8] = findViewById(R.id.btn9);

        //setar(inicializar) valores para os botões
        //botao1

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[0], 0, 0);
                b[0].setEnabled(false);
            }
        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[1], 0, 1);
                b[1].setEnabled(false);
            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[2], 0, 2);
                b[2].setEnabled(false);
            }
        });
        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[3], 1, 0);
                b[3].setEnabled(false);
            }
        });
        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[4], 1, 1);
                b[4].setEnabled(false);
            }
        });
        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[5], 1, 2);
                b[5].setEnabled(false);
            }
        });
        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[6], 2, 0);
                b[6].setEnabled(false);
            }
        });
        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[7], 2, 1);
                b[7].setEnabled(false);
            }
        });
        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogada(b[8], 2, 2);
                b[8].setEnabled(false);
            }
        });

    }

    //metodo jogada
    private void jogada(Button btn, int linha, int coluna) {
        btn.setEnabled(true);
        if (jogador == 1) {
            matriz[linha][coluna] = 1;
            btn.setText("X");
            jogador = 2;
            vencedor = jogador1;

            //chamada ao metodo verificar se após jogadas temos um vencedor
            verificarJogada(1);
        } else {
            matriz[linha][coluna] = 2;
            btn.setText("●");
            jogador = 1;
            vencedor = jogador2;

            //chamada ao metodo verificar se após jogadas temos um vencedor
            verificarJogada(2);
        }
        //Contador de jogadas
        qtde++;
    }

    private void verificarJogada(int x) {
        if (vitoria(x)) {
            AlertDialog.Builder alertaVenceu = new AlertDialog.Builder(this);
            alertaVenceu.setTitle("Parabéns!!");
            alertaVenceu.setMessage("O Jogador " + vencedor + " venceu!!");
            alertaVenceu.setIcon(android.R.drawable.star_on);
            alertaVenceu.setPositiveButton("Ok", null);
            alertaVenceu.create().show();
            fimDoJogo();
        } else if (qtde == 9) {
            AlertDialog.Builder alertaVelha = new AlertDialog.Builder(this);
            alertaVelha.setTitle("Deu velha");
            alertaVelha.setMessage("Ninguém ganhou ou perdeu!!");
            alertaVelha.setIcon(android.R.drawable.star_on);
            alertaVelha.setPositiveButton("Ok", null);
            alertaVelha.create().show();
            fimDoJogo();
        }
    }

    private void fimDoJogo() {
        for (int i = 0; i < 9; i++) {
            b[i].setEnabled(false);
        }
    }

    private boolean vitoria(int x) {

        //verificacao se realmente houve vitoria
        for (int i = 0; i < matriz.length; i++) {

            //verificar os valores preenchidos na horizontal

            if (matriz[i][0] == x && matriz[i][1] == x && matriz[i][2] == x) {
                return true;
            }
            //verificar os valores preenchidos na vertical
            if (matriz[0][i] == x && matriz[1][i] == x && matriz[2][i] == x) {
                return true;
            }
        }

        //verificar os valores preenchidos na diagonal direita

        if (matriz[0][0] == x && matriz[1][1] == x && matriz[2][2] == x) {
            return true;
        }

        //verificar os valores preenchidos na diagonal esquerda

        if (matriz[0][2] == x && matriz[1][1] == x && matriz[2][0] == x) {
            return true;
        }

        return false;
    }
}


