/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

/**
 *
 * @author rafael
 */
public class Worker implements Runnable {
    private final long numPontos; // Número de pontos para esta thread calcular
    private final CalculadoraPi calculadoraPi; // Instância do calculador de Pi

    public Worker(long numPontos) {
        this.numPontos = numPontos;
        this.calculadoraPi = new CalculadoraPi(numPontos);
    }

    @Override
    public void run() {
        // Calcular Pi usando o método de Monte Carlo
        double estimativaPi = calculadoraPi.calcularPi();
        System.out.println("Estimativa de Pi na thread: " + estimativaPi);
    }

    public long getNumPontos() {
        return numPontos;
    }

    public CalculadoraPi getCalculadoraPi() {
        return calculadoraPi;
    }
}
