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

    public void insertar(int valor) {
        this.raiz = insertarRecursivo(this.raiz, valor);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, int valor) {
        // 1. INSERCIÓN NORMAL (Igual que en ABB)
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.getValor()) {
            nodo.setNodoIzquierda(insertarRecursivo(nodo.getNodoIzquierda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setNodoDerecha(insertarRecursivo(nodo.getNodoDerecha(), valor));
        } else {
            return nodo; 
        }

        // 2. ACTUALIZAR ALTURA
        // La altura es 1 + la altura del hijo más alto
        nodo.setAltura(1 + Math.max(getAltura(nodo.getNodoIzquierda()), 
                                    getAltura(nodo.getNodoDerecha())));

        // 3. OBTENER FACTOR DE EQUILIBRIO
        // (Altura Izquierda - Altura Derecha)
        int balance = getBalance(nodo);

        // 4. APLICAR ROTACIONES SI HAY DESEQUILIBRIO
        
        // Caso Izquierda - Izquierda (Se insertó en la izquierda del hijo izquierdo)
        if (balance > 1 && valor < nodo.getNodoIzquierda().getValor()) {
            return rotacionDerecha(nodo);
        }

        // Caso Derecha - Derecha (Se insertó en la derecha del hijo derecho)
        if (balance < -1 && valor > nodo.getNodoDerecha().getValor()) {
            return rotacionIzquierda(nodo);
        }

        // Caso Izquierda - Derecha (Rotación Doble)
        if (balance > 1 && valor > nodo.getNodoIzquierda().getValor()) {
            nodo.setNodoIzquierda(rotacionIzquierda(nodo.getNodoIzquierda()));
            return rotacionDerecha(nodo);
        }

        // Caso Derecha - Izquierda (Rotación Doble)
        if (balance < -1 && valor < nodo.getNodoDerecha().getValor()) {
            nodo.setNodoDerecha(rotacionDerecha(nodo.getNodoDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo; // Si llegamos aquí, es que NO hubo rotación y devolvemos el nodo actual actualizado.
    }

    // --- MÉTODOS AUXILIARES ---

    private int getAltura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.getAltura();
    }

    private int getBalance(NodoAVL nodo) {
        if (nodo == null) return 0;
        return getAltura(nodo.getNodoIzquierda()) - getAltura(nodo.getNodoDerecha());
    }

    // --- ROTACIONES ---

    // Rotación Simple a la Derecha
    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getNodoIzquierda();
        NodoAVL T2 = x.getNodoDerecha();

        // Rotar
        x.setNodoDerecha(y);
        y.setNodoIzquierda(T2);

        // Actualizar alturas (primero y, luego x porque x está arriba ahora)
        y.setAltura(Math.max(getAltura(y.getNodoIzquierda()), getAltura(y.getNodoDerecha())) + 1);
        x.setAltura(Math.max(getAltura(x.getNodoIzquierda()), getAltura(x.getNodoDerecha())) + 1);

        return x; // Nueva raíz
    }

    // Rotación Simple a la Izquierda
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getNodoDerecha();
        NodoAVL T2 = y.getNodoIzquierda();

        // Rotar
        y.setNodoIzquierda(x);
        x.setNodoDerecha(T2);

        // Actualizar alturas
        x.setAltura(Math.max(getAltura(x.getNodoIzquierda()), getAltura(x.getNodoDerecha())) + 1);
        y.setAltura(Math.max(getAltura(y.getNodoIzquierda()), getAltura(y.getNodoDerecha())) + 1);

        return y; // Nueva raíz
    }
    
    // Método para imprimir (InOrden) y verificar
    public void inOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        inOrden(this.raiz, modelo);
    }
    private void inOrden(NodoAVL nodo, DefaultListModel modelo) {
        
        if (nodo != null) {
            inOrden(nodo.getNodoIzquierda(), modelo);
            modelo.addElement(nodo.getValor());
            inOrden(nodo.getNodoDerecha(),modelo);
        }
    }
    
    public void preOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        preOrden(this.raiz, modelo);
    }
    private void preOrden(NodoAVL nodo, DefaultListModel modelo) {
        
        if (nodo != null) {
            modelo.addElement(nodo.getValor());
            preOrden(nodo.getNodoIzquierda(), modelo);
            preOrden(nodo.getNodoDerecha(),modelo);
        }
    }
    
    public void postOrden(DefaultListModel modelo) {
        modelo.removeAllElements();
        postOrden(this.raiz, modelo);
    }
    private void postOrden(NodoAVL nodo, DefaultListModel modelo) {
        
        if (nodo != null) {
            postOrden(nodo.getNodoIzquierda(), modelo);
            postOrden(nodo.getNodoDerecha(),modelo);
            modelo.addElement(nodo.getValor());
        }
    }
    
    public boolean buscar(int valor){
        return buscar(raiz, valor);
    }
    public boolean buscar(NodoAVL nodo, int valor){
        if(nodo == null)
            return false;
        if(valor == nodo.getValor())
            return true;
        if(valor < nodo.getValor())
            return buscar(nodo.getNodoIzquierda(),valor);
        else
            return buscar(nodo.getNodoDerecha(),valor);
    }
    
    private NodoAVL eliminarRecursivo(NodoAVL nodo, int valor) {
    // 1. ELIMINACIÓN NORMAL
    if (nodo == null) return null;

    if (valor < nodo.getValor()) {
        nodo.setNodoIzquierda(eliminarRecursivo(nodo.getNodoIzquierda(), valor));
    } else if (valor > nodo.getValor()) {
        nodo.setNodoDerecha(eliminarRecursivo(nodo.getNodoDerecha(), valor));
    } else {
        // ¡ENCONTRADO! 
        
        // --- TU MEJORA AQUÍ ---
        // Casos 1 y 2: Sin hijos o con un solo hijo (Salida Rápida)
        if (nodo.getNodoIzquierda() == null) {
            return nodo.getNodoDerecha(); 
        } 
        else if (nodo.getNodoDerecha() == null) {
            return nodo.getNodoIzquierda();
        }

        // Caso 3: Dos hijos (Este sí se queda igual)
        NodoAVL temp = encontrarMinimoNodo(nodo.getNodoDerecha());
        nodo.setValor(temp.getValor());
        nodo.setNodoDerecha(eliminarRecursivo(nodo.getNodoDerecha(), temp.getValor()));
    }

    // --- PARTE AVL (Balanceo) ---
    // Esta parte se ejecutará para los PADRES que se quedaron esperando en la recursión.
    
    // Actualizar altura
    nodo.setAltura(Math.max(getAltura(nodo.getNodoIzquierda()), getAltura(nodo.getNodoDerecha())) + 1);

    // Obtener balance
    int balance = getBalance(nodo);

    // Rotaciones (Mismo código de antes)
    if (balance > 1 && getBalance(nodo.getNodoIzquierda()) >= 0)
        return rotacionDerecha(nodo);

    if (balance > 1 && getBalance(nodo.getNodoIzquierda()) < 0) {
        nodo.setNodoIzquierda(rotacionIzquierda(nodo.getNodoIzquierda()));
        return rotacionDerecha(nodo);
    }

    if (balance < -1 && getBalance(nodo.getNodoDerecha()) <= 0)
        return rotacionIzquierda(nodo);

    if (balance < -1 && getBalance(nodo.getNodoDerecha()) > 0) {
        nodo.setNodoDerecha(rotacionDerecha(nodo.getNodoDerecha()));
        return rotacionIzquierda(nodo);
    }

    return nodo;
}
    public NodoAVL encontrarMinimoNodo(NodoAVL nodo){
        NodoAVL actual = nodo;
        while(actual.getNodoIzquierda() != null)
            actual = actual.getNodoIzquierda();
        return actual;
    }
}

