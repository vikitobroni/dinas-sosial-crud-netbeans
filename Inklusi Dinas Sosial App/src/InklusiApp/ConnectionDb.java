/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InklusiApp;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP 14s
 */
public class ConnectionDb {

    static Connection conn;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inklusi", "root", "");
            System.out.println("koneksi Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: Koneksi Gagal:" + e.getMessage());
            System.exit(0);
        }
        return conn;
    }
}
