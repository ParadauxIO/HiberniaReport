/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package co.paradaux.Hibernia.Report;

import co.paradaux.Hibernia.Report.cmds.ReportCMD;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Report extends JavaPlugin {

    private static FileConfiguration config;
    public static FileConfiguration getConfigFile() { return config; }

    @Override
    public void onEnable() {

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("report").setExecutor(new ReportCMD(this));
        config = getConfig();

    }

    @Override
    public void onDisable() {}
}
