package ulpgc.imageviewer;

public interface Image {
    String name();
    Image next();
    Image prev();
}
