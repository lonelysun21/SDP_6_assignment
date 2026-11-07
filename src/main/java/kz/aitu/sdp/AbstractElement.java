package kz.aitu.sdp;

public abstract class AbstractElement implements Element {
    private final String name;
    private DirectoryNode parent;

    protected AbstractElement(String name) {
        if (name == null || name.isBlank() || name.contains("/")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    DirectoryNode getParent() {
        return parent;
    }

    void setParent(DirectoryNode parent) {
        this.parent = parent;
    }
}
