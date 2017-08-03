import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by EGlushchenko on 28.07.2017.
 */
public class TUTDFFileParserTest {
    public static final String TUTDF_BC = "TUTDF\t4.0r\t20150701\t1301ZZ013006\t\t20160102\t4d9e6s8w\t";
    public static final String ID01_BC = "ID01\t21\t19 12\t982429\t20120707\tОУФМС России по Москве\t\t";
    public static final String NA01_BC = "NA01\tПортугалин\tСергеевич\tПетр\t\t19880710\tМосква\t\t\t\t\t\t";
    public static final String TUTDF_TR = "TUTDF\t4.0r\t20150701\t9999ZZ999999\t\t20160713\t12879654\t";
    public static final String ID01_TR = "ID01\t21\t7955\t765022\t20051107\tИглинским РОВД РБ\tИглинским РОВД РБ\t";
    public static final String NA01_TR = "NA01\tНотреалова\tИвановна\tТатьяна\t\t19850904\tпос.МУрман Мирского района РБ\t\t\t\t\t\t";
    public static final String TR01_TR = "TR01\t1452pp011001\t45859874585\t09\t1\t20160610\t20160712\t00\t20160713\t20160713\t5000\t0\t0\t0\t7\t1\tRUB\t\t20160701\t20160701\t20160701\t7\t\t\t0\tN\t\t\t\tN\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String TRLR_SIMPLE = "TRLR\t";
    public static final String TUTDF_CLGRBG = "TUTDF\t4.0r\t20150701\t9999ZZ999999\t\t20151002\t12345678\t";
    public static final String ID01_CLGRBG = "ID01\t21\t12 09\t655346\t20100707\tОУФМС России\t\t";
    public static final String NA01_CLGRBG = "NA01\tПетри\tВадимович\tОлег\t\t19831210\tМосква\t\t\t\t\t\t";
    public static final String TR01_CLGRBG = "TR01\t9999ZZ999999\t375578533344\t16\t1\t20150401\t19000102\t0\t20150401\t20150401\t2200\t0\t0\t1000\t3\t0\tRUB\t01\t20150501\t20150501\t20150501\t3\t\t\t1000\tG\tP\t100\t20150501\tB\tP\t100\t20150501\t1000\t20150401\t20150501\t100,000\t\t\t\t20150501\t";

    private static final String PN_DEF = "PN01\t3129851123\t1";
    TUTDFFileParser parser;
    TUTDFData sampleBC;
    TUTDFData sampleTR;
    TUTDFData sampleCLGRBG;

    @Before
    public void init() throws ParseException {
        parser = new TUTDFFileParser();
        initSampleBC();
        initSampleTR();
        initSampleCLGRBG();
    }

    private void initSampleBC() throws ParseException {
        sampleBC = new TUTDFData();
        TUTDFEntry sampleBCEntry = new TUTDFEntry();
        sampleBC.setHeaderSegment(parser.parseHeaderSegment(TUTDF_BC));
        sampleBCEntry.getIdSegmentList().add(parser.parseIdSegment(ID01_BC));
        sampleBCEntry.setNameSegment(parser.parseNameSegment(NA01_BC));
        sampleBC.setTrlrSegment(parser.parseTRLRSegment(TRLR_SIMPLE));
        sampleBC.getTutdfEntryList().add(sampleBCEntry);
    }

    private void initSampleTR() throws ParseException {
        sampleTR = new TUTDFData();
        TUTDFEntry sampleTREntry = new TUTDFEntry();
        sampleTR.setHeaderSegment(parser.parseHeaderSegment(TUTDF_TR));
        sampleTREntry.getIdSegmentList().add(parser.parseIdSegment(ID01_TR));
        sampleTREntry.setNameSegment(parser.parseNameSegment(NA01_TR));
        sampleTREntry.setTransactionSegment(parser.parseTransactionSegment(TR01_TR));
        sampleTR.setTrlrSegment(parser.parseTRLRSegment(TRLR_SIMPLE));
        sampleTR.getTutdfEntryList().add(sampleTREntry);
    }

    private void initSampleCLGRBG() throws ParseException {
        sampleCLGRBG = new TUTDFData();
        TUTDFEntry sampleCLGRBGEntry = new TUTDFEntry();
        sampleCLGRBG.setHeaderSegment(parser.parseHeaderSegment(TUTDF_CLGRBG));
        sampleCLGRBGEntry.getIdSegmentList().add(parser.parseIdSegment(ID01_CLGRBG));
        sampleCLGRBGEntry.setNameSegment(parser.parseNameSegment(NA01_CLGRBG));
        sampleCLGRBGEntry.setTransactionSegment(parser.parseTransactionSegment(TR01_CLGRBG));
        sampleCLGRBG.setTrlrSegment(parser.parseTRLRSegment(TRLR_SIMPLE));
        sampleCLGRBG.getTutdfEntryList().add(sampleCLGRBGEntry);
    }


