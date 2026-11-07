package kz.aitu.sdp;

public class FileSystemWalker implements Visitor {
    private final Visitor visitor;

    private FileSystemWalker(Visitor visitor) {
        this.visitor = visitor;
    }

    public static void walk(Element root, Visitor visitor) {
        if (root == null || visitor == null) {
            throw new IllegalArgumentException("Root and visitor cannot be null");
        }

        visitor.start(root);
        root.accept(new FileSystemWalker(visitor));
    }

    @Override
    public void visit(FileNode file) {
        file.accept(visitor);
    }

    @Override
    public void visit(DirectoryNode directory) {
        directory.accept(visitor);

        for (AbstractElement child : directory.getChildren()) {
            child.accept(this);
        }
    }
}
