/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Departament;

/**
 *
 * @author slour
 */
public interface DepartamentDao {
    void insert(Departament obj);
    void update(Departament obj);
    void deletbyid(Integer id);
    Departament findById(Integer id);
    List <Departament> findAll();
}
