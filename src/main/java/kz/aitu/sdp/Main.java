package kz.aitu.sdp;

public class Main {
    public static void main(String[] args) {
        DirectoryNode root = new DirectoryNode("root");

        root.addChild(new FileNode("readme.txt", 120));
        root.addChild(new FileNode("photo.png", 900));

        DirectoryNode documents = new DirectoryNode("documents");
        documents.addChild(new FileNode("notes.txt", 200));

        DirectoryNode projects = new DirectoryNode("projects");
        projects.addChild(new FileNode("report.txt", 350));

        documents.addChild(projects);
        root.addChild(documents);

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        FileSystemWalker.walk(root, sizeVisitor);

        System.out.println("Total size: " + sizeVisitor.getTotalSize() + " bytes");

        SearchVisitor searchVisitor = new SearchVisitor(".txt", 200);
        FileSystemWalker.walk(root, searchVisitor);

        System.out.println("\nMatching files:");
        for (String path : searchVisitor.getMatchingPaths()) {
            System.out.println(path);
        }

        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();
        FileSystemWalker.walk(root, statisticsVisitor);

        System.out.println("\nStatistics:");
        System.out.println("Files: " + statisticsVisitor.getFileCount());
        System.out.println("Directories: " + statisticsVisitor.getDirectoryCount());
        System.out.println("Maximum depth: " + statisticsVisitor.getMaxDepth());
    }
}
