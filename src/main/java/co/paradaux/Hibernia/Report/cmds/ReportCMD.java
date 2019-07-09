package co.paradaux.Hibernia.Report.cmds;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import co.paradaux.Hibernia.Report.Report;
import co.paradaux.Hibernia.Report.api.BugReport;
import co.paradaux.Hibernia.Report.api.PlayerReport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;

public class ReportCMD implements CommandExecutor {
    FileConfiguration config;
    Plugin plugin;

    public ReportCMD(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        config = Report.getConfigFile();

        if (!sender.hasPermission("hibernia.report")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.no-permission")));
            return true;
        }

        if (config.getString("settings.webhook-url") == null) {
            sender.sendMessage(ChatColor.RED + "You forgot to set your webhook url in the configuration file.");
            return true;
        }

        String webhook = config.getString("settings.webhook-url");
        String servername = config.getString("settings.server-name");
        String avatarUrl = config.getString("settings.avatar-url");
        String brtitle = config.getString("bug-report.username");
        String prtitle = config.getString("player-report.username");
        String reportSentMsg = ChatColor.translateAlternateColorCodes('&', config.getString("messages.report-sent"));

        if (args.length <= 2) {
            sendHelpMenu(sender);
            return true;
        }

        WebhookClient client = new WebhookClientBuilder(webhook).build();

        if (args[0].equalsIgnoreCase("bug")) {

            BugReport issue = new BugReport(servername, sender.getName(), getIssue(args, 1), avatarUrl, brtitle);

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> client.send(issue.buildMessage()));
            sender.sendMessage(reportSentMsg);
            return true;

        } else if (args[0].equalsIgnoreCase("player")) {

            PlayerReport issue = new PlayerReport(servername, sender.getName(), args[1], getIssue(args, 2), avatarUrl, prtitle);

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> client.send(issue.buildMessage()));
            sender.sendMessage(reportSentMsg);
            return true;

        } else {

            sendHelpMenu(sender);
            return true;

        }

    }

    public void sendHelpMenu(CommandSender sender) {
        config = Report.getConfigFile();

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.help-menu-one")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.help-menu-two")));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.help-menu-three")));

    }

    public String getIssue(String[] args, int num){
        StringBuilder sb = new StringBuilder();
        for(int i = num; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        return sb.toString().trim();
    }

}
