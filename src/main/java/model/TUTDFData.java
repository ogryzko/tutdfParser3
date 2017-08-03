package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGlushchenko on 28.07.2017.
 */
public class TUTDFData {
    private HeaderSegment headerSegment;
    List<TUTDFEntry> tutdfEntryList = new ArrayList<TUTDFEntry>();
    private TRLRSegment trlrSegment;

    public HeaderSegment getHeaderSegment() {
        return headerSegment;
    }

    public void setHeaderSegment(HeaderSegment headerSegment) {
        this.headerSegment = headerSegment;
    }

    public List<TUTDFEntry> getTutdfEntryList() {
        return tutdfEntryList;
    }

    public void setTutdfEntryList(List<TUTDFEntry> tutdfEntryList) {
        this.tutdfEntryList = tutdfEntryList;
    }

    public TRLRSegment getTrlrSegment() {
        return trlrSegment;
    }

    public void setTrlrSegment(TRLRSegment trlrSegment) {
        this.trlrSegment = trlrSegment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TUTDFData{");
        sb.append("headerSegment=").append(headerSegment);
        sb.append(", tutdfEntryList=").append(tutdfEntryList);
        sb.append(", trlrSegment=").append(trlrSegment);
        sb.append('}');
        return sb.toString();
    }
}
