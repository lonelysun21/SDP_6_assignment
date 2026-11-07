package kz.aitu.sdp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedExecutionTest {
    @Test
    void testRepeatedExecutions() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("notes.txt", 100));

        DirectoryNode documents = new DirectoryNode("documents");
        documents.addChild(new FileNode("report.txt", 300));
        root.addChild(documents);

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        SearchVisitor searchVisitor = new SearchVisitor(".txt", 200);
        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();

        for (int i = 0; i < 2; i++) {
            FileSystemWalker.walk(root, sizeVisitor);
            FileSystemWalker.walk(root, searchVisitor);
            FileSystemWalker.walk(root, statisticsVisitor);

            assertEquals(400, sizeVisitor.getTotalSize());
            assertEquals(List.of("documents/report.txt"),
                    searchVisitor.getMatchingPaths());
            assertEquals(2, statisticsVisitor.getFileCount());
            assertEquals(2, statisticsVisitor.getDirectoryCount());
            assertEquals(2, statisticsVisitor.getMaxDepth());
        }

        DirectoryNode anotherRoot = new DirectoryNode("another");
        anotherRoot.addChild(new FileNode("small.txt", 50));

        FileSystemWalker.walk(anotherRoot, sizeVisitor);
        FileSystemWalker.walk(anotherRoot, searchVisitor);
        FileSystemWalker.walk(anotherRoot, statisticsVisitor);

        assertEquals(50, sizeVisitor.getTotalSize());
        assertEquals(List.of(), searchVisitor.getMatchingPaths());
        assertEquals(1, statisticsVisitor.getFileCount());
        assertEquals(1, statisticsVisitor.getDirectoryCount());
        assertEquals(1, statisticsVisitor.getMaxDepth());
    }
}
