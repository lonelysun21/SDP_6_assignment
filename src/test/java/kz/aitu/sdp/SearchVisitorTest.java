package kz.aitu.sdp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchVisitorTest {
    @Test
    void testSearchFilters() {
        DirectoryNode root = new DirectoryNode("root");

        root.addChild(new FileNode("small.txt", 50));
        root.addChild(new FileNode("large.txt", 300));
        root.addChild(new FileNode("image.png", 500));

        SearchVisitor visitor = new SearchVisitor(".txt", 100);
        FileSystemWalker.walk(root, visitor);

        assertEquals(List.of("large.txt"), visitor.getMatchingPaths());
    }

    @Test
    void testSortedRelativePaths() {
        DirectoryNode root = new DirectoryNode("root");

        DirectoryNode zFolder = new DirectoryNode("zFolder");
        zFolder.addChild(new FileNode("second.txt", 200));

        DirectoryNode aFolder = new DirectoryNode("aFolder");
        aFolder.addChild(new FileNode("first.txt", 300));

        root.addChild(zFolder);
        root.addChild(aFolder);
        root.addChild(new FileNode("third.txt", 400));

        SearchVisitor visitor = new SearchVisitor(".txt", 0);
        FileSystemWalker.walk(root, visitor);

        assertEquals(
                List.of(
                        "aFolder/first.txt",
                        "third.txt",
                        "zFolder/second.txt"
                ),
                visitor.getMatchingPaths()
        );
    }
}
