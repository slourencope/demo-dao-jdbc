/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

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
        //Departament obj = new Department(1, "Books");
        //System.out.println(obj);
        
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        
        System.out.println("\n=== TEST 2: seller findDepartmentById ===");
        Department department = new Department(2, null); 
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller object : list) {
            System.out.println(object);
        }
        
        System.out.println("\n=== TEST 3: seller findAll ===");
        List<Seller> list1 = sellerDao.findAll();
        for (Seller object : list1) {
            System.out.println(object);
        }
/*        
        System.out.println("\n=== TEST 4: seller Insert ===");
        seller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(seller);
        System.out.println("Inserted! New id: " + seller.getId());
*/        
        System.out.println("\n=== TEST 5: seller Update ===");
        seller = sellerDao.findById(1);
        seller.setName("Martha Weine");
        sellerDao.update(seller);
        System.out.println("Update completed.");
        
        System.out.println("\n=== TEST 6: seller Delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        Seller sellerDel = null;
        sellerDel = sellerDao.findById(id);
        if (sellerDel == null){
            System.out.println("Seller " + id +" does not exist.");
        }else {
            System.out.println("Seller " + sellerDel.getName() +" will be deleted.");
            sellerDao.deletbyid(id);
            System.out.println("Delete completed.");
        }
        sc.close();
    }
}