    @Test
    public void testParseFileBCExample() throws IOException, ParseException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = getClass().getClassLoader();
        TUTDFData data = parser.parseTUTDFFile(classLoader.getResourceAsStream(
                "TUTDF Sample v 4.0 - BC_bankruptcy_20160805.txt"));
        assertEquals("TUTDF Sample v 4.0 - BC_bankruptcy_20160805.txt not parsed", sampleBC.toString(), data.toString());
    }

    @Test
    public void testParseFileTRExample() throws IOException, ParseException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = getClass().getClassLoader();
        TUTDFData data = parser.parseTUTDFFile(classLoader.getResourceAsStream(
                "TUTDF Sample v 4.0 - TR_20160805.txt"));
        assertEquals("TUTDF Sample v 4.0 - TR_20160805.txt not parsed",sampleTR.toString(), data.toString());
    }

    @Test
    public void testParseFileCLGRBGExample() throws IOException, ParseException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = getClass().getClassLoader();
        TUTDFData data = parser.parseTUTDFFile(classLoader.getResourceAsStream(
                "TUTDF Sample v 4.0 - CL_GR_BG_20160224.txt"));

        assertEquals("TUTDF Sample v 4.0 - CL_GR_BG_20160224.txt not parsed", sampleCLGRBG.toString(), data.toString());
    }

    @Test
    public void testParseHeaderSegment() throws ParseException {
        HeaderSegment headerSegmentExpected = sampleBC.getHeaderSegment();
        HeaderSegment headerSegmentActual = parser.parseHeaderSegment(TUTDF_BC);
        assertEquals(headerSegmentExpected.toString(), headerSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumHeaderSegment() throws ParseException {
        assertNull("parsed line with extra Tab",parser.parseHeaderSegment(TUTDF_BC + "\t"));
        assertNull(parser.parseHeaderSegment(TUTDF_BC.replace("\t", "")));
    }

    @Test
    public void testParseIDSegment() throws ParseException {
        IDSegment idSegmentExpected = sampleBC.getTutdfEntryList().get(0).getIdSegmentList().get(0);
        IDSegment idSegmentActual = parser.parseIdSegment(ID01_BC);
        assertEquals(idSegmentExpected.toString(), idSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumIDSegment() throws ParseException {
        assertNull(parser.parseIdSegment(ID01_BC + "\t"));
        assertNull(parser.parseIdSegment(ID01_BC.replace("\t", "")));
    }
    
    @Test
    public void testParseNameSegment() throws ParseException {
        NameSegment nameSegmentExpected = sampleBC.getTutdfEntryList().get(0).getNameSegment();
        NameSegment nameSegmentActual = parser.parseNameSegment(NA01_BC);
        assertEquals(nameSegmentExpected.toString(), nameSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumNameSegment() throws ParseException {
        assertNull(parser.parseNameSegment(NA01_BC + "\t"));
    }
    
    @Test
    public void testParsePhoneNumberSegment(){
        PhoneNumberSegment phoneNumberSegmentExpected = new PhoneNumberSegment();
        phoneNumberSegmentExpected.setSegmentTag("PN01");
        phoneNumberSegmentExpected.setNumber("3129851123");
        phoneNumberSegmentExpected.setType(1);
        PhoneNumberSegment phoneNumberSegmentActual = parser.parsePhoneNumberSegment(PN_DEF);
        assertEquals(phoneNumberSegmentExpected.toString(), phoneNumberSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumPhoneNumberSegment(){
        assertNull(parser.parsePhoneNumberSegment(PN_DEF + "\t"));
        assertNull(parser.parsePhoneNumberSegment(PN_DEF.replace("\t", "")));
    }

    @Test
    public void testParseTransactionSegment() throws ParseException {
        TransactionSegment transactionSegmentExpected = sampleTR.getTutdfEntryList().get(0).getTransactionSegment();
        TransactionSegment transactionSegmentActual = parser.parseTransactionSegment(TR01_TR);
        assertEquals(transactionSegmentExpected.toString(), transactionSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumTransactionSegment() throws ParseException {
        assertNull(parser.parseTransactionSegment(TR01_TR + "\t"));
        assertNull(parser.parseTransactionSegment(TR01_TR.replace("\t", "")));
    }

    @Test
    public void testParseTRLRSegment(){
        TRLRSegment trlrSegmentExpected = new TRLRSegment();
        trlrSegmentExpected.setTrailerSegment("TRLR");
        TRLRSegment trlrSegmentActual = parser.parseTRLRSegment(TRLR_SIMPLE);
        assertEquals(trlrSegmentExpected.toString(), trlrSegmentActual.toString());
    }

    @Test
    public void testParseIncorrectFieldNumTRLRSegment(){
        assertNull(parser.parseTRLRSegment(TRLR_SIMPLE + "\t"));
        assertNull(parser.parseTRLRSegment(TRLR_SIMPLE.replace("\t", "")));
    }

    /*@Test
    public void testParseFromString() throws IOException, ParseException {
        String bcSampleString = "TUTDF\t4.0r\t20150701\t1301ZZ013006\t\t20160102\t4d9e6s8w\t\n" +
                "ID01\t21\t19 12\t982429\t20120707\tОУФМС России по Москве\t\t\n" +
                "NA01\tПортугалин\tСергеевич\tПетр\t\t19880710\tМосква\t\t\t\t\t\t\n" +
                "AD01\t2\t163240\t\t\t\t\tМосква\t\tПрохладная\t\t\t\t\t\t\n" +
                "AD02\t1\t163240\t\t\t\t\tМосква\t\tПрохладная\t\t\t\t\t\t\n" +
                "BC01\t1301ZZ013006\tBankr_Cun_20160805\t20151001\t1\t20151001\t20151222\t\t\t\t20151001\t20160505\n" +
                "TRLR\t\n";

        TUTDFData tutdfDataActual = parser.parseTUTDFFile(new BufferedReader(new StringReader(bcSampleString)));
        assertEquals(sampleBC, tutdfDataActual);
    }*/

    @Test
    public void testParseEmptyString() throws IOException, ParseException {
        assertNull(parser.parseTUTDFFile(new BufferedReader(new StringReader(""))));
    }

    @Test
    public void testParseMultiSubjectFile() throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        TUTDFData tutdfDataActual = parser.parseTUTDFFile(classLoader.getResourceAsStream("0002BB000001_20170712_161333.txt"));
    }

}