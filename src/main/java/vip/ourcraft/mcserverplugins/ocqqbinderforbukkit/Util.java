package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {
    public static void sendMsg(CommandSender cs, String msg) {
        cs.sendMessage("§a[OCQB] §c" + ChatColor.translateAlternateColorCodes('&', msg));
    }
}
