/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VersionCheckerTest {

    VersionChecker vChecker;
    String currentVersion = "1.1.0";

    @Test
    public void testVersionChecker() {
        new VersionChecker( 69145).getVersion(version -> {
            assertEquals("1.1.0", version);
            if (currentVersion.equalsIgnoreCase(version)) {
                System.out.println("There are no new updates available");
            } else {
                System.out.println("There is a new update available. \n Please update: https://www.spigotmc.org/resources/hibernia-report-legacy-show-player-bug-reports-on-your-discord-server-webook-not-supported.69145/");
            }
        });
    }


}