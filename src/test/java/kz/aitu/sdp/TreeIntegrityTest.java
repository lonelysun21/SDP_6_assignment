package kz.aitu.sdp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreeIntegrityTest {
    @Test
    void testInvalidTreeChanges() {
        assertThrows(IllegalArgumentException.class,
                () -> new FileNode("bad.txt", -10));

        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode documents = new DirectoryNode("documents");
        DirectoryNode archive = new DirectoryNode("archive");

        root.addChild(documents);
        documents.addChild(archive);

        assertThrows(IllegalArgumentException.class,
                () -> archive.addChild(root));

        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(root));

        root.addChild(new FileNode("notes.txt", 100));

        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(new FileNode("notes.txt", 200)));

        FileNode shared = new FileNode("shared.txt", 50);
        root.addChild(shared);

        DirectoryNode other = new DirectoryNode("other");

        assertThrows(IllegalArgumentException.class,
                () -> other.addChild(shared));

        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(null));

        archive.addChild(new FileNode("valid.txt", 20));

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        FileSystemWalker.walk(root, visitor);

        assertEquals(170, visitor.getTotalSize());
        assertEquals(3, root.getChildren().size());
        assertEquals(0, other.getChildren().size());
    }

    @Test
    void testChildrenCannotBeModifiedDirectly() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("first.txt", 100));

        assertThrows(UnsupportedOperationException.class,
                () -> root.getChildren().add(new FileNode("second.txt", 200)));

        assertEquals(1, root.getChildren().size());
    }
}
