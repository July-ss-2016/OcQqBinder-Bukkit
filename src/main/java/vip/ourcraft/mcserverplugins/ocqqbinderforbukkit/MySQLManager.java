package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLManager {
    private Connection con;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    public MySQLManager(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean executeStatement(String sql) {
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Connection getCon() {
        return con;
    }

    public boolean reconnect() {
        Connection oldCon = con;

        if (connect()) {
            try {
                oldCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con != null;
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
