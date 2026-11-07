package kz.aitu.sdp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalSizeVisitorTest {
    @Test
    void testNestedFileSizes() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("first.txt", 100));

        DirectoryNode documents = new DirectoryNode("documents");
        documents.addChild(new FileNode("second.txt", 250));

        DirectoryNode projects = new DirectoryNode("projects");
        projects.addChild(new FileNode("third.txt", 400));

        documents.addChild(projects);
        root.addChild(documents);

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        FileSystemWalker.walk(root, visitor);

        assertEquals(750, visitor.getTotalSize());
    }
}
