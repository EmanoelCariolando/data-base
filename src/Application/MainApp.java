package Application;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        double baseSalary;
        int departmentId;

        Connection conn = null;
        PreparedStatement ps = null;

        System.out.print("Update your Base Salary: ");
        baseSalary = sc.nextDouble();
        System.out.print("Type your departmentId (from 1 to 4): ");
        departmentId = sc.nextInt();

        try{
            conn = DB.getConn();

            ps = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");

            ps.setDouble(1, baseSalary);
            ps.setInt(2, departmentId);
            int rows = ps.executeUpdate();
            System.out.println("Done!");
            System.out.println("Total Rows Affected: " + rows);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatment(ps);
        }

    }
}

