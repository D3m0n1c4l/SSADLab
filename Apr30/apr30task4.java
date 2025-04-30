// Interface for GUI Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Windows Factory
class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton(); // Create a Windows button
    }

    public Checkbox createCheckbox() {
        return new WinCheckbox(); // Create a Windows checkbox
    }
}

// Mac Factory
class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton(); // Create a Mac button
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox(); // Create a Mac checkbox
    }
}

// Abstract Button class
class Button {
    public void paint() {
        System.out.println("Painting a generic button.");
    }
}

// Windows Button
class WinButton extends Button {
    public void paint() {
        System.out.println("Painting a Windows button.");
    }
}

// Mac Button
class MacButton extends Button {
    public void paint() {
        System.out.println("Painting a Mac button.");
    }
}

// Abstract Checkbox class
class Checkbox {
    public void paint() {
        System.out.println("Painting a generic checkbox.");
    }
}

// Windows Checkbox
class WinCheckbox extends Checkbox {
    public void paint() {
        System.out.println("Painting a Windows checkbox.");
    }
}

// Mac Checkbox
class MacCheckbox extends Checkbox {
    public void paint() {
        System.out.println("Painting a Mac checkbox.");
    }
}

// Main class to create and paint UI
public class Main {
    private GUIFactory factory;
    private Button button;
    private Checkbox checkbox;

    public Main(GUIFactory f) {
        this.factory = f;
        createUI(); // Create UI components
    }

    public void createUI() {
        button = factory.createButton(); // Create button using factory
        checkbox = factory.createCheckbox(); // Create checkbox using factory
    }

    public void paint() {
        button.paint(); // Paint the button
        checkbox.paint(); // Paint the checkbox
    }

    public static void main(String[] args) {
        GUIFactory factory;

        // Choose the factory based on the operating system
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            factory = new WinFactory(); // Use Windows factory
        } else {
            factory = new MacFactory(); // Use Mac factory
        }

        Main app = new Main(factory); // Create the main application
        app.paint(); // Paint the UI components
    }
}
