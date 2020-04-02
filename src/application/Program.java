/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import model.entities.Departament;

/**
 *
 * @author slour
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Departament obj = new Departament(1, "Books");
        System.out.println(obj);
    }
    
}
