package io.paradaux.report.reporting;

public class ReportBuilder implements IBuilder {

    String userIconUrlTemplate;
    String userLinkUrlTemplate;

    String serverName;
    String reporter;
    String reporterUUID;
    String reportee;
    String reporteeUUID;
    String issue;

    public ReportBuilder(String userIconUrlTemplate, String userLinkUrlTemplate, String serverName, String reporter, String reporterUUID, String reportee, String reporteeUUID, String issue) {
        this.userIconUrlTemplate = userIconUrlTemplate;
        this.userLinkUrlTemplate = userLinkUrlTemplate;
        this.serverName = serverName;
        this.reporter = reporter;
        this.reporterUUID = reporterUUID;
        this.reportee = reportee;
        this.reporteeUUID = reporteeUUID;
        this.issue = issue;
    }

    public ReportBuilder setUserIconUrlTemplate(String str) {
        this.userIconUrlTemplate = str;
        return this;
    }

    public ReportBuilder setUserLinkUrlTemplate(String str) {
        this.userLinkUrlTemplate = str;
        return this;
    }

    public ReportBuilder setServerName(String str) {
        this.serverName = str;
        return this;
    }

    public ReportBuilder setReporter(String str) {
        this.reporter = str;
        return this;
    }

    public ReportBuilder setReporterUUID(String str) {
        this.reporterUUID = str;
        return this;
    }

    public ReportBuilder setReportee(String str) {
        this.reportee = str;
        return this;
    }

    public ReportBuilder setReporteeUUID(String str) {
        this.reporteeUUID = str;
        return this;
    }

    public ReportBuilder setIssue(String str) {
        this.issue = str;
        return this;
    }

    @Override
    public Report build() {
        return new Report(userIconUrlTemplate, userLinkUrlTemplate, serverName, reporter, reporterUUID, reportee, reporteeUUID, issue);
    }

}
