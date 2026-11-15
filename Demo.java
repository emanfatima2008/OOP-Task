import java.sql.*;

public class Demo {
    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/college", "root", "#eman@123fatima"
        );
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            System.out.println(id + " " + name);
        }
        rs.close();
        stmt.close();
        con.close();
    }
}