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

/**
 * Classe responsável por realizar o cálculo de Pi utilizando o método de Monte Carlo.
 */
public class CalculadoraPi {
    
    private final long numPontos; // Número total de pontos a serem gerados
    private long pontosDentroDoCirculo; // Contador de pontos dentro do círculo
    private final Random random; // Gerador de números aleatórios

    /**
     * Construtor que inicializa o número de pontos a serem utilizados no cálculo.
     * 
     * @param numPontos número total de pontos a serem gerados.
     */
    public CalculadoraPi(long numPontos) {
        this.numPontos = numPontos;
        this.pontosDentroDoCirculo = 0;
        this.random = new Random(); // Inicializa o gerador de números aleatórios
    }

    /**
     * Método que executa o cálculo de Pi pelo método de Monte Carlo.
     * 
     * Para cada ponto gerado, verifica se está dentro do círculo de raio 1
     * e dentro de um quadrado de lado 2, centrado na origem (0, 0).
     * 
     * @return o valor estimado de Pi.
     */
    public double calcularPi() {
        for (long i = 0; i < numPontos; i++) {
            // Gera um ponto aleatório dentro do quadrado [-1, 1] x [-1, 1]
            double x = random.nextDouble() * 2 - 1; // Gera valor entre -1 e 1
            double y = random.nextDouble() * 2 - 1; // Gera valor entre -1 e 1

            // Verifica se o ponto está dentro do círculo de raio 1
            if (x * x + y * y <= 1) {
                pontosDentroDoCirculo++; // Incrementa o contador se o ponto estiver dentro do círculo
            }
        }

        // Calcula e retorna a estimativa de Pi com base nos pontos gerados
        return 4.0 * pontosDentroDoCirculo / numPontos;
    }

    // Método para obter o número de pontos dentro do círculo
    public long getPontosDentroDoCirculo() {
        return pontosDentroDoCirculo;
    }
}

