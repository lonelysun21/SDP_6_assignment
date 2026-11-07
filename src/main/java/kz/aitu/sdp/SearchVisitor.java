package kz.aitu.sdp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class SearchVisitor implements Visitor {
    private final String nameFilter;
    private final long minimumSize;
    private final List<String> matchingPaths = new ArrayList<>();
    private Element root;

    public SearchVisitor(String nameFilter, long minimumSize) {
        if (nameFilter == null || minimumSize < 0) {
            throw new IllegalArgumentException("Invalid search parameters");
        }
        this.nameFilter = nameFilter;
        this.minimumSize = minimumSize;
    }

    @Override
    public void start(Element root) {
        this.root = root;
        matchingPaths.clear();
    }

    @Override
    public void visit(FileNode file) {
        if (file.getName().contains(nameFilter)
                && file.getSize() >= minimumSize) {
            matchingPaths.add(buildPath(file));
        }
    }

    @Override
    public void visit(DirectoryNode directory) {
    }

    private String buildPath(FileNode file) {
        if (file == root) {
            return file.getName();
        }

        Deque<String> parts = new ArrayDeque<>();
        AbstractElement current = file;

        while (current != root) {
            if (current == null) {
                throw new IllegalStateException("File is outside the root");
            }
            parts.addFirst(current.getName());
            current = current.getParent();
        }

        return String.join("/", parts);
    }

    public List<String> getMatchingPaths() {
        List<String> result = new ArrayList<>(matchingPaths);
        Collections.sort(result);
        return result;
    }
}
