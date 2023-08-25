package fr.fnafgameur.daycounter;

import fr.fnafgameur.daycounter.commands.DayCounterCommand;
import fr.fnafgameur.daycounter.commands.DayCounterTabCompleter;
import fr.fnafgameur.daycounter.tasks.DayCountRefreshTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.ScoreboardManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initScoreboard();
        getCommand("daycounter").setExecutor(new DayCounterCommand(this));
        getCommand("daycounter").setTabCompleter(new DayCounterTabCompleter(this));
        new DayCountRefreshTask(this).runTaskTimer(this, 0, 40);
        Bukkit.getLogger().info("DayCounter is enabled !");
    }

    @Override
    public void onDisable() {
        deleteScoreboard();
        Bukkit.getLogger().info("DayCounter is disabled !");
    }

    private void initScoreboard() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        if (scoreboardManager.getMainScoreboard().getObjective("daycounter") == null) {
            scoreboardManager.getMainScoreboard().registerNewObjective("daycounter", "dummy", "§6DayCounter");
            if (getConfig().getBoolean("scoreboard")) {
                scoreboardManager.getMainScoreboard().getObjective("daycounter").setDisplaySlot(DisplaySlot.SIDEBAR);
            }
            else {
                scoreboardManager.getMainScoreboard().getObjective("daycounter").setDisplaySlot(null);
            }
        }
    }
    private void deleteScoreboard() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        if (scoreboardManager.getMainScoreboard().getObjective("daycounter") != null) {
            scoreboardManager.getMainScoreboard().getObjective("daycounter").unregister();
        }
    }
}
