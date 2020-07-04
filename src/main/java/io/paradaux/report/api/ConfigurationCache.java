/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationCache {

    String settings_webhookUrl;
    String settings_serverName;
    String settings_avatarUrl;

    String bugreport_username;
    String playerreport_username;

    String messages_reportSent;
    String messages_helpMenuOne;
    String messages_helpMenuTwo;
    String messages_helpMenuThree;
    String messages_noPermission;

    double configVersion;

    public ConfigurationCache(FileConfiguration config) {
        this.settings_webhookUrl = config.getString("settings.webhook-url");
        this.settings_serverName = config.getString("settings.server-name");
        this.settings_avatarUrl = config.getString("settings.avatar-url");

        this.bugreport_username = config.getString("bug-report.username");

        this.playerreport_username = config.getString("player-report.username");

        this.messages_reportSent = config.getString("messages.report-sent");
        this.messages_helpMenuOne = config.getString("messages.help-menu-one");
        this.messages_helpMenuTwo = config.getString("messages.help-menu-two");
        this.messages_helpMenuThree = config.getString("messages.help-menu-three");
        this.messages_noPermission = config.getString("no-permission");

        this.configVersion = config.getDouble("config-version");
    }

    public String getWebhookUrl()  { return settings_webhookUrl; }
    public String getServerName() { return settings_serverName; }
    public String getAvatarUrl() { return settings_avatarUrl; }
    public String getBugUsername() { return bugreport_username; }
    public String getPlayerUsername() { return playerreport_username; }

    public String getMessageHelpMenu() {
        return messages_helpMenuOne + "\n" + messages_helpMenuTwo + "\n" + messages_helpMenuThree;
    }

    public String getMessageReportSent() { return messages_reportSent; }
    public String getMessageNoPermission() { return messages_noPermission; }

}
