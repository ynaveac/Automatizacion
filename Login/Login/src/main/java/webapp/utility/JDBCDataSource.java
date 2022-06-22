package webapp.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class JDBCDataSource
{
  /**
     * JDBC Database connection pool ( DCP )
     */
    private static JDBCDataSource datasource;
    private JDBCDataSource() {
    }
    private ComboPooledDataSource cpds = null;
    /**
     * Create instance of Connection Pool
     *
     * @return
     */
    public static JDBCDataSource getInstance() {
        if (datasource == null) {
        	/*
            ResourceBundle rb = ResourceBundle.getBundle("com.javawebapp.bundle.system");
            datasource = new JDBCDataSource();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            datasource.cpds.setJdbcUrl(rb.getString("url"));
            datasource.cpds.setUser(rb.getString("username"));
            datasource.cpds.setPassword(rb.getString("password"));
            datasource.cpds.setInitialPoolSize(new Integer((String) rb .getString("initialPoolSize")));
            datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
            datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
            datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
            datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
            */
        	
            datasource = new JDBCDataSource();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            datasource.cpds.setJdbcUrl("jdbc:mysql://localhost:3307/javawebapp");
            datasource.cpds.setUser("root");
            datasource.cpds.setPassword("root");
            datasource.cpds.setInitialPoolSize(10);
            datasource.cpds.setAcquireIncrement(10);
            datasource.cpds.setMaxPoolSize(100);
            datasource.cpds.setMaxIdleTime(DataUtility.getInt("100"));
            datasource.cpds.setMinPoolSize(10);
        }
        return datasource;
    }
    /**
     * Gets the connection from ComboPooledDataSource
     *
     * @return connection
     */
    public static Connection getConnection() throws Exception {
        return getInstance().cpds.getConnection();
    }
    /**
     * Closes a connection4
     *
     * @param connection
     * @throws Exception
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }
    
}