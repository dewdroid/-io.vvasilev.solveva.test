package io.vvasilev.solveva.test;

import java.util.Objects;

public class File extends AbstractFile implements IFile{
    private final String content;

    public File(Directory parent, String name, String content) {
        super(parent, name);
        this.content = Objects.requireNonNull(content);
    }

    /**
     * Open file to see its content
     *
     * @return
     */
    public String open() {
        return content;
    }

    @Override
    public int getSize() {
        return content.length();
    }
}
