package kz.aitu.sdp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsVisitorTest {
    @Test
    void testStatistics() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("readme.txt", 100));

        DirectoryNode documents = new DirectoryNode("documents");
        DirectoryNode archive = new DirectoryNode("archive");

        archive.addChild(new FileNode("old.txt", 200));
        documents.addChild(archive);

        root.addChild(documents);
        root.addChild(new DirectoryNode("empty"));

        StatisticsVisitor visitor = new StatisticsVisitor();
        FileSystemWalker.walk(root, visitor);

        assertEquals(2, visitor.getFileCount());
        assertEquals(4, visitor.getDirectoryCount());
        assertEquals(3, visitor.getMaxDepth());
    }
}
