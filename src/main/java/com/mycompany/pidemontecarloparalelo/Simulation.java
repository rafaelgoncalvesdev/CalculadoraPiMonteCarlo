/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rafael
 */
/**
 * Classe responsável por gerenciar a visualização gráfica da simulação do
 * cálculo de Pi utilizando o método de Monte Carlo.
 */
public class Simulation {
    private final List<PointData> pontos;

    public Simulation(List<PointData> pontos) {
        this.pontos = pontos;
    }

    public void mostrar() {
        JFrame frame = new JFrame("Simulação de Monte Carlo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new PainelSimulacao());
        frame.setVisible(true);
    }

    private class PainelSimulacao extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int largura = getWidth();
            int altura = getHeight();

            // Define o fundo cinza
            Color backgroundColor = new Color(52, 53, 65); // Cinza semelhante ao do site do ChatGPT
            g.setColor(backgroundColor);
            g.fillRect(0, 0, largura, altura);

            // Desenha o círculo (com raio proporcional à largura/altura)
            int raio = Math.min(largura, altura) / 2;
            int centroX = largura / 2;
            int centroY = altura / 2;
            g.setColor(Color.LIGHT_GRAY); // Círculo com borda cinza clara
            g.drawOval(centroX - raio, centroY - raio, raio * 2, raio * 2);

            // Desenha os pontos
            for (PointData ponto : pontos) {
                int x = (int) (ponto.getX() * raio + centroX); // Mapeia o ponto no painel
                int y = (int) (ponto.getY() * raio + centroY);

                // Defina cores suaves para os pontos
                Color dentroDoCirculo = new Color(144, 238, 144); // Verde suave
                Color foraDoCirculo = new Color(255, 102, 102);   // Vermelho suave

                g.setColor(ponto.isDentroDoCirculo() ? dentroDoCirculo : foraDoCirculo);
                g.fillOval(x - 3, y - 3, 6, 6); // Pontos levemente maiores (6x6)
            }
        }
    }
}
