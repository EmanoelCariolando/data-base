package Application;
import entities.Department;
import entities.Seller;
import methods.Menu;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.nio.channels.SelectableChannel;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException, SQLException {
        boolean very = true;
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sc);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        int choice;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        do {
            if (very) {
                System.out.println("Please type one option: ");
            }
            else {
                System.out.println("Type Another Option: ");
            }
            choice = menu.choices();

            if (choice < 6){
            switch (choice){
                case 1:
                    System.out.print("Search your id: ");
                    int id = sc.nextInt();
                    Seller findId = sellerDao.findById(id);
                    System.out.println(findId);
                    very = false;
                    break;
                case 2:
                    System.out.println("type your department");
                    int dp = sc.nextInt();
                    Department department = new Department(dp,null);
                    List<Seller> listdp = sellerDao.findbyDep(department);
                    for (Seller seller : listdp){
                        System.out.println(seller);
                    }
                    break;
                case 3:
                    System.out.println("Showing all Seller ");
                    List<Seller> listall = sellerDao.findAll();
                    for (Seller allsellers : listall ){
                        System.out.println(allsellers);
                    }
                     very = false;
                    break;
                case 4:
                    System.out.println("Insert the new Datas: ");
                    System.out.print("type your name: ");
                    String name = sc.next();
                    System.out.print("type your Email: ");
                    String email = sc.next();
                    System.out.print("type your BirthDate: ");
                    String birthDate = sc.next();
                    System.out.print("type your BaseSalary: ");
                    Double baseSalary = sc.nextDouble();
                    System.out.print("type your Id: ");
                    int ids = sc.nextInt();
                    Seller sellers = new Seller(baseSalary,sdf.parse(birthDate),email,ids,name);
                    sellerDao.insert(sellers);
                    very = false;
                    break;


                }
            }
            else {
                System.out.println("Choice the correct number...");
                very = false;
            }
        } while (choice != 0);






    }
}

