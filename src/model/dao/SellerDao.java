package model.dao;

import entities.Seller;

import java.util.List;

public interface SellerDao {
        void insert(Seller obj);
        void update(Seller obj);
        int deleteById(Seller obj);
        int findById(Seller obj);
        List<Seller> findAll(List<Seller> obj);
}
