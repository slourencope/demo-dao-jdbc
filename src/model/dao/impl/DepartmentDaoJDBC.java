/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 *
 * @author slour
 */
public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            + "(?)",
                            + Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, obj.getName());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected!");                
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE id = ?");
            
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            
            st.executeUpdate();

        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }
    
    @Override
    public void deletbyid(Integer id) {
       PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM department "
                      + "WHERE Id = ?");
            
            st.setInt(1, id);
            st.execute();
            
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department "
                            + "WHERE Id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()){
                Department depto = new Department(id, rs.getString("Name"));
                return depto;
            }
            return null;
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);            
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            List <Department> list = new ArrayList<>();
            while (rs.next()){
                Department depto = new Department(rs.getInt("Id"), rs.getString("Name"));
                list.add(depto);
            }
            return list;
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);            
        }
    }    
}
