package utilities.database;

import java.sql.DriverManager;
import java.sql.Connection;
import utilities.configuration.EnvironmentConfiguration;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Class.forName(EnvironmentConfiguration.DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            return DriverManager.getConnection(EnvironmentConfiguration.DATABASE_URL, EnvironmentConfiguration.DATABASE_USERNAME, EnvironmentConfiguration.DATABASE_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
