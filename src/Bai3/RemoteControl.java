package Bai3;
public class RemoteControl {
    private Command[] buttons = new Command[5];
    private Command lastCommand;
    public void setCommand(int slot, Command command) {
        buttons[slot] = command;
        System.out.println("Đã gán command cho nút " + slot);

    }
    public void pressButton(int slot) {
        if (buttons[slot] != null) {
            buttons[slot].execute();
            lastCommand = buttons[slot];
        }
    }
    public void undo() {
        if (lastCommand != null) {
            System.out.print("Undo: ");
            lastCommand.undo();
        }
    }
}