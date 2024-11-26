/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.Random;

/**
 *
 * @author rafael
 */
public class CalculadoraPi {
    
    private final long numPontos;
    private long pontosDentroDoCirculo;
    private final Random random;

    /**
     * Construtor da classe, recebe o número de pontos a serem usados no cálculo.
     * @param numPontos número de pontos a serem gerados no cálculo de Pi.
     */
    public CalculadoraPi(long numPontos) {
        this.numPontos = numPontos;
        this.pontosDentroDoCirculo = 0;
        this.random = new Random();
    }

    /**
     * Método que executa o cálculo do Pi pelo método de Monte Carlo.
     * @return o valor estimado de Pi.
     */
    public double calcularPi() {
        for (long i = 0; i < numPontos; i++) {
            double x = random.nextDouble() * 2 - 1; // [-1, 1]
            double y = random.nextDouble() * 2 - 1; // [-1, 1]

            // Verifica se o ponto está dentro do círculo
            if (x * x + y * y <= 1) {
                pontosDentroDoCirculo++;
            }
        }

        // Estimativa de Pi usando a fórmula do método de Monte Carlo
        return 4.0 * pontosDentroDoCirculo / numPontos;
    }

}
