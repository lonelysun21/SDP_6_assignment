package kz.aitu.sdp;

public interface Visitor {
    default void start(Element root) {
    }

    void visit(FileNode file);
    void visit(DirectoryNode directory);
}
