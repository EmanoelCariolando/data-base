package Application;
import entities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException, SQLException {

        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.print("Search your id: ");
        int id = sc.nextInt();
        Seller seller = sellerDao.findById(id);
        System.out.println(seller);

    }
}

