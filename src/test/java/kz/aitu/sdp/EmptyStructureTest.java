package kz.aitu.sdp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyStructureTest {
    @Test
    void testEmptyDirectoryAndNoMatches() {
        DirectoryNode root = new DirectoryNode("root");

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        FileSystemWalker.walk(root, sizeVisitor);

        assertEquals(0, sizeVisitor.getTotalSize());

        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();
        FileSystemWalker.walk(root, statisticsVisitor);

        assertEquals(0, statisticsVisitor.getFileCount());
        assertEquals(1, statisticsVisitor.getDirectoryCount());
        assertEquals(0, statisticsVisitor.getMaxDepth());

        SearchVisitor searchVisitor = new SearchVisitor(".txt", 100);
        FileSystemWalker.walk(root, searchVisitor);

        assertEquals(List.of(), searchVisitor.getMatchingPaths());

        root.addChild(new FileNode("image.png", 500));
        FileSystemWalker.walk(root, searchVisitor);

        assertEquals(List.of(), searchVisitor.getMatchingPaths());
    }
}
