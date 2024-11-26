/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rafael
 */
public class MonteCarloPiVisualization extends JPanel {
    private final int radius = 250; // Raio do círculo
    private final int panelSize = 600; // Tamanho do painel
    private final Random random = new Random();
    private final AtomicLong pointsInsideCircle = new AtomicLong(0); // Contador de pontos dentro do círculo
    private final int totalPointsToGenerate = 100000; // Número total de pontos a serem gerados
    private final int numThreads = 4; // Número de threads
    private final long delayTime = 10; // Tempo de atraso entre os pontos (em milissegundos)

    private long currentPointIndex = 0;

    public MonteCarloPiVisualization() {
        setPreferredSize(new Dimension(panelSize, panelSize));
        generatePointsInParallel(numThreads);
    }

    // Método para gerar pontos de forma paralela
    private void generatePointsInParallel(int numThreads) {
        long pointsPerThread = totalPointsToGenerate / numThreads;
        
        // Cria as threads para gerar pontos
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                long localPointsInsideCircle = 0;
                for (long i1 = 0; i1 < pointsPerThread; i1++) {
                    double x = random.nextDouble() * 2 - 1; // Coordenada X no intervalo [-1, 1]
                    double y = random.nextDouble() * 2 - 1; // Coordenada Y no intervalo [-1, 1]

                    if (x * x + y * y <= 1) {
                        localPointsInsideCircle++;
                    }

                    // Contabiliza os pontos
                    currentPointIndex++;
                    if (currentPointIndex % 10 == 0) {
                        repaint(); // Atualiza a tela a cada 10 pontos
                    }

                    try {
                        // Adiciona um atraso dependendo do número de pontos
                        if (totalPointsToGenerate < 500) {
                            Thread.sleep(50); // Mais devagar para números menores de pontos
                        } else if (totalPointsToGenerate < 1000) {
                            Thread.sleep(25); // Moderado para números médios de pontos
                        } else {
                            Thread.sleep(10); // Mais rápido para números maiores de pontos
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pointsInsideCircle.addAndGet(localPointsInsideCircle);
                repaint(); // Atualiza a visualização após a thread terminar
            }).start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o quadrado
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(50, 50, 2 * radius, 2 * radius);

        // Desenha o círculo
        g2d.setColor(Color.BLUE);
        g2d.drawOval(50, 50, 2 * radius, 2 * radius);

        // Desenha os pontos na tela
        for (long i = 0; i < currentPointIndex; i++) {
            double x = random.nextDouble() * 2 - 1; // Coordenada X no intervalo [-1, 1]
            double y = random.nextDouble() * 2 - 1; // Coordenada Y no intervalo [-1, 1]

            int drawX = (int) (50 + radius + x * radius);
            int drawY = (int) (50 + radius + y * radius);

            if (x * x + y * y <= 1) {
                g2d.setColor(Color.GREEN); // Ponto dentro do círculo
            } else {
                g2d.setColor(Color.RED); // Ponto fora do círculo
            }
            g2d.fillOval(drawX, drawY, 3, 3); // Desenhar ponto
        }

        // Exibe a estimativa de Pi
        double piEstimate = 4.0 * pointsInsideCircle.get() / totalPointsToGenerate;
        g2d.setColor(Color.BLACK);
        g2d.drawString("Estimativa de Pi: " + piEstimate, 10, panelSize - 20);
        g2d.drawString("Total de Pontos: " + currentPointIndex, 10, panelSize - 40);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Monte Carlo Pi Visualization");
        MonteCarloPiVisualization panel = new MonteCarloPiVisualization();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}