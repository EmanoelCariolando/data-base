package model.dao;
import entities.Department;
import entities.Seller;

import java.util.List;

public interface DepartmentDao {
   void insert(Department obj);
     void update(Department obj);
     int deleteById(Department obj);
     int findById(Department obj);
     List<Seller> findAll(List<Department> obj);
}
