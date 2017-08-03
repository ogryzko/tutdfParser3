import model.TUTDFData;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import static java.nio.file.Files.newInputStream;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by eglushchenko on 01.08.2017.
 */
public class TUTDFFileWriterTest {
    Path outputMultiFile;
    TUTDFFileWriter tutdfWriter;
    TUTDFData tutdfMultiData;
    TUTDFFileParser parser;

    @Before
    public void init() throws IOException {

        parser = new TUTDFFileParser();
        outputMultiFile = Paths.get(this.getClass().getResource("multi_out.txt").getPath().substring(1));
        tutdfWriter = new TUTDFFileWriter(outputMultiFile);
    }

    @Test
    public void testWriteMultiTUTDFData() throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("0002BB000001_20170712_161333.txt");
        tutdfMultiData = parser.parseTUTDFFile(inputStream);
        inputStream = classLoader.getResourceAsStream("0002BB000001_20170712_161333.txt");
        tutdfWriter.write(tutdfMultiData);

        BufferedReader brExpected = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("windows-1251")));
        BufferedReader brActual = new BufferedReader(new InputStreamReader(newInputStream(outputMultiFile), Charset.forName("windows-1251")));
        String currentLineExpected;
        String currentLineActual;
        while ((currentLineExpected = brExpected.readLine()) != null){
            if(parser.segmentTagFromLine(currentLineExpected) == SegmentTag.UNKNOWN) continue;
            else{
                currentLineActual = brActual.readLine();
                assertEquals(currentLineExpected, currentLineActual);
            }
        }
    }
}
