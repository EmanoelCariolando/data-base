package methods;

import java.util.Scanner;

public class Menu {

    private Scanner sc;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    public int choices() {
     try {
        Thread.sleep(1700); // pausa 2 segundo a cada loop
        System.out.println("[1] = FindById");
        System.out.println("[2] = FindByDepartment");
        System.out.println("[3] = DeleteById");
        System.out.println("[4] = insert");
        System.out.println("[5] = Update");
        System.out.println("[6] = FindAll");
        System.out.println("[0] = Exit");
        System.out.print("choice: ");
        return sc.nextInt();

    }
     catch (InterruptedException e) {
         throw new RuntimeException(e);
     }
    }
}
