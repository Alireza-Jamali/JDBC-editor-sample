package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AezA
 */
@ManagedBean(name = "db")
@SessionScoped
public class DatabaseConnection {

    private String username;
    private String password;
    private String url;
    private Connection connection;
    private String connectionStatus;
    private String cnb = "cn.png";

    public String getCnb() {
        return cnb;
    }

    public void setCnb(String cnb) {
        this.cnb = cnb;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            connection = null;
            ex.printStackTrace();
        }

        if (connection != null) {
            setCnb("cn2.png");
        } else {
            setCnb("cn.png");
        }
    }
    
}
