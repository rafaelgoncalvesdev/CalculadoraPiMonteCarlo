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
 * Gerenciador para calcular Pi de forma paralela.
 */
public class GerenciadorParalelo {
    private final long numPontos; // Número total de pontos
    private final int numThreads; // Número de threads

    public GerenciadorParalelo(long totalPontos, int numThreads) {
        this.numPontos = totalPontos;
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    public void iniciarCalculo() {
        System.out.println("Iniciando cálculo com " + numThreads + " threads e " + numPontos + " pontos...");
        System.out.println("Calculando...");

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long pontosPorThread = numPontos / numThreads;

        // Lista para armazenar os resultados das threads
        ArrayList<Future<Long>> resultados = new ArrayList<>();

        // Submete as tarefas para as threads
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker(pontosPorThread);
            resultados.add(executor.submit(worker));
        }

        executor.shutdown();

        // Combina os resultados
        long totalPontosDentroDoCirculo = 0;
        try {
            for (Future<Long> resultado : resultados) {
                totalPontosDentroDoCirculo += resultado.get(); // Aguarda o resultado de cada thread
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Calcula a estimativa final de Pi
        double estimativaPi = 4.0 * totalPontosDentroDoCirculo / numPontos;
        System.out.println("Estimativa final de Pi: " + estimativaPi);
    }
}