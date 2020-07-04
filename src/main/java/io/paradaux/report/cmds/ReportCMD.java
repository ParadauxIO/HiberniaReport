/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.cmds;

import io.paradaux.report.Report;
import io.paradaux.report.api.BugReport;
import io.paradaux.report.api.ConfigurationCache;
import io.paradaux.report.api.GenericReport;
import io.paradaux.report.api.PlayerReport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCMD implements CommandExecutor {

    ConfigurationCache config;
    GenericReport report;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Stop reporting from console.");
            return true;
        }

        config = Report.getConfigurationCache();

        String webhook = config.getWebhookUrl();
        String server = config.getServerName();
        String reporter = sender.getName();
        String reporterUUID = ((Player) sender).getUniqueId().toString();
        String brtitle = config.getBugUsername();
        String prtitle = config.getPlayerUsername();

        String reportSentMsg = colourise(config.getMessageReportSent());

        if (!sender.hasPermission("hibernia.report")) {
            sender.sendMessage(colourise(config.getMessageNoPermission()));
            return true;
        }

        if (args.length < 2) {
            sendHelpMenu(sender);
            return true;
        }

        if (webhook == null || webhook.equals("")) {
            sender.sendMessage(colourise("&4 Error&7: &cYou have forgotten to set your webhook url in the configuration file."));
            return true;
        }

        if (args[0].equalsIgnoreCase("bug")) {
            report = new BugReport(server, reporter, reporterUUID, getIssue(args, 1));

            Report.newChain().async(() -> report.execute()).execute();
            sender.sendMessage(reportSentMsg);
            return true;

        } else if (args[0].equalsIgnoreCase("player")) {
            OfflinePlayer ReportedPlayer;

            try {
                ReportedPlayer = Bukkit.getOfflinePlayer(args[1]);
            } catch (NullPointerException e) {
                sender.sendMessage(colourise("&4 Invalid Player."));
                return true;
            }

            report = new PlayerReport(server, reporter, args[1], ReportedPlayer.getUniqueId().toString(), getIssue(args, 2));

            Report.newChain().async(() -> report.execute()).execute();
            sender.sendMessage(reportSentMsg);
            return true;

        } else {
            sendHelpMenu(sender);
            return true;
        }

    }

    public void sendHelpMenu(CommandSender sender) {
        String helpmenu = config.getMessageHelpMenuOne() + "\n" + config.getMessageHelpMenuTwo() + "\n" + config.getMessageHelpMenuThree();
        sender.sendMessage(colourise(helpmenu));
    }

    public String colourise(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Concatenates arguments after num.
    public String getIssue(String[] args, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = num; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
