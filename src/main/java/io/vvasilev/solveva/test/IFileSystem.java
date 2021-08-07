package io.vvasilev.solveva.test;

import java.util.function.Predicate;

/**
 * Interface for file system
 */
public interface IFileSystem {

    /**
     * Create Directory
     *
     * @param parent parent Directory
     * @param name of Directory
     * @return new {@link Directory}
     */
    Directory createDir(Directory parent, String name);

    /**
     * Create File
     *
     * @param parent parent Directory
     * @param name of File
     * @param content of File
     * @return new {@link File}
     */
    File createFile(Directory parent, String name, String content);

    /**
     * Find file by predicate {@link Predicate}
     *
     * @param predicate
     * @return
     */
    IFile findBy(Predicate<IFile> predicate);

    class FileSystem implements IFileSystem {
        private final Directory root = new Directory(null, "");

        public static FileSystem create() {
            return new FileSystem();
        }

        /**
         * Get root of FileSystem
         *
         * @return
         */
        public Directory getRoot() {
            return root;
        }

        @Override
        public Directory createDir(Directory parent, String name) {
            if (parent != null) {
                Directory dir = new Directory(parent, name);
                parent.add(dir);
                return dir;
            }
            return createDir(root, name);
        }

        @Override
        public File createFile(Directory parent, String name, String content) {
            if (parent != null) {
                File file = new File(parent, name, content);
                parent.add(file);
                return file;
            }
            return createFile(root, name, content);
        }

        @Override
        public IFile findBy(Predicate<IFile> predicate) {
            return root.findBy(predicate);
        }
    }
}
