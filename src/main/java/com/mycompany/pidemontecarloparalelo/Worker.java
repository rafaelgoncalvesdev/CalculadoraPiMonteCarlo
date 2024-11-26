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

/**
 * Classe responsável por realizar o cálculo de Pi em uma thread separada.
 * Cada instância de Worker calcula a quantidade de pontos dentro do círculo
 * para uma quantidade específica de pontos.
 */
public class Worker implements Callable<Long> {
    
    private final long numPontos; // Número de pontos para esta thread calcular
    private final CalculadoraPi calculadoraPi; // Instância da CalculadoraPi para calcular Pi

    /**
     * Construtor que inicializa o número de pontos e a instância da calculadora de Pi.
     * 
     * @param numPontos número de pontos a serem calculados por esta thread.
     */
    public Worker(long numPontos) {
        this.numPontos = numPontos;
        this.calculadoraPi = new CalculadoraPi(numPontos); // Cria uma nova instância de calculadora para esta thread
    }

    /**
     * Método que será executado pela thread.
     * Executa o cálculo de Pi e retorna o número de pontos dentro do círculo.
     * 
     * @return número de pontos dentro do círculo calculados por esta thread.
     */
    @Override
    public Long call() {
        // Realiza o cálculo de Pi
        calculadoraPi.calcularPi();
        return calculadoraPi.getPontosDentroDoCirculo(); // Retorna o número de pontos dentro do círculo
    }
}