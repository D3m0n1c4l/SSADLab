import java.util.Stack;

// Memento: holds editor state
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}

// Originator: the text editor
class TextEditor {
    private String text;

    public TextEditor() {
        this.text = ""; // Initialize text
    }

    public void appendText(String newText) {
        this.text += newText;
    }

    public String getText() {
        return this.text;
    }

    // Capture current state
    public Memento save() {
        return new Memento(this.text); // Create a new Memento with the current text
    }

    // Restore to a previous state
    public void restore(Memento memento) {
        this.text = memento.getState(); // Restore the text from the Memento
    }
}

// Caretaker: manages undo/redo stacks
class UndoRedoManager {
    private Stack<Memento> undoStack;
    private Stack<Memento> redoStack;

    public UndoRedoManager() {
        undoStack = new Stack<>(); // Initialize the undo stack
        redoStack = new Stack<>(); // Initialize the redo stack
    }

    // Push current editor state onto undo stack and clear redo
    public void saveState(TextEditor editor) {
        undoStack.push(editor.save());
        redoStack.clear(); // Clear the redo stack
    }

    // Pop from undo, push to redo, restore previous
    public void undo(TextEditor editor) {
        if (!undoStack.isEmpty()) {
            Memento item = undoStack.pop();
            redoStack.push(item);
            editor.restore(item);
        }
    }

    // Pop from redo, push to undo, restore
    public void redo(TextEditor editor) {
        if (!redoStack.isEmpty()) {
            Memento item = redoStack.pop();
            undoStack.push(item);
            editor.restore(item);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        UndoRedoManager undoRedoManager = new UndoRedoManager();

        // Initial text
        System.out.println("Initial text: " + editor.getText());

        // Append some text and save state
        editor.appendText("Hello");
        undoRedoManager.saveState(editor);
        System.out.println("After appending 'Hello': " + editor.getText());

        // Append more text and save state
        editor.appendText(" World");
        undoRedoManager.saveState(editor);
        System.out.println("After appending ' World': " + editor.getText());

        // Undo and show state
        undoRedoManager.undo(editor);
        System.out.println("After undoing: " + editor.getText());

        // Redo and show state
        undoRedoManager.redo(editor);
        System.out.println("After redoing: " + editor.getText());
    }
}
