/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pidemontecarloparalelo;

import java.util.Scanner;


/**
 *
 * @author rafael
 */
public class PiDeMonteCarloParalelo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número total de pontos: ");
        long numPontos = scanner.nextLong(); // Lê o número total de pontos    
        scanner.close();
        
        // Chama o método estático iniciar()
        iniciar(numPontos);
    }
    
    // Torna o método iniciar estático
    private static void iniciar(long numPontos){
        // Obtém o número ideal de threads baseado no número de núcleos disponíveis
        int numThreads = Runtime.getRuntime().availableProcessors(); 
        
        // Marca o tempo de início
        long startTime = System.currentTimeMillis();

        // Cria o gerenciador e inicia o cálculo
        GerenciadorParalelo gerenciador = new GerenciadorParalelo(numPontos, numThreads);
        gerenciador.iniciarCalculo();

        // Marca o tempo de término
        long endTime = System.currentTimeMillis();

        // Calcula o tempo de execução
        long duration = endTime - startTime; // em milissegundos
        System.out.println("Tempo total de execução: " + duration + " ms"); 
    }
}


