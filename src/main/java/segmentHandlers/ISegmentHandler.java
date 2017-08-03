package segmentHandlers;


/**
 * Created by eglushchenko on 03.08.2017.
 */
public interface ISegmentHandler {
    public Segment Process(IContext context, String line);
}
