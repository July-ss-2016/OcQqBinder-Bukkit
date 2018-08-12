package vip.ourcraft.mcserverplugins.ocqqbinderforbukkit;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlaceholderAPIHook extends EZPlaceholderHook {
    private QqBinderManager qqBinderManager;

    public PlaceholderAPIHook(Plugin plugin, String identifier) {
        super(plugin, identifier);

        this.qqBinderManager = OcQqBinder.getInstance().getQqBinderManager();
    }

    public String onPlaceholderRequest(Player player, String arg) {
        if (arg.equalsIgnoreCase("num")) {
            long qq = qqBinderManager.getQqByPlayerName(player.getName());

            return qq == 0L ? "无" : String.valueOf(qq);
        }

        return null;
    }
}
