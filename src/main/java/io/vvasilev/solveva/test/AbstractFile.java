package io.vvasilev.solveva.test;

import java.util.Objects;

public abstract class AbstractFile implements IFile{
    private final String name;
    private Directory parent;

    public AbstractFile(Directory parent, String name) {
        this.parent = parent;
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void delete() {
        parent.delete(this);
    }
}
