package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

public class Settings {
    private String mySQLHost;
    private int mySQLPort;
    private String mySQLUserName;
    private String mySQLPassword;
    private String mySQLDatabase;
    private String mySQLTableName;
    private int mySQLReconnectInterval;

    public int getMySQLPort() {
        return mySQLPort;
    }

    public void setMySQLPort(int mySQLPort) {
        this.mySQLPort = mySQLPort;
    }

    public String getMySQLHost() {
        return mySQLHost;
    }

    public void setMySQLHost(String mySQLHost) {
        this.mySQLHost = mySQLHost;
    }

    public String getMySQLUserName() {
        return mySQLUserName;
    }

    public void setMySQLUserName(String mySQLUserName) {
        this.mySQLUserName = mySQLUserName;
    }

    public String getMySQLPassword() {
        return mySQLPassword;
    }

    public void setMySQLPassword(String mySQLPassword) {
        this.mySQLPassword = mySQLPassword;
    }

    public String getMySQLDatabase() {
        return mySQLDatabase;
    }

    public void setMySQLDatabase(String mySQLDatabase) {
        this.mySQLDatabase = mySQLDatabase;
    }

    public String getMySQLTableName() {
        return mySQLTableName;
    }

    public void setMySQLTableName(String mySQLTableName) {
        this.mySQLTableName = mySQLTableName;
    }

    public int getMySQLReconnectInterval() {
        return mySQLReconnectInterval;
    }

    public void setMySQLReconnectInterval(int mySQLReconnectInterval) {
        this.mySQLReconnectInterval = mySQLReconnectInterval;
    }
}
