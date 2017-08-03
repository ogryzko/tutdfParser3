package parser;

/**
 * Created by eglushchenko on 03.08.2017.
 */
public interface IParser<T> {
    public T parse(String currentLine);
}
