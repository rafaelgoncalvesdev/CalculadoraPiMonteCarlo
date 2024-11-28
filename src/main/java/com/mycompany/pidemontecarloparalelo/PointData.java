/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pidemontecarloparalelo;

/**
 *
 * @author rafael
 */
public class PointData {
    private final double x;
    private final double y;
    private final boolean dentroDoCirculo;

    public PointData(double x, double y, boolean dentroDoCirculo) {
        this.x = x;
        this.y = y;
        this.dentroDoCirculo = dentroDoCirculo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isDentroDoCirculo() {
        double distanciaQuadrada = getX() * getX() + getY() * getY();
        return distanciaQuadrada <= 1.0; // Comparação precisa para o círculo unitário
    }
}
