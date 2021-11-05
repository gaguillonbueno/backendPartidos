package com.javainuse.instance;

//Clase para mostrar mensajes por pantalla en el cliente (front)
public class MensajeInstancia {

    private String mensaje;

    public MensajeInstancia(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
