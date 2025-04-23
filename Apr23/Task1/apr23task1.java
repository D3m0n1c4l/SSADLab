import java.util.ArrayList;
import java.util.List;

interface Command {
    void execute();
    void undo();
}

class TextEditor {
    private StringBuilder text;
    private String clipboard;

    public TextEditor() {
        text = new StringBuilder();
    }
    public void addText(String newText) {
        text.append(newText);
    }
    public void replaceText(String newText) {
        text = new StringBuilder(newText);
    }
    public String getText() {
        return text.toString();
    }
    public void setClipboard(String text) {
        clipboard = text;
    }
    public String getClipboard() {
        return clipboard;
    }
}

class CopyCommand implements Command {
    private final TextEditor editor;
    private String backup;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        backup = editor.getText();
        editor.setClipboard(backup);
    }

    @Override
    public void undo() {
        editor.replaceText(backup);
    }
}

class PasteCommand implements Command {
    private final TextEditor editor;
    private String backup;

    public PasteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        backup = editor.getText();
        editor.addText(editor.getClipboard());
    }

    @Override
    public void undo() {
        editor.replaceText(backup);
    }
}

class CommandHistory {
    private final List<Command> history;

    public CommandHistory() {
        history = new ArrayList<>();
    }

    public void push(Command cmd) {
        history.add(cmd);
    }

    public Command pop() {
        if (history.isEmpty()) return null;
        return history.removeLast();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();

        System.out.print("Enter text: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        editor.addText(input);

        Command copy = new CopyCommand(editor);
        Command paste = new PasteCommand(editor);

        copy.execute();
        paste.execute();
        history.push(copy);
        history.push(paste);

        System.out.println("Current text: " + editor.getText());

        if (!history.isEmpty()) {
            Command cmd = history.pop();
            cmd.undo();
            System.out.println("Text after undo: " + editor.getText());
        }

        scanner.close();
    }
}
