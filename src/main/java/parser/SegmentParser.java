package parser;

/**
 * Created by eglushchenko on 03.08.2017.
 */
public abstract class SegmentParser {
    protected final int maxArgsNum;

    public SegmentParser(int maxArgsNum) {
        this.maxArgsNum = maxArgsNum;
    }
}
