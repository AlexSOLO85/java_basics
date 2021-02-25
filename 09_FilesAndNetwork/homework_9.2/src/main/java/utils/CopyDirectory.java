package utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public final class CopyDirectory extends SimpleFileVisitor<Path> {
    private final Path sourceDir;
    private final Path destinationDir;

    protected CopyDirectory(Path sourceDir, Path destinationDir) {
        this.sourceDir = sourceDir;
        this.destinationDir = destinationDir;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        try {
            Path destinationFile = destinationDir.resolve(sourceDir.relativize(file));
            Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        try {
            Path newDir = destinationDir.resolve(sourceDir.relativize(dir));
            Files.createDirectory(newDir);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }
}