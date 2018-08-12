package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor {
    private QqBinderManager qqBinderManager;

    public PlayerCommand(OcQqBinder plugin) {
        this.qqBinderManager = plugin.getQqBinderManager();
    }

    public boolean onCommand(CommandSender cs, Command cmd, String lable, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("info")) {
            if (!(cs instanceof Player)) {
                Util.sendMsg(cs, "该命令必须由玩家来执行!");
                return true;
            }

            long qq = qqBinderManager.getQqByPlayerName(cs.getName());

            Util.sendMsg(cs, qq == 0L ? "&c您尚未绑定QQ!" : "&d您已绑定QQ: " + qq+ ".");
            return true;
        }

        Util.sendMsg(cs, "&f/qq info &b> &f查看您绑定的QQ");
        return false;
    }
}
