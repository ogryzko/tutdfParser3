import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class TUTDFFileParser {

    private static final String TUTDF_LOG_TAG = "@TUTDF: " ;
    private static final String TR_LOG_TAG = "@TR: " ;
    private static final String TRLR_LOG_TAG = "@TRLR: ";
    private static final String ID_LOG_TAG = "@ID: ";
    private static final String NA_LOG_TAG = "@NA: ";
    private static final String PN_LOG_TAG = "@PN: ";
    private static final String UNKNOWN_LOG_TAG = "@UNKNOWN: ";

    private static final Integer NUM_ID_FIELDS = 8;
    private static final Integer NUM_NA_FIELDS = 13;
    private static final Integer NUM_TR_FIELDS = 42;
    private static final Integer NUM_TUTDF_FIELDS = 8;
    private static final int NUM_PN_FIELDS = 3;
    private static final Integer NUM_TRLR_FIELDS = 2;

    private  final SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
    private static final String NEWLINE = "\n";
    private TUTDFEntry currentEntry;

    private Logger log = Logger.getLogger(TUTDFFileParser.class.getName());

    private TUTDFData data;

    public TUTDFFileParser(){
        data = new TUTDFData();
    }

    /**
     * Parse a TUTDF file and return TUTDFData object
     * @param inStream an InputStream
     * @return a TUTDFData object
     * @throws IOException
     * @throws ParseException
     */
    public TUTDFData parseTUTDFFile(InputStream inStream) throws IOException, ParseException {
        BufferedReader reader = getBufferedReader(inStream);
        return parseTUTDFFile(reader);
    }

    /**
     * Parse a TUTDF file and return TUTDFData object
     * @param reader a BufferedReader object
     * @return a TUTDFData object
     * @throws IOException
     * @throws ParseException
     */
    public TUTDFData parseTUTDFFile(BufferedReader reader) throws IOException, ParseException {
        String currentLine;
        currentEntry = null;
        data = null;

        while ((currentLine = reader.readLine()) != null){
            if(currentLine.equals("") ||
                    currentLine.equals(NEWLINE)){
                continue;
            }
            switch (segmentTagFromLine(currentLine)){
                case TUTDF:
                    tutdfTUTDFHandler(currentLine);
                    break;
                case TR:
                    tutdfTRHandler(currentLine);
                    break;
                case PN:
                    tutdfPNHandler(currentLine);
                    break;
                case NA:
                    tutdfNAHandler(currentLine);
                    break;
                case ID:
                    tutdfIDHandler(currentLine);
                    break;
                case TRLR:
                    tutdfTRLRHandler(currentLine);
                    break;
                case UNKNOWN:
                    tutdfUNKNOWNHandler(currentLine);
                    break;
                default:
                    // todo
                    break;

            }
        }
        reader.close();
        return data;
    }

    // Handler for unknown format
    private void tutdfUNKNOWNHandler(String currentLine) {
        if (currentEntry == null) log.log(Level.INFO, UNKNOWN_LOG_TAG + "Unexpected segment occurrence!");
    }

    // Handler for TRLR format
    private void tutdfTRLRHandler(String currentLine) {
        if(currentEntry == null){
            log.log(Level.INFO, TRLR_LOG_TAG + "Unexpected segment occurrence!");
            return;
        }

        TRLRSegment trlrSegment = parseTRLRSegment(currentLine);
        if (trlrSegment == null) return; // todo


        data.setTrlrSegment(trlrSegment);
        data.getTutdfEntryList().add(currentEntry);
        currentEntry = null;
    }

    /**
     * Parse a TRLR formatted string and return TRLRSegment object
     * @param currentLine a String object
     * @return a TRLRSegment object
     */
    public TRLRSegment parseTRLRSegment(String currentLine) {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_TRLR_FIELDS) return null;

        TRLRSegment trlrSegment = new TRLRSegment();

        setMandatoryTrailerSegmentFields(strArr[0], trlrSegment);

        setOptionalTrailerSegmentFields(strArr[1], trlrSegment);
        return trlrSegment;
    }

    private void setOptionalTrailerSegmentFields(String s, TRLRSegment trlrSegment) {
        String counterString = s;
        Integer counter = parseOptionalNumericalField(counterString);
        trlrSegment.setCounter(counter);
    }

    private void setMandatoryTrailerSegmentFields(String s, TRLRSegment trlrSegment) {
        String trailerSegment = s;
        trlrSegment.setTrailerSegment(trailerSegment);
    }

    // Handler for ID format
    private void tutdfIDHandler(String currentLine) throws ParseException {
        if(data == null){
            log.log(Level.INFO, ID_LOG_TAG + "Unexpected segment occurrence! (Out of data bounds)");
            return;
        }

        IDSegment idSegment = parseIdSegment(currentLine);
        if (idSegment == null) return; // todo
        /*if(idSegment.getSegmentTag().equals("ID01")){
            if(data.getTutdfEntryList().size() >= 1)
                data.getTutdfEntryList().add(currentEntry);
            currentEntry = new TUTDFEntry();
        } else if(currentEntry == null){
            log.log(Level.INFO, ID_LOG_TAG + "Unexpected segment occurrence! (Out of entry bounds)");
            return;
        }*/
        if(idSegment.getSegmentTag().equals("ID01")) {
            if(currentEntry == null)
                currentEntry = new TUTDFEntry();
            if(!currentEntry.getIdSegmentList().isEmpty()){
                data.getTutdfEntryList().add(currentEntry);
                currentEntry = new TUTDFEntry();
            }
        }
        currentEntry.getIdSegmentList().add(idSegment); // todo
    }

    /**
     *  Parse  a ID formatted string and return IDSegment object
     * @param currentLine a String object
     * @return a IDSegment object
     * @throws ParseException
     */
    public IDSegment parseIdSegment(String currentLine) throws ParseException {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_ID_FIELDS) return null;

        IDSegment idSegment = new IDSegment();

        setMandatoryIdSegmentFields(strArr, idSegment);

        setConditionalIdSegmentFields(strArr, idSegment);

        setOptionalIdSegmentFields(strArr, idSegment);
        return idSegment;
    }

    private void setOptionalIdSegmentFields(String[] strArr, IDSegment idSegment) {
        String issueLocation = strArr[6];
        idSegment.setIssueLocation(issueLocation);

        String oldIDNumber = strArr[7];
        idSegment.setOldIDNumber(oldIDNumber);
    }

    private void setConditionalIdSegmentFields(String[] strArr, IDSegment idSegment) throws ParseException {
        String seriesNumber = strArr[2];
        idSegment.setSeriesNumber(seriesNumber);

        String idNumber = strArr[3];
        idSegment.setIdNumber(idNumber);

        String issueDateString = strArr[4];
        Date issueDate = parseOptionalDateField(issueDateString);
        idSegment.setIssueDate(issueDate);

        String issueAuthority = strArr[5];
        idSegment.setIssueAuthority(issueAuthority);
    }

    private void setMandatoryIdSegmentFields(String[] strArr, IDSegment idSegment) {
        String segmentTag = strArr[0];
        idSegment.setSegmentTag(segmentTag);

        String idTypeString = strArr[1];
        Integer idType = Integer.parseInt(idTypeString);
        idSegment.setIdType(idType);
    }

    // Handler for NA format
    private void tutdfNAHandler(String currentLine) throws ParseException {
        if(currentEntry == null){
            log.log(Level.INFO, NA_LOG_TAG + "Unexpected segment occurrence!");
            return;
        }

        NameSegment nameSegment = parseNameSegment(currentLine);
        if (nameSegment == null) return; //todo


        currentEntry.setNameSegment(nameSegment);
    }

    /**
     *  Parse  a NA formatted string and return NameSegment object
     * @param currentLine a String object
     * @return a NameSegment object
     * @throws ParseException
     */
    public NameSegment parseNameSegment(String currentLine) throws ParseException {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_NA_FIELDS) return null;

        NameSegment nameSegment = new NameSegment();

        setMandatoryNameSegmentFields(strArr, nameSegment);

        setOptionalNameSegmentFields(strArr, nameSegment);
        return nameSegment;
    }

    private void setOptionalNameSegmentFields(String[] strArr, NameSegment nameSegment) {
        String patronymicName = strArr[2]; // O
        nameSegment.setPatronymicName(patronymicName);
        String emptyField1String = strArr[4]; // O
        Integer emptyField1 = parseOptionalNumericalField(emptyField1String); // todo ask about "empty numeric field"...
        nameSegment.setEmptyFieldFirst(emptyField1);
        String emptyField2 = strArr[7]; // O
        nameSegment.setEmptyFieldSecond(emptyField2);

        String emptyField3String = strArr[8]; // O
        Integer emptyField3 = parseOptionalNumericalField(emptyField3String);
        nameSegment.setEmptyFieldThird(emptyField3);

        String emptyField4String = strArr[9]; // O
        Integer emptyField4 = parseOptionalNumericalField(emptyField4String);
        nameSegment.setEmptyFieldFourth(emptyField4);

        String remarksString = strArr[10]; // O
        Integer remarks = parseOptionalNumericalField(remarksString);
        nameSegment.setRemarks(remarks);

        String oldSurname = strArr[11]; // O
        nameSegment.setOldSurname(oldSurname);

        String oldFirstName = strArr[12]; // O
        nameSegment.setOldFirstName(oldFirstName);
    }

    private void setMandatoryNameSegmentFields(String[] strArr, NameSegment nameSegment) throws ParseException {
        String segmentTag = strArr[0]; // M
        nameSegment.setSegmentTag(segmentTag);

        String surname = strArr[1]; // M
        nameSegment.setSurname(surname);


        String firstName = strArr[3]; // M
        nameSegment.setFirstName(firstName);


        String dateOfBirthString = strArr[5]; // M
        Date dateOfBirth = sdf.parse(dateOfBirthString);
        nameSegment.setDateOfBirth(dateOfBirth);

        String placeOfBirth = strArr[6]; // M
        nameSegment.setPlaceOfBirth(placeOfBirth);
    }

    // Handler for PN format
    private void tutdfPNHandler(String currentLine) {
        if(currentEntry == null){
            log.log(Level.INFO, PN_LOG_TAG + "Unexpected segment occurrence!");
            return;
        }
        PhoneNumberSegment phoneNumberSegment = parsePhoneNumberSegment(currentLine);
        if (phoneNumberSegment == null) return; //todo


        currentEntry.getPhoneNumberSegmentList().add(phoneNumberSegment);
    }

    /**
     *  Parse  a PN formatted string and return PhoneNumberSegment object
     * @param currentLine a String object
     * @return a PhoneNumberSegment object
     * @throws ParseException
     */
    public PhoneNumberSegment parsePhoneNumberSegment(String currentLine) {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_PN_FIELDS) return null;

        PhoneNumberSegment phoneNumberSegment = new PhoneNumberSegment();

        setMandatoryPhoneNumberSegmentFields(strArr, phoneNumberSegment);

        setConditionalPhoneNumberSegmentFields(strArr[2], phoneNumberSegment);
        return phoneNumberSegment;
    }

    private void setConditionalPhoneNumberSegmentFields(String s, PhoneNumberSegment phoneNumberSegment) {
        String typeString = s; // C
        Integer type = Integer.parseInt(typeString);
        phoneNumberSegment.setType(type);
    }

    private void setMandatoryPhoneNumberSegmentFields(String[] strArr, PhoneNumberSegment phoneNumberSegment) {
        String segmentTag = strArr[0]; // M
        phoneNumberSegment.setSegmentTag(segmentTag);

        String number = strArr[1]; // M
        phoneNumberSegment.setNumber(number);
    }

    // Handler for TR format
    private void tutdfTRHandler(String currentLine) throws ParseException {
        if(currentEntry == null){
            log.log(Level.INFO, TR_LOG_TAG + "Unexpected segment occurrence!");
            return;
        }
        TransactionSegment transactionSegment = parseTransactionSegment(currentLine);
        if (transactionSegment == null) return; //todo


        currentEntry.setTransactionSegment(transactionSegment);
    }

    /**
     *  Parse  a TR formatted string and return TransactionSegment object
     * @param currentLine a String object
     * @return a TransactionSegment object
     * @throws ParseException
     */
    public TransactionSegment parseTransactionSegment(String currentLine) throws ParseException {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_TR_FIELDS) return null;

        TransactionSegment transactionSegment = new TransactionSegment();

        setMandatoryTransactionSegmentFields(strArr, transactionSegment);

        setConditionalTransactionSegmentFields(strArr, transactionSegment);

        setOptionalTransactionSegmentFields(strArr, transactionSegment);
        return transactionSegment;
    }

    private void setOptionalTransactionSegmentFields(String[] strArr, TransactionSegment transactionSegment) {
        String oldMemberCode = strArr[22]; // O
        transactionSegment.setOldMemberCode(oldMemberCode);

        String oldAccountNumber = strArr[23]; // O
        transactionSegment.setOldAccountNumber(oldAccountNumber);
    }

    private void setConditionalTransactionSegmentFields(String[] strArr,
                                                        TransactionSegment transactionSegment) throws ParseException {
        String accountTypeString = strArr[3]; // C
        Integer accountType = parseOptionalNumericalField(accountTypeString);
        transactionSegment.setAccountType(accountType);

        String dateAccountOpenedString = strArr[5]; // C
        Date dateAccountOpened = parseOptionalDateField(dateAccountOpenedString);
        transactionSegment.setDateAccountOpened(dateAccountOpened);

        String dateOfLastPaymentString = strArr[6]; // C
        Date dateOfLastPayment = parseOptionalDateField(dateOfLastPaymentString);
        transactionSegment.setDateOfLastPayment(dateOfLastPayment);

        String accountRatingString = strArr[7]; // C
        Integer accountRating = parseOptionalNumericalField(accountRatingString);
        transactionSegment.setAccountRating(accountRating);

        String dateAccountRatingString = strArr[8]; // C
        Date dateAccountRating = parseOptionalDateField(dateAccountRatingString);
        transactionSegment.setDateAccountRating(dateAccountRating);

        String dateReportedString = strArr[9]; // C
        Date dateReported = parseOptionalDateField(dateReportedString);
        transactionSegment.setDateReported(dateReported);

        String creditLimit = strArr[10]; // C
        transactionSegment.setCreditLimit(creditLimit);

        String balance = strArr[11]; // C
        transactionSegment.setBalance(balance);

        String pastDue = strArr[12]; // C
        transactionSegment.setPastDue(pastDue);

        String nextPayment = strArr[13]; // C
        transactionSegment.setNextPayment(nextPayment);

        String creditPaymentFrequencyString = strArr[14]; // C
        Integer creditPaymentFrequency = parseOptionalNumericalField(creditPaymentFrequencyString);
        transactionSegment.setCreditPaymentFrequency(creditPaymentFrequency);

        String mop = strArr[15]; // C
        transactionSegment.setMop(mop);

        String currencyCode = strArr[16]; // C
        transactionSegment.setCurrencyCode(currencyCode);

        String collateralCodeString = strArr[17]; // C
        Integer collateralCode = parseOptionalNumericalField(collateralCodeString);
        transactionSegment.setCollateralCode(collateralCode);

        String dateOfContractTerminationString = strArr[18]; // C
        Date dateOfContractTermination = parseOptionalDateField(dateOfContractTerminationString);
        transactionSegment.setDateOfContractTermination(dateOfContractTermination);

        String datePaymentDueString = strArr[19]; // C
        Date datePaymentDue = parseOptionalDateField(datePaymentDueString);
        transactionSegment.setDatePaymentDue(datePaymentDue);

        String dateInterestPaymentDueString = strArr[20]; // C
        Date dateInterestPaymentDue = parseOptionalDateField(dateInterestPaymentDueString);
        transactionSegment.setDateInterestPaymentDue(dateInterestPaymentDue);

        String interestPaymentFrequencyString = strArr[21]; // C
        Integer interestPaymentFrequency = parseOptionalNumericalField(interestPaymentFrequencyString);
        transactionSegment.setInterestPaymentFrequency(interestPaymentFrequency);


        String amountOutstanding = strArr[24]; // C
        transactionSegment.setAmountOutstanding(amountOutstanding);

        String guarantorIndicator = strArr[25]; // C
        transactionSegment.setGuarantorIndicator(guarantorIndicator);

        String volumeOfDebtSecuredByGuarantee = strArr[26]; // C
        transactionSegment.setVolumeOfDebtSecuredByGuarantee(volumeOfDebtSecuredByGuarantee);

        String guaranteeSum = strArr[27]; // C
        transactionSegment.setGuaranteeSum(guaranteeSum);

        String guaranteeTemString = strArr[28]; // C
        Date guaranteeTem = parseOptionalDateField(guaranteeTemString);
        transactionSegment.setGuaranteeTem(guaranteeTem);

        String bankGuaranteeIndicator = strArr[29]; // C
        transactionSegment.setBankGuaranteeIndicator(bankGuaranteeIndicator);

        String volumeOfDebtSecuredByBankGuarantee = strArr[30]; // C
        transactionSegment.setVolumeOfDebtSecuredByBankGuarantee(volumeOfDebtSecuredByBankGuarantee);

        String bankGuaranteeSum = strArr[31]; // C
        transactionSegment.setBankGuaranteeSum(bankGuaranteeSum);

        String bankGuaranteeTem = strArr[32]; // C
        transactionSegment.setBankGuaranteeTem(bankGuaranteeTem);

        String collateralValue = strArr[33]; // C
        transactionSegment.setCollateralValue(collateralValue);

        String collateralDateString  = strArr[34]; // C
        Date collateralDate = parseOptionalDateField(collateralDateString);
        transactionSegment.setCollateralDate(collateralDate);

        String collateralAgreementExpirationDateString = strArr[35]; // С
        Date collateralAgreementExpirationDate = parseOptionalDateField(collateralAgreementExpirationDateString);
        transactionSegment.setCollateralAgreementExpirationDate(collateralAgreementExpirationDate);

        String overallValueOfCredit = strArr[36]; // С
        transactionSegment.setOverallValueOfCredit(overallValueOfCredit);

        String rightOfClaimAcquirersNames = strArr[37]; // С
        transactionSegment.setRightOfClaimAcquirersNames(rightOfClaimAcquirersNames);

        String rightOfClaimAcquirersRegistrationData = strArr[38]; // С
        transactionSegment.setRightOfClaimAcquirersRegistrationData(rightOfClaimAcquirersRegistrationData);

        String rightOfClaimAcquirersTaxpayerIDString = strArr[39]; // С
        Integer rightOfClaimAcquirersTaxpayerID = parseOptionalNumericalField(rightOfClaimAcquirersTaxpayerIDString);
        transactionSegment.setRightOfClaimAcquirersTaxpayerID(rightOfClaimAcquirersTaxpayerID);

        String rightOfClaimAcquirersSocialInsuranceNumberString = strArr[40]; // С
        Integer rightOfClaimAcquirersSocialInsuranceNumber = parseOptionalNumericalField(rightOfClaimAcquirersSocialInsuranceNumberString);
        transactionSegment.setRightOfClaimAcquirersSocialInsuranceNumber(rightOfClaimAcquirersSocialInsuranceNumber);

        String completePerformanceOfObligationsDateString = strArr[41]; // С
        Date completePerformanceOfObligationsDate = parseOptionalDateField(completePerformanceOfObligationsDateString);
        transactionSegment.setCompletePerformanceOfObligationsDate(completePerformanceOfObligationsDate);
    }

    private void setMandatoryTransactionSegmentFields(String[] strArr, TransactionSegment transactionSegment) {
        String segmentTag = strArr[0]; // M
        transactionSegment.setSegmentTag(segmentTag);

        String memberCode = strArr[1]; // M
        transactionSegment.setMemberCode(memberCode);

        String accountNumber = strArr[2]; // M
        transactionSegment.setAccountNumber(accountNumber);


        String accountRelationshipString = strArr[4]; // M
        Integer accountRelationship = Integer.parseInt(accountRelationshipString);
        transactionSegment.setAccountRelationship(accountRelationship);
    }

    // Handler for TUTDF (Header) format
    private void tutdfTUTDFHandler(String currentLine) throws ParseException {

        if(data == null){
            data = new TUTDFData();
        } else{
            log.log(Level.INFO, TUTDF_LOG_TAG + "Unexpected data header!");
            return;
        }
        HeaderSegment headerSegment = parseHeaderSegment(currentLine);
        if (headerSegment == null) return; //todo
        data.setHeaderSegment(headerSegment);
    }

    /**
     *  Parse  a TUTDF formatted string and return HeaderSegment object
     * @param currentLine a String object
     * @return a HeaderSegment object
     * @throws ParseException
     */
    public HeaderSegment parseHeaderSegment(String currentLine) throws ParseException {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != NUM_TUTDF_FIELDS) return null;

        HeaderSegment headerSegment = new HeaderSegment();

        setMandatoryHeaderSegmentFields(strArr, headerSegment);

        setOptionalHeaderSegmentFields(strArr, headerSegment);
        return headerSegment;
    }

    private void setOptionalHeaderSegmentFields(String[] strArr, HeaderSegment headerSegment) {
        String cycleIdentification = strArr[4];
        headerSegment.setCycleIdentification(cycleIdentification);
        String memberData = strArr[7];
        headerSegment.setMemberData(memberData);
    }

    private void setMandatoryHeaderSegmentFields(String[] strArr, HeaderSegment headerSegment) throws ParseException {
        String segmentTag = strArr[0];
        headerSegment.setSegmentTag(segmentTag);

        String version = strArr[1];
        headerSegment.setVersion(version);

        String versionDateString = strArr[2];
        Date versionDate = sdf.parse(versionDateString);
        headerSegment.setVersionDate(versionDate);

        String memberCode = strArr[3];
        headerSegment.setMemberCode(memberCode);


        String reportedDateString = strArr[5];
        Date reportedDate = sdf.parse(reportedDateString);
        headerSegment.setReportDate(reportedDate);

        String authorizationCode = strArr[6];
        headerSegment.setAuthorizationCode(authorizationCode);
    }

    public SegmentTag segmentTagFromLine(String line){
        if(line.startsWith("TUTDF")) return SegmentTag.TUTDF;
        else if(line.startsWith("TRLR")) return SegmentTag.TRLR;
        else if(line.startsWith("ID")) return SegmentTag.ID;
        else if(line.startsWith("NA")) return SegmentTag.NA;
        else if(line.startsWith("PN")) return SegmentTag.PN;
        else if(line.startsWith("TR")) return SegmentTag.TR;
        else return SegmentTag.UNKNOWN;
    }

    private BufferedReader getBufferedReader(InputStream inStream)
            throws IOException {

        BufferedReader reader;
        if (inStream == null) {
            throw new IOException ("input stream is null!");
        }

        reader = new BufferedReader (new InputStreamReader(inStream, Charset.forName("windows-1251") ));
        return reader;

    }

    private Integer parseOptionalNumericalField(String field){
        return field.equals("") ? null : Integer.parseInt(field);
    }
    
    private Date parseOptionalDateField(String field) throws ParseException {
        return field.equals("") ? null : sdf.parse(field);
    }
}
