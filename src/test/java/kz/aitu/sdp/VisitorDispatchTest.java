package kz.aitu.sdp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorDispatchTest {
    @Test
    void testDoubleDispatch() {
        Element file = new FileNode("test.txt", 100);
        Element directory = new DirectoryNode("documents");

        int[] visits = new int[2];

        Visitor visitor = new Visitor() {
            @Override
            public void visit(FileNode file) {
                visits[0]++;
            }

            @Override
            public void visit(DirectoryNode directory) {
                visits[1]++;
            }
        };

        file.accept(visitor);
        directory.accept(visitor);

        assertEquals(1, visits[0]);
        assertEquals(1, visits[1]);
    }
}
