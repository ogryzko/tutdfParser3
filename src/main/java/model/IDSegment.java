package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class IDSegment {
    private String segmentTag; // M
    private Integer idType; // M
    private String seriesNumber; // C
    private String idNumber; // C
    private Date issueDate; // C
    private String issueAuthority; // C
    private String issueLocation; // O
    private String oldIDNumber; // O

    public String getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueLocation() {
        return issueLocation;
    }

    public void setIssueLocation(String issueLocation) {
        this.issueLocation = issueLocation;
    }

    public String getOldIDNumber() {
        return oldIDNumber;
    }

    public void setOldIDNumber(String oldIDNumber) {
        this.oldIDNumber = oldIDNumber;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        StringBuilder sb = new StringBuilder();
        sb.append(segmentTag).append("\t");
        sb.append(idType).append("\t");
        sb.append(seriesNumber).append("\t");
        sb.append(idNumber).append("\t");

        String issueDateString = (issueDate == null) ? "" : sdf.format(issueDate);
        sb.append(issueDateString).append("\t");

        sb.append(issueAuthority).append("\t");
        sb.append(issueLocation).append("\t");
        sb.append(oldIDNumber);
        return sb.toString();
    }
}
