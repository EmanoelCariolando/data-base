package model.dao.impl;
import db.DB;
import entities.Department;
import entities.Seller;
import model.dao.SellerDao;

import java.sql.*;
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
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1,obj.getName());
            ps.setString(2,obj.getEmail());
            ps.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4,obj.getBaseSalary());
            ps.setInt(5,obj.getDepartment().getId());

            int rowsAfected = ps.executeUpdate();

            if (rowsAfected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                    System.out.println("New insert approved!");
                }

            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Seller obj) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                            + "WHERE Id = ?"

            );

            ps.setString(1,obj.getName());
            ps.setString(2,obj.getEmail());
            ps.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4,obj.getBaseSalary());
            ps.setInt(5,obj.getDepartment().getId());
            ps.setInt(6,obj.getId());
            ps.executeUpdate();

            String nameObj = obj.getName();

            System.out.println("your " + obj + ", its update!");

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatment(ps);
        }

    }


    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM seller WHERE Id = ?"
            );
            st.setInt(1,id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                        return functiorSeller(rs,dp);
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            +   "ON seller.DepartmentId = department.Id "
                            +   "ORDER BY name"

            );


            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while (rs.next()){
                Department department = map.get(rs.getInt("DepartmentId"));

                if (department == null){
                    department = functionDep(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }

                Seller sl = functiorSeller(rs,department);
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
                     department = functionDep(rs);
                     map.put(rs.getInt("DepartmentId"), department);
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
