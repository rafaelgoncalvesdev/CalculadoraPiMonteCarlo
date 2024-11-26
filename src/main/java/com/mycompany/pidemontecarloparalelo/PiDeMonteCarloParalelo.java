/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pidemontecarloparalelo;


/**
 *
 * @author rafael
 */
public class PiDeMonteCarloParalelo {
    public static void main(String[] args) {
        long totalPontos = 1_000_000; // Exemplo: número total de pontos
        int numThreads = 4; // Exemplo: número de threads

        // Cria e inicia o gerenciador
        GerenciadorParalelo gerenciador = new GerenciadorParalelo(totalPontos, numThreads);
        gerenciador.iniciarCalculo();
    }
}
