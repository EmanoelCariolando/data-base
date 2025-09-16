package Application;
import entities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException, SQLException {
        int choice;
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        do {
            System.out.print(
                    "type the option: " +
                            "[1] = FindById" +
                            "[2] = FindByDepartment " +
                            "[3] = DeleteById" +
                            "[4] = insert" +
                            "[5] = Update" +
                            "[6] = FindAll" +
                            "[0] = Exit"

            );
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Search your id: ");
                    int id = sc.nextInt();
                    Seller findId = sellerDao.findById(id);
                    System.out.println(findId);
                    break;
                case 2:
                    System.out.println("type you department");
                    int depId = sc.nextInt();


            }
        } while (choice < 6 && choice != 0);





    }
}

