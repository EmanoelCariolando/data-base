package Application;
import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String name = "";
        String email = "";
        String birthDate;
        double baseSalary;
        int departmentId;

        Connection conn = null;
        PreparedStatement ps = null;

            System.out.print("Type Your Name: ");
            name = sc.nextLine();
            System.out.print("Type Your Email: ");
            email = sc.nextLine();
            System.out.print("Type your birthdate (dd/mm/yyyy): ");
            birthDate = sc.nextLine();
            System.out.print("Type your Salary: ");
            baseSalary = sc.nextDouble();
            System.out.print("Type your departmentId (from 1 to 4): ");
            departmentId = sc.nextInt();


        try{
            conn = DB.getConn();

            ps = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?,?,?,?,?)");
                   ps.setString(1, name);
                   ps.setString(2,email);
                   ps.setDate(3, new java.sql.Date(sdf.parse(birthDate).getTime()));
                   ps.setDouble(4, baseSalary);
                   ps.setInt(5, departmentId);

                   int rows = ps.executeUpdate();
                   System.out.println("Done! Rows Affected: " + rows);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatment(ps);
        }

    }
    }
