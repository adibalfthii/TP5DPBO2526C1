package icip_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/db_people";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Kosongkan jika pakai XAMPP default

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Memuat driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
        }
        return conn;
    }
}