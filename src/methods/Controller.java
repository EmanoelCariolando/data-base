package methods;

import entities.Department;
import entities.Seller;
import model.dao.SellerDao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Controller {
        private final Menu menu;
        private final SellerDao sellerDao;
        private final Scanner sc;
        private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Cores
        private final String green = "\u001B[32m";
        private final String red = "\u001B[31m";
        private final String blue = "\u001B[34m";
        private final String reset = "\u001B[0m";

        public Controller(Menu menu, SellerDao sellerDao, Scanner sc) {
            this.menu = menu;
            this.sellerDao = sellerDao;
            this.sc = sc;
        }

        public void run() throws Exception {
            boolean very = true;
            int choice;

            do {
                if (very) {
                    System.out.println(green + "Please type one option: " + reset);
                } else {
                    System.out.println(green + "Type Another Option: " + reset);
                }

                choice = menu.choices();

                if (choice < 6) {
                    handleChoice(choice);
                    very = false;
                } else if (choice > 6) {
                    System.out.println(red + "Choice the correct number..." + reset);
                }
            } while (choice != 0);
        }

        private void handleChoice(int choice) throws Exception {
            switch (choice) {
                case 1 -> findSellerById();
                case 2 -> findSellerByDepartment();
                case 3 -> listAllSellers();
                case 4 -> insertSeller();
                case 5 -> updateSeller();
                case 6 -> deleteSeller();
                case 0 -> System.out.println(red + "Leaving..." + reset);
            }
        }

        // ---- Cada case vira um método separado ----
        private void findSellerById() {
            System.out.print(green + "Search your id: " + reset);
            int id = sc.nextInt();
            Seller findId = sellerDao.findById(id);
            System.out.println(findId);
        }

        private void findSellerByDepartment() {
            System.out.println(green + "type your department" + reset);
            int dp = sc.nextInt();
            Department department = new Department(dp, null);
            List<Seller> listdp = sellerDao.findbyDep(department);
            listdp.forEach(System.out::println);
        }

        private void listAllSellers() {
            System.out.println(green + "Showing all Seller " + reset);
            List<Seller> listall = sellerDao.findAll();
            listall.forEach(System.out::println);
        }

        private void insertSeller() throws Exception {
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

            Seller newSeller = new Seller(baseSalary, sdf.parse(birthDate), email, ids, name);
            sellerDao.insert(newSeller);
        }

        private void updateSeller() throws Exception {
            System.out.println(green + "enter an ID to update: " + reset);
            int idUp = sc.nextInt();
            Seller newSeller = sellerDao.findById(idUp);

            int updateChoice = menu.updateChoices();
            switch (updateChoice) {
                case 1 -> {
                    System.out.println(green + "Type the new name: " + reset);
                    String name = sc.next();
                    newSeller.setName(name);
                    sellerDao.update(newSeller);
                }
                case 2 -> {
                    System.out.println(green + "Type the new email: " + reset);
                    String email = sc.next();
                    newSeller.setEmail(email);
                    sellerDao.update(newSeller);
                }
                case 3 -> {
                    System.out.println(green + "Type the new BirthDate: " + reset);
                    String birthDate = sc.next();
                    newSeller.setBirthDate(sdf.parse(birthDate));
                    sellerDao.update(newSeller);
                }
                case 4 -> {
                    System.out.println(green + "Type the new Base Salary: " + reset);
                    double baseSalary = sc.nextDouble();
                    newSeller.setBaseSalary(baseSalary);
                    sellerDao.update(newSeller);
                }
                case 5 -> {
                    Department existingDep;
                    int ids;
                    do {
                        System.out.println(green + "Type the new Department ID: " + reset);
                        ids = sc.nextInt();
                        sc.nextLine();
                        Seller sellerFound = sellerDao.findById(ids);
                        existingDep = (sellerFound != null) ? sellerFound.getDepartment() : null;

                        if (existingDep != null && existingDep.getId() == ids) {
                            System.out.println(red + "Department with ID " + ids
                                    + " already exists (" + existingDep.getName() + "). Please try again."
                                    + reset);
                        }
                    } while (existingDep != null && existingDep.getId() == ids);

                    System.out.println(green + "Type the new Department Name: " + reset);
                    String depName = sc.nextLine();
                    Department newDep = new Department(ids, depName);
                    newSeller.setDepartment(newDep);
                    sellerDao.update(newSeller);
                    System.out.println(blue + "Department updated successfully!" + reset);
                }
                case 0 -> System.out.println("leaving...");
            }
        }

        private void deleteSeller() {
            System.out.println(green + "Type one Id For Delete: " + reset);
            int id = sc.nextInt();
            sellerDao.deleteById(id);
        }
    }
