/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author rafael
 */

/**
 * Classe responsável por realizar o cálculo de Pi em uma thread separada.
 * Cada instância de Worker calcula a quantidade de pontos dentro do círculo
 * para uma quantidade específica de pontos.
 */
public class Worker implements Callable<List<PointData>> {
    private final long numPontos; // Número de pontos para esta thread calcular

    public Worker(long numPontos) {
        this.numPontos = numPontos;
    }

    @Override
    public List<PointData> call() {
        List<PointData> pontos = new ArrayList<>();
        Random random = new Random();

        for (long i = 0; i < numPontos; i++) {
            double x = random.nextDouble() * 2 - 1; // Coordenada x [-1, 1]
            double y = random.nextDouble() * 2 - 1; // Coordenada y [-1, 1]
            boolean dentroDoCirculo = (x * x + y * y) <= 1; // Verifica se está no círculo
            pontos.add(new PointData(x, y, dentroDoCirculo));
        }

        return pontos;
    }
}