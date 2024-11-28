/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
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

            // Define o fundo como cinza claro
            g.setColor(new Color(242, 242, 242)); // Fundo cinza (como o ChatGPT)
            g.fillRect(0, 0, largura, altura);

            // Desenha o círculo (com raio proporcional à largura/altura)
            int raio = Math.min(largura, altura) / 2;
            int centroX = largura / 2;
            int centroY = altura / 2;
            g.setColor(Color.BLACK);
            g.drawOval(centroX - raio, centroY - raio, raio * 2, raio * 2);

            // Desenha os pontos
            for (PointData ponto : pontos) {
                // Mapeia o ponto no painel com arredondamento
                int x = (int) Math.round(ponto.getX() * raio + centroX); // Arredonda as coordenadas
                int y = (int) Math.round(ponto.getY() * raio + centroY);

                // Define a cor do ponto
                g.setColor(ponto.isDentroDoCirculo() ? new Color(76, 175, 80) : new Color(244, 67, 54)); // Verde e vermelho suaves
                g.fillOval(x, y, 5, 5); // Aumente o tamanho dos pontos para melhor visualização
            }
        }
    }
}
