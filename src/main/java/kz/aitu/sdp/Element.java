package kz.aitu.sdp;

public interface Element {
    String getName();
    void accept(Visitor visitor);
}
