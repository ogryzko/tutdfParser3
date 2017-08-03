package model;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class PhoneNumberSegment {
    private String segmentTag; // M
    private String number; // M
    private Integer type; // C

    public String getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(segmentTag).append("\t");
        sb.append(number).append("\t");
        sb.append(type);
        return sb.toString();
    }
}
