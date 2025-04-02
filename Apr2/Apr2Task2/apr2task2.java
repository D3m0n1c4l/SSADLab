public class Main {
    public interface Text {
        String text = null;
        String write();
    }
    public static class PlainText implements Text {
        public String text;
        public PlainText(String text) { this.text = text; }
        public String write() {
            System.out.println(this.text);
            return this.text;
        }
    }

    public static abstract class TextDecorator implements Text {
        Text text;
        String decoratedText;

        public TextDecorator(Text text) { this.text = text; }
        public abstract void decorate();
    }

    public static class BoldDecorator extends TextDecorator {
        public BoldDecorator(Text text) { super(text); }
        public void decorate() {
            String textContent = this.text.write();
            this.decoratedText = "bold{" + textContent + "}";
        }
        public String write() {
            System.out.println(this.decoratedText);
            return this.decoratedText;
        }
    }

    public static class ItalicDecorator extends TextDecorator {
        public ItalicDecorator(Text text) { super(text); }
        public void decorate() {
            String textContent = this.text.write();
            this.decoratedText = "italic{" + textContent + "}";
        }
        public String write() {
            System.out.println(this.decoratedText);
            return this.decoratedText;
        }
    }

    public static class UnderlineDecorator extends TextDecorator {
        public UnderlineDecorator(Text text) { super(text); }
        public void decorate() {
            String textContent = this.text.write();
            this.decoratedText = "underline{" + textContent + "}";
        }
        public String write() {
            System.out.println(this.decoratedText);
            return this.decoratedText;
        }
    }

    public static void main(String[] args) {
        Text plainText = new PlainText("MEOW");
        BoldDecorator boldDecorator = new BoldDecorator(plainText);
        boldDecorator.decorate();
        boldDecorator.write();
        ItalicDecorator italicDecorator = new ItalicDecorator(new PlainText(boldDecorator.decoratedText));
        italicDecorator.decorate();
        italicDecorator.write();
        UnderlineDecorator underlineDecorator = new UnderlineDecorator(new PlainText(italicDecorator.decoratedText));
        underlineDecorator.decorate();
        underlineDecorator.write();
    }
}
