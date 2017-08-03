package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class TransactionSegment {
    private String segmentTag; // M
    private String memberCode; // M
    private String accountNumber; // M
    private Integer accountType; // C
    private Integer accountRelationship; // M
    private Date dateAccountOpened; // C
    private Date dateOfLastPayment; // C
    private Integer accountRating; // C
    private Date dateAccountRating; // C
    private Date dateReported; // C
    private String creditLimit; // C
    private String balance; // C
    private String pastDue; // C
    private String nextPayment; // C
    private Integer creditPaymentFrequency; // C
    private String mop; // C
    private String currencyCode; // C
    private Integer collateralCode; // C
    private Date dateOfContractTermination; // C
    private Date datePaymentDue; // C
    private Date dateInterestPaymentDue; // C
    private Integer interestPaymentFrequency; // C
    private String oldMemberCode; // O
    private String oldAccountNumber; // O
    private String amountOutstanding; // C
    private String guarantorIndicator; // C
    private String volumeOfDebtSecuredByGuarantee; // C
    private String guaranteeSum; // C
    private Date guaranteeTem; // C
    private String bankGuaranteeIndicator; // C
    private String volumeOfDebtSecuredByBankGuarantee; // C
    private String bankGuaranteeSum; // C
    private String bankGuaranteeTem; // C
    private String collateralValue; // C
    private Date collateralDate; // C
    private Date collateralAgreementExpirationDate; // C
    private String overallValueOfCredit; // C
    private String rightOfClaimAcquirersNames; // C
    private String rightOfClaimAcquirersRegistrationData; // C
    private Integer rightOfClaimAcquirersTaxpayerID; // C
    private Integer rightOfClaimAcquirersSocialInsuranceNumber; // C
    private Date completePerformanceOfObligationsDate; // C
    public String getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountRelationship() {
        return accountRelationship;
    }

    public void setAccountRelationship(Integer accountRelationship) {
        this.accountRelationship = accountRelationship;
    }

    public Date getDateAccountOpened() {
        return dateAccountOpened;
    }

    public void setDateAccountOpened(Date dateAccountOpened) {
        this.dateAccountOpened = dateAccountOpened;
    }

    public Date getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public void setDateOfLastPayment(Date dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public Integer getAccountRating() {
        return accountRating;
    }

    public void setAccountRating(Integer accountRating) {
        this.accountRating = accountRating;
    }

    public Date getDateAccountRating() {
        return dateAccountRating;
    }

    public void setDateAccountRating(Date dateAccountRating) {
        this.dateAccountRating = dateAccountRating;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPastDue() {
        return pastDue;
    }

    public void setPastDue(String pastDue) {
        this.pastDue = pastDue;
    }

    public String getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(String nextPayment) {
        this.nextPayment = nextPayment;
    }

    public Integer getCreditPaymentFrequency() {
        return creditPaymentFrequency;
    }

    public void setCreditPaymentFrequency(Integer creditPaymentFrequency) {
        this.creditPaymentFrequency = creditPaymentFrequency;
    }

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getCollateralCode() {
        return collateralCode;
    }

    public void setCollateralCode(Integer collateralCode) {
        this.collateralCode = collateralCode;
    }

    public Date getDateOfContractTermination() {
        return dateOfContractTermination;
    }

    public void setDateOfContractTermination(Date dateOfContractTermination) {
        this.dateOfContractTermination = dateOfContractTermination;
    }

    public Date getDatePaymentDue() {
        return datePaymentDue;
    }

    public void setDatePaymentDue(Date datePaymentDue) {
        this.datePaymentDue = datePaymentDue;
    }

    public Date getDateInterestPaymentDue() {
        return dateInterestPaymentDue;
    }

    public void setDateInterestPaymentDue(Date dateInterestPaymentDue) {
        this.dateInterestPaymentDue = dateInterestPaymentDue;
    }

    public Integer getInterestPaymentFrequency() {
        return interestPaymentFrequency;
    }

    public void setInterestPaymentFrequency(Integer interestPaymentFrequency) {
        this.interestPaymentFrequency = interestPaymentFrequency;
    }

    public String getOldMemberCode() {
        return oldMemberCode;
    }

    public void setOldMemberCode(String oldMemberCode) {
        this.oldMemberCode = oldMemberCode;
    }

    public String getOldAccountNumber() {
        return oldAccountNumber;
    }

    public void setOldAccountNumber(String oldAccountNumber) {
        this.oldAccountNumber = oldAccountNumber;
    }

    public String getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(String amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public String getGuarantorIndicator() {
        return guarantorIndicator;
    }

    public void setGuarantorIndicator(String guarantorIndicator) {
        this.guarantorIndicator = guarantorIndicator;
    }

    public String getVolumeOfDebtSecuredByGuarantee() {
        return volumeOfDebtSecuredByGuarantee;
    }

    public void setVolumeOfDebtSecuredByGuarantee(String volumeOfDebtSecuredByGuarantee) {
        this.volumeOfDebtSecuredByGuarantee = volumeOfDebtSecuredByGuarantee;
    }

    public String getGuaranteeSum() {
        return guaranteeSum;
    }

    public void setGuaranteeSum(String guaranteeSum) {
        this.guaranteeSum = guaranteeSum;
    }

    public Date getGuaranteeTem() {
        return guaranteeTem;
    }

    public void setGuaranteeTem(Date guaranteeTem) {
        this.guaranteeTem = guaranteeTem;
    }

    public String getBankGuaranteeIndicator() {
        return bankGuaranteeIndicator;
    }

    public void setBankGuaranteeIndicator(String bankGuaranteeIndicator) {
        this.bankGuaranteeIndicator = bankGuaranteeIndicator;
    }

    public String getVolumeOfDebtSecuredByBankGuarantee() {
        return volumeOfDebtSecuredByBankGuarantee;
    }

    public void setVolumeOfDebtSecuredByBankGuarantee(String volumeOfDebtSecuredByBankGuarantee) {
        this.volumeOfDebtSecuredByBankGuarantee = volumeOfDebtSecuredByBankGuarantee;
    }

    public String getBankGuaranteeSum() {
        return bankGuaranteeSum;
    }

    public void setBankGuaranteeSum(String bankGuaranteeSum) {
        this.bankGuaranteeSum = bankGuaranteeSum;
    }

    public String getBankGuaranteeTem() {
        return bankGuaranteeTem;
    }

    public void setBankGuaranteeTem(String bankGuaranteeTem) {
        this.bankGuaranteeTem = bankGuaranteeTem;
    }

    public String getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(String collateralValue) {
        this.collateralValue = collateralValue;
    }

    public Date getCollateralDate() {
        return collateralDate;
    }

    public void setCollateralDate(Date collateralDate) {
        this.collateralDate = collateralDate;
    }

    public String getOverallValueOfCredit() {
        return overallValueOfCredit;
    }

    public void setOverallValueOfCredit(String overallValueOfCredit) {
        this.overallValueOfCredit = overallValueOfCredit;
    }

    public String getRightOfClaimAcquirersNames() {
        return rightOfClaimAcquirersNames;
    }

    public void setRightOfClaimAcquirersNames(String rightOfClaimAcquirersNames) {
        this.rightOfClaimAcquirersNames = rightOfClaimAcquirersNames;
    }

    public String getRightOfClaimAcquirersRegistrationData() {
        return rightOfClaimAcquirersRegistrationData;
    }

    public void setRightOfClaimAcquirersRegistrationData(String rightOfClaimAcquirersRegistrationData) {
        this.rightOfClaimAcquirersRegistrationData = rightOfClaimAcquirersRegistrationData;
    }

    public Integer getRightOfClaimAcquirersTaxpayerID() {
        return rightOfClaimAcquirersTaxpayerID;
    }

    public void setRightOfClaimAcquirersTaxpayerID(Integer rightOfClaimAcquirersTaxpayerID) {
        this.rightOfClaimAcquirersTaxpayerID = rightOfClaimAcquirersTaxpayerID;
    }

    public Integer getRightOfClaimAcquirersSocialInsuranceNumber() {
        return rightOfClaimAcquirersSocialInsuranceNumber;
    }

    public void setRightOfClaimAcquirersSocialInsuranceNumber(Integer rightOfClaimAcquirersSocialInsuranceNumber) {
        this.rightOfClaimAcquirersSocialInsuranceNumber = rightOfClaimAcquirersSocialInsuranceNumber;
    }

    public Date getCompletePerformanceOfObligationsDate() {
        return completePerformanceOfObligationsDate;
    }

    public void setCompletePerformanceOfObligationsDate(Date completePerformanceOfObligationsDate) {
        this.completePerformanceOfObligationsDate = completePerformanceOfObligationsDate;
    }

    public Date getCollateralAgreementExpirationDate() {
        return collateralAgreementExpirationDate;
    }

    public void setCollateralAgreementExpirationDate(Date collateralAgreementExpirationDate) {
        this.collateralAgreementExpirationDate = collateralAgreementExpirationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        StringBuilder sb = new StringBuilder();
        sb.append(segmentTag).append("\t");
        sb.append(memberCode).append("\t");
        sb.append(accountNumber).append("\t");
        sb.append(accountType == null ? "" : String.format("%02d",accountType)).append("\t");

        sb.append(accountRelationship == null ? "" : accountRelationship).append("\t");

        String dateAccountOpenedString = dateAccountOpened == null ? "" : sdf.format(dateAccountOpened);
        sb.append(dateAccountOpenedString).append("\t");

        String dateOfLastPaymentString = dateOfLastPayment == null ? "" : sdf.format(dateOfLastPayment);
        sb.append(dateOfLastPaymentString).append("\t");

        sb.append(accountRating == null ? "" : String.format("%02d",accountRating)).append("\t");

        String dateAccountRatingString = dateAccountRating == null ? "" : sdf.format(dateAccountRating);
        sb.append(dateAccountRatingString).append("\t");

        String dateReportedString = dateReported == null ? "" : sdf.format(dateReported);
        sb.append(dateReportedString).append("\t");

        sb.append(creditLimit).append("\t");
        sb.append(balance).append("\t");
        sb.append(pastDue).append("\t");
        sb.append(nextPayment).append("\t");
        sb.append(creditPaymentFrequency).append("\t");
        sb.append(mop).append("\t");
        sb.append(currencyCode).append("\t");
        sb.append(collateralCode).append("\t");

        String dateOfContractTerminationString = dateOfContractTermination == null ? "" : sdf.format(dateOfContractTermination);
        sb.append(dateOfContractTerminationString).append("\t");

        String datePaymentDueString = sdf.format(datePaymentDue);
        sb.append(datePaymentDueString).append("\t");

        String dateInterestPaymentDueString = sdf.format(dateInterestPaymentDue);
        sb.append(dateInterestPaymentDueString).append("\t");

        sb.append(interestPaymentFrequency).append("\t");
        sb.append(oldMemberCode).append("\t");
        sb.append(oldAccountNumber).append("\t");
        sb.append(amountOutstanding).append("\t");
        sb.append(guarantorIndicator).append("\t");
        sb.append(volumeOfDebtSecuredByGuarantee).append("\t");
        sb.append(guaranteeSum).append("\t");

        String guaranteeTemString = guaranteeTem == null ? "" : sdf.format(guaranteeTem);
        sb.append(guaranteeTemString).append("\t");

        sb.append(bankGuaranteeIndicator).append("\t");
        sb.append(volumeOfDebtSecuredByBankGuarantee).append("\t");
        sb.append(bankGuaranteeSum).append("\t");
        sb.append(bankGuaranteeTem).append("\t");
        sb.append(collateralValue).append("\t");

        String collateralDateString = collateralDate == null ? "" : sdf.format(collateralDate);
        sb.append(collateralDateString).append("\t");

        String collateralAgreementExpirationDateString =
                collateralAgreementExpirationDate == null ? "" : sdf.format(collateralAgreementExpirationDate);
        sb.append(collateralAgreementExpirationDateString).append("\t");

        sb.append(overallValueOfCredit).append("\t");
        sb.append(rightOfClaimAcquirersNames).append("\t");
        sb.append(rightOfClaimAcquirersRegistrationData).append("\t");
        sb.append(rightOfClaimAcquirersTaxpayerID).append("\t");
        sb.append(rightOfClaimAcquirersSocialInsuranceNumber).append("\t");

        String completePerformanceOfObligationsDateString =
                completePerformanceOfObligationsDate == null ? "" : sdf.format(completePerformanceOfObligationsDate);
        sb.append(completePerformanceOfObligationsDateString);

        return sb.toString();
    }
}
