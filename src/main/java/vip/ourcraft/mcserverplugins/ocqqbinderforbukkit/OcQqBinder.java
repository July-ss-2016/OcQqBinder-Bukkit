package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class OcQqBinder extends JavaPlugin {
    private static OcQqBinder instance;
    private Settings settings;
    private MySQLManager mySQLManager;
    private QqBinderManager qqBinderManager;

    public void onEnable() {
        instance = this;
        this.settings = new Settings();

        loadConfig();

        this.mySQLManager = new MySQLManager(settings.getMySQLHost(), settings.getMySQLPort(), settings.getMySQLDatabase(), settings.getMySQLUserName(), settings.getMySQLPassword());
        this.qqBinderManager = new QqBinderManager(this);

        if (!mySQLManager.connect()) {
            getLogger().warning("MySQL连接失败!");
            setEnabled(false);
            return;
        }

        // UNIQUE KEY 保证了qq和pn的唯一性
        if (!mySQLManager.executeStatement("CREATE TABLE IF NOT EXISTS " + settings.getMySQLTableName() + "(player_name VARCHAR(16), qq BIGINT, UNIQUE KEY `player_name` (`player_name`), UNIQUE KEY `qq` (`qq`))")) {
            getLogger().warning(settings.getMySQLTableName() + "表 创建失败!");
            setEnabled(false);
            return;
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") && !new PlaceholderAPIHook(this, "ocqqbinder").hook()) {
            getLogger().warning("PlaceholderAPI Hook 失败!");
        }

        getCommand("qq").setExecutor(new PlayerCommand(this));
        runMySQLReconnectTask();
        getLogger().info("初始化完毕!");
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        mySQLManager.disconnect();
    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();

        FileConfiguration config = getConfig();
        ConfigurationSection mysqlSection = config.getConfigurationSection("mysql");

        settings.setMySQLHost(mysqlSection.getString("host"));
        settings.setMySQLPort(mysqlSection.getInt("port"));
        settings.setMySQLUserName(mysqlSection.getString("username"));
        settings.setMySQLPassword(mysqlSection.getString("password"));
        settings.setMySQLDatabase(mysqlSection.getString("database"));
        settings.setMySQLTableName(mysqlSection.getString("tablename"));
        settings.setMySQLReconnectInterval(mysqlSection.getInt("reconnect_interval"));
    }

    public QqBinderManager getQqBinderManager() {
        return qqBinderManager;
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public Settings getSettings() {
        return settings;
    }

    public static OcQqBinder getInstance() {
        return instance;
    }

    private void runMySQLReconnectTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if (mySQLManager.reconnect()) {
                getLogger().info("MySQL 重连成功!");
                return;
            }

            getLogger().warning("MySQL 重连失败!");
        }, settings.getMySQLReconnectInterval() * 20L, settings.getMySQLReconnectInterval() * 20L);
    }
}
