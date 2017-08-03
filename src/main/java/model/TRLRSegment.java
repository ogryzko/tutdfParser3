package model;

/**
 * Created by EGlushchenko on 27.07.2017.
 */
public class TRLRSegment {
    private String trailerSegment;
    private Integer Counter;

    public String getTrailerSegment() {
        return trailerSegment;
    }

    public void setTrailerSegment(String trailerSegment) {
        this.trailerSegment = trailerSegment;
    }

    public Integer getCounter() {
        return Counter;
    }

    public void setCounter(Integer counter) {
        Counter = counter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(trailerSegment).append("\t");
        sb.append(Counter);
        return sb.toString();
    }
}
