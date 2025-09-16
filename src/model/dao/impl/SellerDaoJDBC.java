package model.dao.impl;
import db.DB;
import entities.Department;
import entities.Seller;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    @Override
    public List<Seller> findbyDep() {
        return List.of();
    }

    private Connection conn;

    public SellerDaoJDBC(Connection cone){
        this.conn = cone;
    }

    //METHODS
    public Department functionDep(ResultSet rs) throws SQLException {
        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("DepName"));
        return dp;
    }
    public Seller functiorSeller(ResultSet rs, Department dp) throws SQLException {
        Seller sl = new Seller();
        sl.setId(rs.getInt("Id"));
        sl.setName(rs.getString("Name"));
        sl.setEmail(rs.getString("Email"));
        sl.setBirthDate(rs.getDate("BirthDate"));
        sl.setBaseSalary(rs.getDouble("BaseSalary"));
        sl.setDepartment(dp);
        return sl;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + "(DepartmentId = ?)"
            );
            ps.setInt(1, obj.getId());
            int rows = ps.executeUpdate();
            System.out.println("Done! Rows Affected: " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            +   "ON seller.DepartmentId = department.Id "
                            +   "WHERE seller.Id = ? " );

                    st.setInt(1, id);
                    rs = st.executeQuery();
                    if (rs.next()){
                        Department dp = functionDep(rs);
                        Seller sl;
                        sl = functiorSeller(rs,dp);
                        return sl;
                    }
                    return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatment(st);
            DB.closeResult(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    @Override
    public List<Seller> findbyDep(Department dp) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            +   "ON seller.DepartmentId = department.Id "
                            +   "WHERE DepartmentId = ? "
                            +   "ORDER BY name"

            );

            st.setInt(1, dp.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while (rs.next()){
                Department department = map.get(rs.getInt("DepartmentId"));

                if (department == null){
                     dp = functionDep(rs);
                     map.put(rs.getInt("DepartmentId"), dp);
                }

                Seller sl = functiorSeller(rs,dp);
                list.add(sl);
            }
             return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatment(st);
            DB.closeResult(rs);
        }
    }
}
