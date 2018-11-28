package ch6.util.jdbc;

import java.sql.*;

public class JdbcUtil {

    private Connection connection;

    public JdbcUtil(final String DB_URL, final String USER, final String PASS) {
        try {
            connect(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connect(final String DB_URL, final String USER, final String PASS) throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Returns ResultSet of JDBC Query as String
     */
    public String query(final String sql) {
        final StringBuilder sb = new StringBuilder();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            sb.append("[");

            while (rs.next()) {

                if (!rs.isFirst()) {
                    sb.append(", ");

                }
                sb.append("{");

                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) sb.append(",  ");
                    String columnValue = rs.getString(i);
                    sb.append(rsmd.getColumnName(i) + "=" + columnValue);
                }
                sb.append("}");

            }
            sb.append("]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
