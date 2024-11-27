/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.ArrayList;
import java.util.List;
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
    private final int numThreads; // Número de threads

    public GerenciadorParalelo(long totalPontos, int numThreads) {
        this.numPontos = totalPontos;
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    public List<PointData> iniciarCalculo() {
        System.out.println("Iniciando cálculo com " + numThreads + " threads e " + numPontos + " pontos...");

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        long pontosPorThread = numPontos / numThreads;

        // Lista para armazenar os resultados das threads
        ArrayList<Future<List<PointData>>> resultados = new ArrayList<>();

        // Submete as tarefas para as threads
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker(pontosPorThread);
            resultados.add(executor.submit(worker));
        }

        executor.shutdown();

        // Combina os resultados
        List<PointData> todosPontos = new ArrayList<>();
        try {
            for (Future<List<PointData>> resultado : resultados) {
                todosPontos.addAll(resultado.get()); // Aguarda o resultado de cada thread
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Calcula a estimativa final de Pi
        long totalPontosDentroDoCirculo = todosPontos.stream().filter(PointData::isDentroDoCirculo).count();
        double estimativaPi = 4.0 * totalPontosDentroDoCirculo / numPontos;
        System.out.println("Estimativa final de Pi: " + estimativaPi);

        return todosPontos;
    }
}