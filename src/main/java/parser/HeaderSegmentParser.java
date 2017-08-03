package parser;

import model.HeaderSegment;

/**
 * Created by eglushchenko on 03.08.2017.
 */
public class HeaderSegmentParser extends SegmentParser implements IParser<HeaderSegment> {

    public HeaderSegmentParser(int maxArgsNum) {
        super(maxArgsNum);
    }

    @Override
    public HeaderSegment parse(String currentLine) {
        String[] strArr = currentLine.split("\t", -1);
        if(strArr.length != maxArgsNum) return null;
        HeaderSegment headerSegment = new headerSegmentBuilder(currentLine);
        return null;
    }
}
