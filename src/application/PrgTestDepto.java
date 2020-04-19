/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 *
 * @author slour
 */
public class PrgTestDepto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Scanner sc = new Scanner(System.in);
/*        
        System.out.println("=== TEST 1: department Insert ===");
        Department depto = new Department(null, "Tests");
        departmentDao.insert(depto);
        System.out.println("Inserted! New id: " + depto.getId());;
*/
        System.out.println("=== TEST 2: department findById ===");
        Department depto = departmentDao.findById(1);
        System.out.println(depto.toString());

        System.out.println("\n=== TEST 3: department findAll ===");
        List <Department> deptos = departmentDao.findAll();
        for (Department obj: deptos) {
            System.out.println(obj.toString());
        }        

        System.out.println("\n=== TEST 4: department update ===");
        depto = new Department(7, "Fruit");
        Department deptoOld = departmentDao.findById(depto.getId());
        System.out.println("Updating the department: "+ deptoOld.toString());
        departmentDao.update(depto);
        Department deptoNew = departmentDao.findById(depto.getId());
        System.out.println("Department updated: "+ deptoNew.toString());
        
        System.out.println("\n=== TEST 5: seller Delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        Department deptoDel = departmentDao.findById(id);
        if (deptoDel == null){
            System.out.println("Department " + id +" does not exist.");
        }else {
            System.out.println("Department " + deptoDel.getName() +" will be deleted.");
            departmentDao.deletbyid(id);
            System.out.println("Delete completed.");
        }
        sc.close();        
    }
    
}
