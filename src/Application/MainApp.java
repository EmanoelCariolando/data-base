package Application;
import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) throws ParseException, SQLException {

        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        Statement ps = null;


        try{
            conn = DB.getConn();

            conn.setAutoCommit(false);

            ps = conn.createStatement();

            int oneRow = ps.executeUpdate("UPDATE seller set BaseSalary = 3090 WHERE DepartmentId = 2");

            int twoRow = ps.executeUpdate("UPDATE seller set BaseSalary = 5090 WHERE DepartmentId = 1");

            conn.commit();

            System.out.println("rows1" + oneRow);
            System.out.println("rows2" + twoRow);


        }
        catch (SQLException e) {
            conn.rollback();
        }
        finally {
            DB.closeStatment(ps);
        }

    }
}

