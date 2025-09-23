package Application;
import entities.Department;
import entities.Seller;
import methods.Menu;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        //Colors
        String green = "\u001B[32m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";


        do {
            if (very) {
                System.out.println(green + "Please type one option: " + reset);
            }
            else {
                System.out.println(green + "Type Another Option: " + reset);
            }
            choice = menu.choices();

            if (choice < 6){
            switch (choice){
                case 1:
                    System.out.print(green + "Search your id: " + reset);
                    int id = sc.nextInt();
                    Seller findId = sellerDao.findById(id);
                    System.out.println(findId);
                    very = false;
                    break;
                case 2:
                    System.out.println(green + "type your department" + reset);
                    int dp = sc.nextInt();
                    Department department = new Department(dp,null);
                    List<Seller> listdp = sellerDao.findbyDep(department);
                    for (Seller seller : listdp){
                        System.out.println(seller);
                    }
                    break;
                case 3:
                    System.out.println(green + "Showing all Seller " + reset);
                    List<Seller> listall = sellerDao.findAll();
                    for (Seller allsellers : listall ){
                        System.out.println(allsellers);
                    }
                     very = false;
                    break;
                case 4:
                    System.out.println(green + "Insert the new Datas: " + reset);
                    System.out.print("type your name: ");
                    String name = sc.next();
                    System.out.print("type your Email: ");
                    String email = sc.next();
                    System.out.print("type your BirthDate: ");
                    String birthDate = sc.next();
                    System.out.print("type your BaseSalary: ");
                    double baseSalary = sc.nextDouble();
                    System.out.print("type your Id: ");
                    int ids = sc.nextInt();
                    Seller newSeller = new Seller(baseSalary,sdf.parse(birthDate),email,ids,name);
                    sellerDao.insert(newSeller);
                    very = false;
                    break;
                case 5:
                    System.out.println(green +"enter an ID to update: "+ reset);
                    int idUp = sc.nextInt();
                    newSeller = sellerDao.findById(idUp);
                    int updateChoice = menu.updateChoices();

                     switch (updateChoice) {
                         case 1:
                             System.out.println(green +"Type the new name: "+ reset);
                             name = sc.next();
                             newSeller.setName(name);
                             sellerDao.update(newSeller);
                             break;
                         case 2:
                             System.out.println(green +"Type the new email: "+ reset);
                             email = sc.next();
                             newSeller.setEmail(email);
                             sellerDao.update(newSeller);
                             break;
                         case 3:
                             System.out.println(green +"Type the new BirthDate: "+ reset);
                             birthDate = sc.next();
                             newSeller.setBirthDate(sdf.parse(birthDate));
                             sellerDao.update(newSeller);
                             break;
                         case 4:
                             System.out.println(green +"Type the new Base Salary: "+ reset);
                             baseSalary = sc.nextDouble();
                             newSeller.setBaseSalary(baseSalary);
                             sellerDao.update(newSeller);
                             break;
                         case 5:
                             Department existingDep;

                             do {
                                 System.out.println(green + "Type the new Department ID: " + reset);
                                 ids = sc.nextInt();
                                 sc.nextLine(); // consumir quebra de linha do nextInt()

                                 Seller sellerFound = sellerDao.findById(ids);
                                 existingDep = (sellerFound != null) ? sellerFound.getDepartment() : null;

                                 if (existingDep != null && existingDep.getId() == ids) {
                                     System.out.println(red + "Department with ID " + ids
                                             + " already exists (" + existingDep.getName() + "). Please try again."
                                             + reset);
                                 }
                             } while (existingDep != null && existingDep.getId() == ids);

// Quando sair do loop, o ID é válido (não existe no banco)
                             System.out.println(green + "Type the new Department Name: " + reset);
                             String depName = sc.nextLine();

                             Department newDep = new Department(ids, depName);
                             newSeller.setDepartment(newDep);
                             sellerDao.update(newSeller);

                             System.out.println(blue + "Department updated successfully!" + reset);
                             break;
                         case 0:
                             System.out.println("leaving...");
                             very = false;
                             break;

                     }
                     break;

                case 6:
                    System.out.println(green +"Type one Id For Delete: "+ reset);
                    id = sc.nextInt();
                    sellerDao.deleteById(id);
                    break;

                case 0:
                    System.out.println(red + "Leaving..." + reset);

                }
            }
            else {
                System.out.println("Choice the correct number...");
                very = false;
            }
        } while (choice != 0);






    }
}

