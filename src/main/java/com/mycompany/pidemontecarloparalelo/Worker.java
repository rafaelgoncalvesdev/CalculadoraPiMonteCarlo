/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.concurrent.Callable;

/**
 *
 * @author rafael
 */
public class Worker implements Callable<Long> {
    private final long numPontos; // Número de pontos para esta thread calcular
    private final CalculadoraPi calculadoraPi; // Instância do calculador de Pi

    public Worker(long numPontos) {
        this.numPontos = numPontos;
        this.calculadoraPi = new CalculadoraPi(numPontos);
    }

    /**
     *
     * @return
     */
    @Override
    public Long call() {
        // Calcular pontos dentro do círculo
        calculadoraPi.calcularPi();
        return calculadoraPi.getPontosDentroDoCirculo();
    }
}
