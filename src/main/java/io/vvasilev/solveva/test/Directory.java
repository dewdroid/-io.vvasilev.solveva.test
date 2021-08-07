package io.vvasilev.solveva.test;

import java.util.*;
import java.util.function.Predicate;

public class Directory extends AbstractFile implements IFile{
    private final Map<String, IFile> children = new HashMap<>();

    public Directory(Directory parent, String name) {
        super(parent, name);
    }

    public void add(IFile file) {
        this.children.put(file.getName(), file);
    }

    /**
     * Open directory to see other files in it
     *
     * @return
     */
    public Set<IFile> open() {
        return new HashSet<>(children.values());
    }

    @Override
    public int getSize() {
        return children.size();
    }

    public void delete(IFile file) {
        children.remove(file.getName());
    }

    public IFile findBy(Predicate<IFile> predicate) {
        for (IFile child: children.values()) {
            if (predicate.test(child)) {
                return child;
            }
            if (child instanceof Directory) {
                IFile result = ((Directory) child).findBy(predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
