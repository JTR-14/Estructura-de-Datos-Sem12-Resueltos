/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;

import javax.swing.DefaultListModel;



public class ArbolAVL {
  private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return nodo.getAltura();
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int balance(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        y.setAltura(max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);
        x.setAltura(max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        x.setAltura(max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);
        y.setAltura(max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);

        return y;
    }

    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
    }

    private NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null)
            return (new NodoAVL(valor));

        if (valor < nodo.getValor())
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), valor));
        else if (valor > nodo.getValor())
            nodo.setDerecho(insertar(nodo.getDerecho(), valor));
        else
            return nodo;

        nodo.setAltura(1 + max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        int balance = balance(nodo);

        if (balance > 1 && valor < nodo.getIzquierdo().getValor())
            return rotarDerecha(nodo);

        if (balance < -1 && valor > nodo.getDerecho().getValor())
            return rotarIzquierda(nodo);

        if (balance > 1 && valor > nodo.getIzquierdo().getValor()) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && valor < nodo.getDerecho().getValor()) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminar(raiz, valor);
    }

    private NodoAVL eliminar(NodoAVL nodo, int valor) {
        if (nodo == null)
            return nodo;

        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), valor));
        } else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            NodoAVL temp = minimoValor(nodo.getDerecho());
            nodo.setValor(temp.getValor());
            nodo.setDerecho(eliminar(nodo.getDerecho(), temp.getValor()));
        }

        nodo.setAltura(max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);

        int balance = balance(nodo);

        if (balance > 1 && balance(nodo.getIzquierdo()) >= 0)
            return rotarDerecha(nodo);

        if (balance > 1 && balance(nodo.getIzquierdo()) < 0) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && balance(nodo.getDerecho()) <= 0)
            return rotarIzquierda(nodo);

        if (balance < -1 && balance(nodo.getDerecho()) > 0) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL minimoValor(NodoAVL nodo) {
        if (nodo.getIzquierdo() == null)
            return nodo;
        return minimoValor(nodo.getIzquierdo());
    }
  
    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }

    private boolean buscar(NodoAVL nodo, int valor) {
        if (nodo == null) return false;
        
        if (valor == nodo.getValor()) return true;
        
        if (valor < nodo.getValor())
            return buscar(nodo.getIzquierdo(), valor);
        else
            return buscar(nodo.getDerecho(), valor);
    }
    public void inOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        inOrden(raiz, modelo);
    }
    private void inOrden(NodoAVL nodo, DefaultListModel modelo) {      
        
        if (nodo != null) {
            inOrden(nodo.getIzquierdo(), modelo);
            modelo.addElement(nodo.getValor());
            inOrden(nodo.getDerecho(),modelo);
        }
    }
    
    public void preOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        preOrden(raiz, modelo);
    }
    private void preOrden(NodoAVL nodo, DefaultListModel modelo) {
        
        if (nodo != null) {
            modelo.addElement(nodo.getValor());
            preOrden(nodo.getIzquierdo(), modelo);
            preOrden(nodo.getDerecho(),modelo);
        }
    }
    
    public void postOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        postOrden(raiz, modelo);
    }
    private void postOrden(NodoAVL nodo, DefaultListModel modelo) {
        
        if (nodo != null) {
            postOrden(nodo.getIzquierdo(), modelo);
            postOrden(nodo.getDerecho(),modelo);
            modelo.addElement(nodo.getValor());
        }
    }
    
}

