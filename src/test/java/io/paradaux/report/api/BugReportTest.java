/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BugReportTest {

    String server = "HiberniaMc";
    String reporter = "Paradaux";
    String reporterUUID = "22d45362-ccfc-4845-a843-3012aa8dbfd9";
    String issue = "duplication glitch found.";

    String webhookurl = "https://discord.com/api/webhooks/728976559473557645/CWUA9jSGEUTnhFTUp0ak9Z8V6iS7guIkx5OytZSoBiqcftKGTiV9wgFkJQIG-wL8ORQ9";
    String avatarurl = "https://cdn.paradaux.io/static/plugin-branding/hiberniareport/report.png";
    String username = "HiberniaReport";

    BugReport report;

    @Before
    public void setUp() {}

    @Test
    public void ReportTest() {
        report = new BugReport(server, reporter, reporterUUID, issue, webhookurl, avatarurl, username);
        report.execute();
    }

    @Test
    public void ConsoleReportTest() {
        reporter = "Console";
        reporterUUID = "Console";
        report = new BugReport(server, reporter, reporterUUID, issue, webhookurl, avatarurl, username);
        report.execute();
    }
}