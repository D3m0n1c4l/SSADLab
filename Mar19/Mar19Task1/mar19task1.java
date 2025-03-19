import java.util.Scanner;

public class Mar19Task1 {
    // Logger
    public static class Logger {
        static DraftState state = new DraftState();
        static Document document = new Document();
        static String message;

        private static final Logger unique = new Logger(state, document);
        public static Logger getInstance() { return unique; }
        public static void log(String message) {
            Logger.message = message;
        }

        public Logger(DraftState state, Document document) {
            Logger.state = state;
            Logger.document = document;
        }
    }

    // Document States
    abstract static class DocumentState {
        abstract void handleInput(String input);
    }
    public static class DraftState extends DocumentState {
        public DraftState() {}
        void handleInput(String input) {
            System.out.println("[?] Draft State is handling input...");
        }
        ReviewState next() { return new ReviewState(); }
    }
    public static class ReviewState extends DocumentState {
        public ReviewState() {}
        void handleInput(String input) {
            System.out.println("[?] Review State is handling input... ");
        }
        FinalState next() { return new FinalState(); }
    }
    public static class FinalState extends DocumentState {
        public FinalState() {}
        void handleInput(String input) {
            System.out.println("[+] Final State has handled input.");
            System.out.println("[+] Result: " + input);
        }
        FinalState next() { return new FinalState(); }
    }

    // Document Prototypes
    abstract static class DocumentPrototype {
        public abstract DocumentPrototype clone();
    }
    public static class ReportType extends DocumentPrototype {
        ReportType reportType;
        public ReportType(ReportType reportType) {
            this.reportType = reportType;
        }
        public ReportType() {}
        public ReportType clone() { return new ReportType(this); }
    }
    public static class InvoiceType extends DocumentPrototype {
        InvoiceType invoiceType;
        public InvoiceType(InvoiceType invoiceType) {
            this.invoiceType = invoiceType;
        }
        public InvoiceType() {}
        public InvoiceType clone() { return new InvoiceType(this); }
    }

    // Document
    public static class Document {
        public Document() { System.out.println("[+] Document has been created."); }
        DocumentPrototype prototype;
        DocumentState state;
        void changeState(DocumentState newState) {
            this.state = newState;
        }
        void changePrototype(DocumentPrototype newPrototype) {
            this.prototype = newPrototype;
            System.out.println("[!] The state of the Document has been changed.");
        }
    }

    public static void main(String[] args) {
        Logger.log(new Scanner(System.in).nextLine());
        Logger.document.changeState(Logger.state);
        Logger.document.changePrototype(new ReportType());
        Logger.document.changePrototype(new InvoiceType());
        Logger.document.state.handleInput(Logger.message);
        Logger.document.changePrototype(new ReportType());
        Logger.document.changePrototype(new InvoiceType());
        Logger.document.changeState(Logger.state.next());
        Logger.document.state.handleInput(Logger.message);
        Logger.document.changePrototype(new ReportType());
        Logger.document.changePrototype(new InvoiceType());
        Logger.document.changePrototype(new ReportType());
        Logger.document.changeState(Logger.state.next().next());
        Logger.document.state.handleInput(Logger.message);
    }
}
