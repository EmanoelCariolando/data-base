package Application;
import db.DB;
import db.DbIntegratException;

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

        System.out.print("Enter which ID you want to delete: ");
        departmentId = sc.nextInt();

        try{
            conn = DB.getConn();

            ps = conn.prepareStatement(
                    "DELETE FROM seller "
                            + "WHERE "
                            + "(ID = ?)");

            ps.setInt(1, departmentId);

            int rows = ps.executeUpdate();
            System.out.println("Done!");
            System.out.println("Total Rows Affected: " + rows);

        }
        catch (SQLException e) {
            throw new DbIntegratException(e.getMessage());
        }
        finally {
            DB.closeStatment(ps);
        }

    }
}

