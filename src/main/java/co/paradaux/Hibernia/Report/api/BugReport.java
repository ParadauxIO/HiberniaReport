/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package co.paradaux.Hibernia.Report.api;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class BugReport {

    String server;
    String reporter;
    String issue;
    String avatarUrl;
    String userName;

    public BugReport( String server, String reporter, String issue, String avatarUrl, String userName) {

        this.server = server;
        this.reporter = reporter;
        this.issue = issue;
        this.avatarUrl = avatarUrl;
        this.userName = userName;

    }

    public WebhookEmbed buildEmbed() {

        WebhookEmbedBuilder builder = new WebhookEmbedBuilder();
        builder.setColor(0x4896a2);

        builder.addField(new WebhookEmbed.EmbedField(true, "Server: ", server));
        builder.addField(new WebhookEmbed.EmbedField(true, "Reported by: ", reporter));
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
