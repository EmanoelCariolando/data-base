package Application;
import entities.Department;
import entities.Seller;
import methods.Controller;
import methods.Menu;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        Controller controller = new Controller(menu,sellerDao,sc);
        controller.run();
        sc.close();





    }
}

