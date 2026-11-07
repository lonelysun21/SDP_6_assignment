package kz.aitu.sdp;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends AbstractElement {
    private final List<AbstractElement> children = new ArrayList<>();

    public DirectoryNode(String name) {
        super(name);
    }

    public void addChild(AbstractElement child) {
        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }

        if (child.getParent() != null) {
            throw new IllegalArgumentException("Element already has a parent");
        }

        for (DirectoryNode current = this; current != null; current = current.getParent()) {
            if (current == child) {
                throw new IllegalArgumentException("Cycle detected");
            }
        }

        for (AbstractElement existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException("Duplicate name");
            }
        }

        children.add(child);
        child.setParent(this);
    }

    public List<AbstractElement> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
