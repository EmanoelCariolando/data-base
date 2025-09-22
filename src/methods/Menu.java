package methods;

import java.util.Scanner;

public class Menu {
     //colors
     String blue = "\u001B[34m";
     String reset = "\u001B[0m";

    private final Scanner sc;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    public int choices() {
     try {
        Thread.sleep(1700); // pausa 2 segundo a cada loop
        System.out.println("[1] = FindById");
        System.out.println("[2] = FindByDepartment");
        System.out.println("[3] = FindAll");
        System.out.println("[4] = Insert");
        System.out.println("[5] = Update");
        System.out.println("[6] = Delete");
        System.out.println("[0] = Exit");
        System.out.print(blue + "choice: " +  reset);
        return sc.nextInt();

    }
     catch (InterruptedException e) {
         throw new RuntimeException(e);
     }
    }
    public int updateChoices(){
            System.out.println("what do you want to modify: ");
            System.out.println("[1] = Name");
            System.out.println("[2] = Email");
            System.out.println("[3] = BirthDate");
            System.out.println("[4] = Base Salary");
            System.out.println("[5] = Department Id");
            System.out.println("[0] = Exit");
            System.out.print("choice: ");
            return sc.nextInt();

        }
    }
