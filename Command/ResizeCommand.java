package Command;

import Handle.ResizeHandle;

import java.awt.*;

public class ResizeCommand implements Command{
    private final ResizeHandle handle;
    private final Point initialPoint;
    private final Point finalPoint;

    public ResizeCommand(ResizeHandle handle, Point initialPoint, Point finalPoint) {
        this.handle = handle;
        this.initialPoint = initialPoint;
        this.finalPoint = finalPoint;
    }

    @Override
    public void execute() {
        System.out.println("ResizeCommand Executed");
        handle.endEdit();
    }

    @Override
    public void undo() {
        handle.resize(finalPoint, initialPoint);
        handle.endEdit();
    }

    @Override
    public void redo() {
        System.out.println("ResizeCommand Redo");
        handle.resize(initialPoint,finalPoint);
        execute();
    }
}
