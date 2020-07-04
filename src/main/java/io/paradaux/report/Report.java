/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import io.paradaux.report.api.ConfigurationCache;
import io.paradaux.report.cmds.ReportCMD;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Report extends JavaPlugin {

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

        handleConfiguration();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    public void handleConfiguration() {
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    public void registerCommands() {
        getCommand("report").setExecutor(new ReportCMD());
    }

}
