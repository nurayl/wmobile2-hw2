package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtils {

    static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/homework_database");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql, String... args) {

        try {
            Connection connection = DBUtils.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }

            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL:Update failed; " + "Query: " + sql);
        }

        return null;
    }

    public static void singleQuery(String sql, String... args) {
        try {
            Connection connection = DBUtils.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                statement.setString(i + 1, args[i]);
            }

            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
