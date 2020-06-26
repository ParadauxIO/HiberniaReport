/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package co.paradaux.Hibernia.Report.api;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import org.bukkit.Bukkit;

public class PlayerReport {

    String server;
    String reporter;
    String reportee;
    String reporteeUUID;
    String issue;
    String avatarUrl;
    String userName;


    public PlayerReport ( String server, String reporter, String reportee, String reason, String avatarUrl, String userName) {

        this.server = server;
        this.reporter = reporter;

        this.reportee = reportee;
        this.reporter = reporter;
        this.reporteeUUID = Bukkit.getOfflinePlayer(reportee).getUniqueId().toString();

        this.issue = reason;

        this.avatarUrl = avatarUrl;
        this.userName = userName;

    }

    public WebhookEmbed buildEmbed() {

        WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
        builder.setColor(0x4896a2);

        builder.addField(new WebhookEmbed.EmbedField(true, "Server: ", server));
        builder.addField(new WebhookEmbed.EmbedField(true, "Reported by: ", reporter));
        builder.addField(new WebhookEmbed.EmbedField(true, "Reported Player: ", reportee));
        builder.addField(new WebhookEmbed.EmbedField(true, "Player UUID: ", reporteeUUID));
        builder.addField(new WebhookEmbed.EmbedField(false, "Issue: ", issue));
        return builder.build();

    }

    public WebhookMessage buildMessage() {

        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.addEmbeds(buildEmbed());
        builder.setAvatarUrl(avatarUrl);
        builder.setUsername(userName);

        return builder.build();

    }

}
