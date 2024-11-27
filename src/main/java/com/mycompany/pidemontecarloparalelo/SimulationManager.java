/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author rafael
 */
/**
 * Gerencia a instância da simulação gráfica para que todos os Workers possam interagir com ela.
 */
public class SimulationManager {
    private static final List<PointData> pontos = new CopyOnWriteArrayList<>();

    /**
     * Adiciona um ponto à lista.
     *
     * @param x coordenada x
     * @param y coordenada y
     * @param dentroDoCirculo se o ponto está dentro do círculo
     */
    public static void adicionarPonto(double x, double y, boolean dentroDoCirculo) {
        pontos.add(new PointData(x, y, dentroDoCirculo));
    }

    /**
     * Retorna a lista de pontos gerados.
     *
     * @return lista imutável de pontos
     */
    public static List<PointData> getPontos() {
        return Collections.unmodifiableList(pontos);
    }
}