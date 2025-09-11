package Application;
import db.DB;
import entities.Department;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException, SQLException {

        Scanner sc = new Scanner(System.in);

        Department dpOne = new Department(1,"Books");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println(dpOne);

    }
}

