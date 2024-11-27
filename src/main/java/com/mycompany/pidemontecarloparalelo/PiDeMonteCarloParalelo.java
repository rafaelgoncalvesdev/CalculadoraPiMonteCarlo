/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pidemontecarloparalelo;

import java.util.List;
import java.util.Scanner;


/**
 *
 * @author rafael
 */

/**
 * Classe principal que inicia o cálculo de Pi utilizando o método de Monte Carlo de forma paralela.
 */
public class PiDeMonteCarloParalelo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário o número total de pontos a serem gerados
        System.out.print("Digite o número total de pontos: ");
        long numPontos = scanner.nextLong(); // Lê o número total de pontos    

        // Inicia o cálculo passando o número total de pontos para o método 'iniciar'
        List<PointData> pontos = iniciar(numPontos);

        // Pergunta ao usuário se deseja visualizar a simulação gráfica
        System.out.print("Deseja visualizar a simulação gráfica? (s/n): ");
        char opcao = scanner.next().toLowerCase().charAt(0); // Lê a opção do usuário
        scanner.close();

        if (opcao == 's') {
            // Inicia a simulação gráfica
            Simulation simulation = new Simulation(pontos);
            simulation.mostrar(); // Exibe a simulação gráfica
        } else {
            System.out.println("Simulação gráfica não será exibida.");
        }
    }

    /**
     * Inicia o cálculo paralelo de Pi com o número de pontos especificado.
     * Calcula o tempo de execução e cria o gerenciador para iniciar o cálculo.
     * 
     * @param numPontos número total de pontos a serem usados no cálculo.
     * @return Lista de pontos gerados para a simulação gráfica.
     */
    private static List<PointData> iniciar(long numPontos) {
        // Obtém o número de threads disponíveis com base no número de núcleos do processador
        int numThreads = Runtime.getRuntime().availableProcessors();

        // Marca o tempo de início da execução
        long startTime = System.currentTimeMillis();

        // Cria e inicializa o gerenciador paralelo, que controlará as threads
        GerenciadorParalelo gerenciador = new GerenciadorParalelo(numPontos, numThreads);
        List<PointData> pontos = gerenciador.iniciarCalculo(); // Inicia o cálculo de Pi

        // Marca o tempo de término da execução
        long endTime = System.currentTimeMillis();

        // Calcula o tempo total de execução
        long duration = endTime - startTime; // em milissegundos
        System.out.println("Tempo total de execução: " + duration + " ms");

        return pontos;
    }
}


