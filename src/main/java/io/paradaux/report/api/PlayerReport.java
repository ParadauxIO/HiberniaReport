/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import io.paradaux.report.Report;

public class PlayerReport extends GenericReport {

    ConfigurationCache config = Report.getConfigurationCache();


    String server;
    String reporter;
    String reportee;
    String reporteeUUID;
    String issue;

    String baseUserIconUrl = "https://crafatar.com/renders/head/%uuid%?size=10&overlay";
    String baseUserUrl = "https://namemc.com/";

    public PlayerReport(String server, String reporter, String reportee, String reporteeUUID, String issue) {
        super(Report.getConfigurationCache().getWebhookUrl(), Report.getConfigurationCache().getAvatarUrl(), Report.getConfigurationCache().getPlayerUsername());
        this.server = server;
        this.reporter = reporter;
        this.reportee = reportee;
        this.reporteeUUID = reporteeUUID;
        this.issue = issue;
    }

    public PlayerReport(String server, String reporter, String reportee, String reporteeUUID, String issue, String webhookurl, String avatarurl, String username) {
        super(webhookurl, avatarurl, username);
        this.server = server;
        this.reporter = reporter;
        this.reportee = reportee;
        this.reporteeUUID = reporteeUUID;
        this.issue = issue;
    }

    @Override
    public WebhookEmbed createEmbed() {
        String userIconUrl = baseUserIconUrl.replace("%uuid%", reporteeUUID);
        String UserUrl = baseUserUrl + reportee;

        return new WebhookEmbedBuilder()
                .setAuthor(new WebhookEmbed.EmbedAuthor(reportee + " has been reported.", userIconUrl, UserUrl))
                .setDescription("**Issue**: " + issue)
                .setColor(0x4896a2)
                .addField(new WebhookEmbed.EmbedField(true, "Reported:", reportee))
                .addField(new WebhookEmbed.EmbedField(true, "UUID:", reporteeUUID))
                .addField(new WebhookEmbed.EmbedField(true, "Reporter:", reporter))
                .addField(new WebhookEmbed.EmbedField(true, "Server:", server))
                .build();
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
