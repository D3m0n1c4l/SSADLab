import java.util.ArrayList;
class FormattedCharacter {
    private Character character;
    private FormatType formatType;

    public FormattedCharacter(char c, String f, int s, String col) {
        this.character = c;
        this.formatType = new FormatType(f, s, col);
    }
    public FormatType getFormatType() { return this.formatType; }
    public Character getCharacter() { return this.character; }

    public void print() {
        System.out.println("Character: " + character + " with properties: " +
                "Font: " + formatType.font +
                ", Size: " + formatType.size +
                ", Color: " + formatType.color);
    }
}
class FormatType {
    String font;
    Integer size;
    String color;

    public FormatType() {
        this.font = "Times New Roman";
        this.size = 14;
        this.color = "Black";
    }
    public FormatType(String font, Integer size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }
}
class FormatFactory {
    private ArrayList<FormattedCharacter> formattedCharacters = new ArrayList<>();
    public FormattedCharacter getFormatType(int repeatingState) {
        return formattedCharacters.get(repeatingState);
    }
    public void updateFormatType(FormattedCharacter formattedCharacter) {
        formattedCharacters.add(formattedCharacter);
    }

    public ArrayList<FormattedCharacter> getFormatTypes() { return formattedCharacters; }
}

public class Main {
    public static void main(String[] args) {
        FormatFactory document = new FormatFactory();

        document.updateFormatType(new FormattedCharacter('H', "Arial", 12, "Red"));
        document.updateFormatType(new FormattedCharacter('e', "Arial", 12, "Red"));
        document.updateFormatType(new FormattedCharacter('l', "Arial", 12, "Red"));
        document.updateFormatType(new FormattedCharacter('l', "Arial", 12, "Red"));
        document.updateFormatType(new FormattedCharacter('o', "Arial", 12, "Red"));

        document.updateFormatType(new FormattedCharacter(' ', "Times New Roman", 12, "Black"));

        document.updateFormatType(new FormattedCharacter('W', "Arial", 14, "Blue"));
        document.updateFormatType(new FormattedCharacter('o', "Arial", 14, "Blue"));
        document.updateFormatType(new FormattedCharacter('r', "Arial", 14, "Blue"));
        document.updateFormatType(new FormattedCharacter('l', "Arial", 14, "Blue"));
        document.updateFormatType(new FormattedCharacter('d', "Arial", 14, "Blue"));

        document.updateFormatType(new FormattedCharacter('!', "Arial", 16, "Green"));

        System.out.println("Document content:");
        for (FormattedCharacter fc : document.getFormatTypes()) {
            fc.print();
        }
    }
}
