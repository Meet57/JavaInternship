package Driver;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database db = new Database("jdbc:mysql://localhost:3306/", "root", "password");
        System.out.println(db.showDbs());

        db.close();
    }
}
