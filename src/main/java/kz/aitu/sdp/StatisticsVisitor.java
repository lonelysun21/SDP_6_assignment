package kz.aitu.sdp;

public class StatisticsVisitor implements Visitor {
    private int fileCount;
    private int directoryCount;
    private int maxDepth;
    private Element root;

    @Override
    public void start(Element root) {
        this.root = root;
        fileCount = 0;
        directoryCount = 0;
        maxDepth = 0;
    }

    @Override
    public void visit(FileNode file) {
        fileCount++;
        updateDepth(file);
    }

    @Override
    public void visit(DirectoryNode directory) {
        directoryCount++;
        updateDepth(directory);
    }

    private void updateDepth(AbstractElement element) {
        int depth = 0;
        AbstractElement current = element;

        while (current != root) {
            if (current == null) {
                throw new IllegalStateException("Element is outside the root");
            }
            depth++;
            current = current.getParent();
        }

        maxDepth = Math.max(maxDepth, depth);
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirectoryCount() {
        return directoryCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
