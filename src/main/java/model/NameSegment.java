package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class NameSegment {
    private String segmentTag; // M
    private String surname; // M
    private String patronymicName; // O
    private String firstName; // M
    private Integer emptyFieldFirst; // 1 O
    private Date dateOfBirth; // M
    private String placeOfBirth; // M
    private String emptyFieldSecond; // 2 O
    private Integer emptyFieldThird; // 3 O
    private Integer emptyFieldFourth; // 4 O
    private Integer remarks; // O
    private String oldSurname; // O
    private String oldFirstName; // 0


    public String getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getEmptyFieldFirst() {
        return emptyFieldFirst;
    }

    public void setEmptyFieldFirst(Integer emptyFieldFirst) {
        this.emptyFieldFirst = emptyFieldFirst;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getEmptyFieldSecond() {
        return emptyFieldSecond;
    }

    public void setEmptyFieldSecond(String emptyFieldSecond) {
        this.emptyFieldSecond = emptyFieldSecond;
    }

    public Integer getEmptyFieldThird() {
        return emptyFieldThird;
    }

    public void setEmptyFieldThird(Integer emptyFieldThird) {
        this.emptyFieldThird = emptyFieldThird;
    }

    public Integer getEmptyFieldFourth() {
        return emptyFieldFourth;
    }

    public void setEmptyFieldFourth(Integer emptyFieldFourth) {
        this.emptyFieldFourth = emptyFieldFourth;
    }

    public Integer getRemarks() {
        return remarks;
    }

    public void setRemarks(Integer remarks) {
        this.remarks = remarks;
    }

    public String getOldSurname() {
        return oldSurname;
    }

    public void setOldSurname(String oldSurname) {
        this.oldSurname = oldSurname;
    }

    public String getOldFirstName() {
        return oldFirstName;
    }

    public void setOldFirstName(String oldFirstName) {
        this.oldFirstName = oldFirstName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        sb.append(segmentTag).append("\t");
        sb.append(surname).append("\t");
        sb.append(patronymicName).append("\t");
        sb.append(firstName).append("\t");
        sb.append("").append("\t");

        String dateOfBirthString = sdf.format(dateOfBirth);
        sb.append(dateOfBirthString).append("\t");

        sb.append(placeOfBirth).append("\t");
        sb.append("").append("\t");
        sb.append("").append("\t");
        sb.append("").append("\t");
        sb.append(remarks).append("\t");
        sb.append(oldSurname).append("\t");
        sb.append(oldFirstName);
        return sb.toString();
    }
}
