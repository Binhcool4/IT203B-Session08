package BTTH.remote;

import BTTH.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoteControl {

    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public void press(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Không có lệnh để undo.");
            return;
        }
        Command lastCommand = undoStack.pop();
        lastCommand.undo();
        redoStack.push(lastCommand);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Không có lệnh để redo.");
            return;
        }
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }
}
