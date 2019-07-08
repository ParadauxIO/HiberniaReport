package co.paradaux.Hibernia.Report.cmds;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import co.paradaux.Hibernia.Report.Report;
import co.paradaux.Hibernia.Report.api.BugReport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class ReportCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = Report.getConfigFile();
        String webhook = config.getString("settings.webhook-url");
        String servername = config.getString("settings.server-name");
        String avatarUrl = config.getString("settings.avatar-url");
        String title = config.getString("bug-report.title");

        if(webhook.equalsIgnoreCase("")) {
            sender.sendMessage("INVALID WEBHOOK");
        }

        if (args.length <= 2) {
            return false;
        }

        System.out.println("Webhook: " + webhook);
        System.out.println("Server Name: " + servername);

        if (args[0].equalsIgnoreCase("bug")) {
            WebhookClient client = new WebhookClientBuilder(webhook).build();

            BugReport issue = new BugReport(servername, sender.getName(), getIssue(args, 1), avatarUrl, title);

            client.send(issue.buildMessage());

        }

        return true;
    }




    public String getIssue(String[] args, int num){ //You can use a method if you want
        StringBuilder sb = new StringBuilder(); //We make a String Builder
        for(int i = num; i < args.length; i++) { //We get all the arguments with a for loop
            sb.append(args[i]).append(" "); //We add the argument and the space
        }
        return sb.toString().trim(); //We return the message
    }


}
