/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author rafael
 */

/**
 * Gerencia o cálculo de Pi de forma paralela, distribuindo as tarefas entre várias threads.
 */
public class GerenciadorParalelo {
    
    private final long numPontos; // Número total de pontos
    private final int numThreads; // Número de threads a serem usadas

    /**
     * Construtor que inicializa o número total de pontos e o número de threads.
     * 
     * @param totalPontos número total de pontos a serem utilizados no cálculo.
     * @param numThreads número de threads a serem usadas.
     */
    public GerenciadorParalelo(long totalPontos, int numThreads) {
        this.numPontos = totalPontos;
        // Se o número de threads for inválido (menor que 1), usa o número de núcleos disponíveis
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    /**
     * Inicia o cálculo de Pi utilizando várias threads.
     * Divide os pontos entre as threads e coleta os resultados após a execução.
     */
    public void iniciarCalculo() {
        System.out.println("Iniciando cálculo com " + numThreads + " threads e " + numPontos + " pontos...");

        // Cria um pool de threads com o número especificado de threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        // Divide igualmente os pontos entre as threads
        long pontosPorThread = numPontos / numThreads;

        // Lista para armazenar os futuros resultados das threads
        ArrayList<Future<Long>> resultados = new ArrayList<>();

        // Submete as tarefas para as threads
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker(pontosPorThread); // Cria o Worker para esta thread
            resultados.add(executor.submit(worker)); // Submete o Worker para execução
        }

        executor.shutdown(); // Encerra o executor após submeter todas as tarefas

        // Combina os resultados das threads
        long totalPontosDentroDoCirculo = 0;
        try {
            // Espera os resultados de todas as threads e soma os pontos dentro do círculo
            for (Future<Long> resultado : resultados) {
                totalPontosDentroDoCirculo += resultado.get(); // Espera o resultado e soma
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Tratamento de exceções caso algo dê errado
        }

        // Calcula a estimativa final de Pi com base no total de pontos dentro do círculo
        double estimativaPi = 4.0 * totalPontosDentroDoCirculo / numPontos;
        System.out.println("Estimativa final de Pi: " + estimativaPi); // Exibe a estimativa de Pi
    }
}