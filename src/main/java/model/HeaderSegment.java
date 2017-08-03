package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class HeaderSegment {
    private String segmentTag; // M
    private String version; // M
    private Date versionDate; // M
    private String memberCode; // M
    private String cycleIdentification; // O
    private Date reportDate; // M
    private String authorizationCode; // M
    private String memberData; // O

    public String getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(Date versionDate) {
        this.versionDate = versionDate;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getCycleIdentification() {
        return cycleIdentification;
    }

    public void setCycleIdentification(String cycleIdentification) {
        this.cycleIdentification = cycleIdentification;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getMemberData() {
        return memberData;
    }

    public void setMemberData(String memberData) {
        this.memberData = memberData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        sb.append(segmentTag).append("\t");
        sb.append(version).append("\t");

        String versionDateString = versionDate == null ? "" : sdf.format(versionDate);
        sb.append(versionDateString).append("\t");

        sb.append(memberCode).append("\t");
        sb.append(cycleIdentification).append("\t");

        String reportDateString  = reportDate == null ? "" : sdf.format(reportDate);
        sb.append(reportDateString).append("\t");

        sb.append(authorizationCode).append("\t");
        sb.append(memberData);
        return sb.toString();
    }
}
