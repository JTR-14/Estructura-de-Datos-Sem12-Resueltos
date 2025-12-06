/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;


public class NodoAVL {
    private int valor;
    private int altura;
    private NodoAVL nodoDerecha;
    private NodoAVL nodoIzquierda;
    
    public NodoAVL(int valor){
        this.valor = valor;
        this.altura = 1;
        this.nodoDerecha  = null;
        this.nodoIzquierda = null;
}

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVL getNodoDerecha() {
        return nodoDerecha;
    }

    public void setNodoDerecha(NodoAVL nodoDerecha) {
        this.nodoDerecha = nodoDerecha;
    }

    public NodoAVL getNodoIzquierda() {
        return nodoIzquierda;
    }

    public void setNodoIzquierda(NodoAVL nodoIzquierda) {
        this.nodoIzquierda = nodoIzquierda;
    }

}
