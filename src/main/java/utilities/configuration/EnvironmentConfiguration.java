package utilities.configuration;

public class EnvironmentConfiguration {
    public static final String DATABASE_NAME = "jpos";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "";
    public static final String DATABASE_HOST = "localhost";
    public static final String DATABASE_PORT = "3306";
    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = String.format("jdbc:mysql://%s:%s/%s", DATABASE_HOST, DATABASE_PORT, DATABASE_NAME);

    public static final String SERVER_PORT = "8080";
    public static final String SERVER_HOST = "localhost";    
}
