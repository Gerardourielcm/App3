package com.example.proyectoilulu2_0;

import java.io.Serializable;

public class Cuenta implements Serializable{
    private String nameCuenta;
    private String passCuenta;
    private int image;
    private String red;

    public Cuenta(){

    }

    public String getNameCuenta() {
        return nameCuenta;
    }

    public void setNameCuenta(String nameCuenta) {
        this.nameCuenta = nameCuenta;
    }

    public String getPassCuenta() {
        return passCuenta;
    }

    public void setPassCuenta(String passCuenta) {
        this.passCuenta = passCuenta;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }
}