/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baserelacionale;

/**
 *
 * @author ddizoya
 */


public class Product<T> { //Clase genérica, en la que se le puede pasar el tipo de dato que queramos. 
    T cod, desc, prezo;

    public T getCod() {
        return cod;
    }

    public void setCod(T cod) {
        this.cod = cod;
    }

    public T getDesc() {
        return desc;
    }

    public void setDesc(T desc) {
        this.desc = desc;
    }

    public T getPrezo() {
        return prezo;
    }

    public void setPrezo(T prezo) {
        this.prezo = prezo;
    }

   
    
    
}
