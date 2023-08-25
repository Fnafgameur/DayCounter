package fr.fnafgameur.daycounter.tasks;

import fr.fnafgameur.daycounter.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DayCountRefreshTask extends BukkitRunnable {

    private final Main main;

    public DayCountRefreshTask(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        if (main.getConfig().getBoolean("actionbar")) {
            sendActionBar();
        }
        if (main.getConfig().getBoolean("scoreboard")) {
            sendScoreboard();
        }
    }
    private void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String worldName = player.getWorld().getName();
            if (worldName.equalsIgnoreCase("world")) {
                worldName = "§6Overworld";
            }
            else if (worldName.equalsIgnoreCase("world_nether")) {
                worldName = "§6Nether";
            }
            else if (worldName.equalsIgnoreCase("world_the_end")) {
                worldName = "§6End";
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aDay number : §6" + player.getWorld().getFullTime() / 24000 + " §aWorld : §6" + worldName));
        }
    }
    private void sendScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard().getObjective("daycounter").getScore("§aDay number : §6").setScore((int)(player.getWorld().getFullTime() / 24000));
        }
    }
}
