/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class GenericReport {

    WebhookClient client;
    WebhookMessageBuilder messageBuilder;

    final private String userName;
    final private String avatarUrl;

    public GenericReport(String webhookUrl, String avatarUrl, String userName) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;

        client = createClient(webhookUrl);
        messageBuilder = new WebhookMessageBuilder();
    }

    public WebhookClient createClient(String webhookUrl) {
        WebhookClientBuilder builder = new WebhookClientBuilder(webhookUrl);
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("hiberniareport");
            thread.setDaemon(true);
            return thread;
        });

        builder.setWait(true);
        return builder.build();
    }

    public WebhookClient getClient() {
        return client;
    }

    public WebhookEmbed createEmbed() {
        return new WebhookEmbedBuilder().build();
    }

    public void sendWebhook(WebhookMessage message) {
        try {
            getClient().send(message).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebhookMessage newMessage(WebhookEmbed embed) {
        return messageBuilder.setUsername(userName).setAvatarUrl(avatarUrl).addEmbeds(embed).build();
    }

    public void execute() {

    }
}
