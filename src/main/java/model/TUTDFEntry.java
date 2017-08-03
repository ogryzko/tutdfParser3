package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class TUTDFEntry {
    private List<IDSegment> idSegmentList = new ArrayList<IDSegment>();
    private NameSegment nameSegment;
    private List<PhoneNumberSegment> phoneNumberSegmentList = new ArrayList<PhoneNumberSegment>();
    private TransactionSegment transactionSegment;

    public List<IDSegment> getIdSegmentList() {
        return idSegmentList;
    }

    public void setIdSegmentList(List<IDSegment> idSegmentList) {
        this.idSegmentList = idSegmentList;
    }

    public NameSegment getNameSegment() {
        return nameSegment;
    }

    public void setNameSegment(NameSegment nameSegment) {
        this.nameSegment = nameSegment;
    }

    public List<PhoneNumberSegment> getPhoneNumberSegmentList() {
        return phoneNumberSegmentList;
    }

    public void setPhoneNumberSegmentList(List<PhoneNumberSegment> phoneNumberSegmentList) {
        this.phoneNumberSegmentList = phoneNumberSegmentList;
    }

    public TransactionSegment getTransactionSegment() {
        return transactionSegment;
    }

    public void setTransactionSegment(TransactionSegment transactionSegment) {
        this.transactionSegment = transactionSegment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TUTDFEntry{");
        sb.append("idSegmentList=").append(idSegmentList);
        sb.append(", nameSegment=").append(nameSegment);
        sb.append(", phoneNumberSegmentList=").append(phoneNumberSegmentList);
        sb.append(", transactionSegment=").append(transactionSegment);
        sb.append('}');
        return sb.toString();
    }
}
