package io.vvasilev.solveva.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class FileSystemTest {

    @Test
    public void test_createFile() {
        IFileSystem.FileSystem fileSystem = IFileSystem.FileSystem.create();

        File file = fileSystem.createFile(null, "README.txt", "Helllo World");
        Assertions.assertNotNull(file);
        Assertions.assertEquals(Set.of(file), fileSystem.getRoot().open());

        String content = file.open();
        Assertions.assertEquals("Helllo World", content);
        file.delete();
        Assertions.assertEquals(Set.of(), fileSystem.getRoot().open());
    }

    @Test
    public void test_Directory() {
        IFileSystem.FileSystem fileSystem = IFileSystem.FileSystem.create();

        Directory src = fileSystem.createDir(null, "src");
        Assertions.assertNotNull(src);
        Assertions.assertEquals(new HashSet<>(), src.open());

        Directory main = fileSystem.createDir(src, "main");
        Assertions.assertNotNull(main);
        Assertions.assertEquals(Set.of(main), src.open());

        IFile alt = fileSystem.findBy(file -> file.getName().equals("alt"));
        Assertions.assertNull(alt);

        IFile result = fileSystem.findBy(file -> file.getName().equals("main"));
        Assertions.assertNotNull(result);

        src.delete();
        Assertions.assertEquals(Set.of(), fileSystem.getRoot().open());
    }
}
