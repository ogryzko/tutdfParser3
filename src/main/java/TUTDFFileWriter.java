import model.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import static java.nio.file.Files.newBufferedWriter;

/**
 * Created by eglushchenko on 01.08.2017.
 */
public class TUTDFFileWriter {
    BufferedWriter writer;

    public TUTDFFileWriter(Path outputMultiFile) throws IOException {
        writer = newBufferedWriter(outputMultiFile,  Charset.forName("windows-1251"));
    }

    public void write(TUTDFData tutdfData) throws IOException {
        HeaderSegment headerSegment = tutdfData.getHeaderSegment();
        writer.write(printSegment(headerSegment));
        writer.newLine();
        for(TUTDFEntry tutdfEntry : tutdfData.getTutdfEntryList()){
            for (IDSegment idSegmet : tutdfEntry.getIdSegmentList()){
                writer.write(printSegment(idSegmet));
                writer.newLine();
            }
            NameSegment nameSegment = tutdfEntry.getNameSegment();
            writer.write(printSegment(nameSegment));
            writer.newLine();

            for(PhoneNumberSegment phoneNumberSegment : tutdfEntry.getPhoneNumberSegmentList()){
                writer.write(printSegment(phoneNumberSegment));
                writer.newLine();
            }

            TransactionSegment transactionSegment = tutdfEntry.getTransactionSegment();
            if(transactionSegment != null){
                writer.write(transactionSegment.toString().replace("null", ""));
                writer.newLine();
            }
        }
        TRLRSegment trlrSegment = tutdfData.getTrlrSegment();
        writer.write(printSegment(trlrSegment));
        writer.flush();
        writer.close();
    }

    private String printSegment(Object segment){
        return segment.toString().replace("null", "");
    }
}
