/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author rafael
 */
public class GerenciadorParalelo {

    private final long totalPontos; // Número total de pontos
    private final int numThreads; // Número de threads

    /**
     * Construtor para inicializar o gerenciador com o número total de pontos e threads.
     * @param totalPontos número total de pontos para o cálculo de Pi.
     * @param numThreads número de threads a serem usadas.
     */
    public GerenciadorParalelo(long totalPontos, int numThreads) {
        this.totalPontos = totalPontos;
        this.numThreads = numThreads;
    }

    /**
     * Método que inicia o cálculo de Pi usando threads.
     */
    public void iniciarCalculo() {
        System.out.println("Cálculo iniciado!");
        
        // Executor para gerenciar as threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long pontosPorThread = totalPontos / numThreads;

        // Submete tarefas às threads
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker(pontosPorThread);
            executor.submit(worker); // Submete o worker para execução
        }

        executor.shutdown(); // Finaliza o executor após submissão das tarefas

        // Aguarda a conclusão de todas as threads
        while (!executor.isTerminated()) {
            // Bloqueia até que todas as tarefas terminem
        }

        System.out.println("Cálculo completo!");
    }    
}
