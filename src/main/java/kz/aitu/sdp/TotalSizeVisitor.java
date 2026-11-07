package kz.aitu.sdp;

public class TotalSizeVisitor implements Visitor {
    private long totalSize;

    @Override
    public void start(Element root) {
        totalSize = 0;
    }

    @Override
    public void visit(FileNode file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(DirectoryNode directory) {
    }

    public long getTotalSize() {
        return totalSize;
    }
}
