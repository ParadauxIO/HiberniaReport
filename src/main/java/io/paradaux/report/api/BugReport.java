/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import io.paradaux.report.Report;

public class BugReport extends GenericReport {

    String server;
    String reporter;
    String reporterUUID;
    String issue;

    String baseUserIconUrl = "https://crafatar.com/renders/head/%uuid%?size=10&overlay";
    String baseUserUrl = "https://namemc.com/";

    public BugReport(String server, String reporter, String reporterUUID, String issue) {
        super(Report.getConfigurationCache().getWebhookUrl(), Report.getConfigurationCache().getAvatarUrl(), Report.getConfigurationCache().getPlayerUsername());
        this.server = server;
        this.reporter = reporter;
        this.reporterUUID = reporterUUID;
        this.issue = issue;
    }

    public BugReport(String server, String reporter, String reporterUUID, String issue, String webhookUrl, String avatarUrl, String userName) {
        super(webhookUrl, avatarUrl, userName);
        this.server = server;
        this.reporter = reporter;
        this.reporterUUID = reporterUUID;
        this.issue = issue;
    }

    @Override
    public WebhookEmbed createEmbed() {
        String userIconUrl = baseUserIconUrl.replace("%uuid%", reporterUUID);
        String UserUrl = baseUserUrl + reporter;

        WebhookEmbedBuilder embedBuilder = new WebhookEmbedBuilder()
                .setDescription("**Issue**: " + issue)
                .setColor(0x4896a2)
                .addField(new WebhookEmbed.EmbedField(true, "Server:", server))
                .addField(new WebhookEmbed.EmbedField(true, "Reporter:", reporter));

        if(!(reporterUUID.equals("Console"))) {
            embedBuilder
                    .setAuthor(new WebhookEmbed.EmbedAuthor(reporter + " has reported a bug.", userIconUrl, UserUrl))
                    .addField(new WebhookEmbed.EmbedField(true, "UUID:", reporterUUID));
        } else {
            embedBuilder.setAuthor(new WebhookEmbed.EmbedAuthor("Console has reported a bug.", "", ""));
        }

        return embedBuilder.build();
    }

    @Override
    public WebhookMessage newMessage(WebhookEmbed embed) {
        return super.newMessage(embed);
    }

    @Override
    public void sendWebhook(WebhookMessage message) {
        super.sendWebhook(message);
    }


    @Override
    public void execute() {
        sendWebhook(newMessage(createEmbed()));
    }
}
