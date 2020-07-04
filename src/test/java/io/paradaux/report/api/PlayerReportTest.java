/*
 * Copyright © 2020 Property of Rían Errity Licensed under GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007. See <LICENSE.md>
 */

package io.paradaux.report.api;

import org.junit.Before;
import org.junit.Test;

public class PlayerReportTest {

    PlayerReport report;

    @Before
    public void setUp() {

        String server = "HiberniaMc";
        String reporter = "Paradaux";
        String reportee = "PlotIKidYouNot";
        String reporteeuuid = "a67211e0-ea6c-4a22-9634-d23c54878c19";
        String issue = "hacking";

        String webhookurl = "https://discord.com/api/webhooks/728976559473557645/CWUA9jSGEUTnhFTUp0ak9Z8V6iS7guIkx5OytZSoBiqcftKGTiV9wgFkJQIG-wL8ORQ9";
        String avatarurl = "https://cdn.paradaux.io/static/plugin-branding/hiberniareport/report.png";
        String username = "HiberniaReport";


        report = new PlayerReport(server, reporter, reportee, reporteeuuid, issue, webhookurl, avatarurl, username);

    }
    @Test
    public void ReportTest() {
        report.execute();
    }

}