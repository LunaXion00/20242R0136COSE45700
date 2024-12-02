package model;

import java.util.Stack;
import Command.*;
import Singleton.ShapeModelSingleton;

public class CommandManager{
    ShapeModel model = ShapeModelSingleton.getInstance();
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        model.notifyObservers();
    }

    public void undo(){
        if(!undoStack.isEmpty()){
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            model.notifyObservers();
        }
    }
    public void redo(){
        if(!redoStack.isEmpty()){
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            model.notifyObservers();
        }
    }
}
