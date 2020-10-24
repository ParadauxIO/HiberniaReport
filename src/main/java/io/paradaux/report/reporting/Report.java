package io.paradaux.report.reporting;

public class Report implements IReport, Buildable {

    String userIconUrlTemplate;
    String userLinkUrlTemplate;

    String serverName;
    String reporter;
    String reporterUUID;
    String reportee;
    String reporteeUUID;
    String issue;

    public Report(String userIconUrlTemplate, String userLinkUrlTemplate, String serverName, String reporter, String reporterUUID, String reportee, String reporteeUUID, String issue) {
        this.userIconUrlTemplate = userIconUrlTemplate;
        this.userLinkUrlTemplate = userLinkUrlTemplate;
        this.serverName = serverName;
        this.reporter = reporter;
        this.reporterUUID = reporterUUID;
        this.reportee = reportee;
        this.reporteeUUID = reporteeUUID;
        this.issue = issue;
    }

    @Override
    public void execute() {
        // TODO
    }

}
