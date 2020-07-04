/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import io.paradaux.report.api.ConfigurationCache;
import io.paradaux.report.api.VersionChecker;
import io.paradaux.report.cmds.ReportCMD;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Report extends JavaPlugin {

    private static Logger logger;
    public static Logger getPluginLogger() { return logger; }

    private static FileConfiguration config;
    public static FileConfiguration getConfigFile() { return config; }

    private static ConfigurationCache configurationCache;
    public static ConfigurationCache getConfigurationCache() { return configurationCache; }

    private static TaskChainFactory taskChainFactory;
    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }
    public static <T> TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }

    @Override
    public void onEnable() {

        config = getConfig();
        configurationCache = new ConfigurationCache(config);
        taskChainFactory = BukkitTaskChainFactory.create(this);

        startupMessage();
        handleConfiguration();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void startupMessage() {
        logger.log(Level.FINE, "\n" +
                "+ ------------------------------------ +\n" +
                "|     Running HiberniaReport v1.1.0    |\n" +
                "|       © Rían Errity (Paradaux)       |\n" +
                "|         https://paradaux.io          |\n" +
                "+ ------------------------------------ +\n" +
                "\n" +
                "Are you looking for a freelance plugin developer?\n" +
                "Think no further than Paradaux.io! rian@paradaux.io / Rían#6500"
        );
    }


    public void handleConfiguration() {
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    public void registerCommands() {
        getCommand("report").setExecutor(new ReportCMD());
    }

    public void versionChecker() {
        new VersionChecker(this, 69145).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("There are no new updates available");
            } else {
                logger.info("There is a new update available. \n Please update: https://www.spigotmc.org/resources/autobroadcast.69377/");
            }
        });
    }

}
