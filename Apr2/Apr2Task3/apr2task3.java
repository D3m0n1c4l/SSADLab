public class Main {
    public interface Document {
        void display();
    }
    public enum Role {
        USER, ADMINISTRATOR
    }
    public static class RealDocument implements Document {
        String documentText;
        public RealDocument(String documentText) { this.documentText = documentText; }
        public void display() {
            System.out.println(this.documentText);
        }
    }
    public static class SecureDocumentProxy implements Document {
        private final RealDocument realDocument;
        private boolean hasAccess = false;
        private Role role;

        public SecureDocumentProxy(RealDocument realDocument) {
            this.realDocument = realDocument;
        }
        public void setRole(Role role) { this.role = role; }
        public void checkAccess() {
            this.hasAccess = (this.role == Role.ADMINISTRATOR);
        }
        public void display() {
            this.checkAccess();
            if (this.hasAccess) realDocument.display();
            else System.out.println("You do not have access!");
        }
    }

    public static void main(String[] args) {
        RealDocument realDocument = new RealDocument("Super Secret Information");
        SecureDocumentProxy secureDocumentProxy = new SecureDocumentProxy(realDocument);

        // User scenario
        secureDocumentProxy.setRole(Role.USER);
        secureDocumentProxy.display();

        // Administration scenario
        secureDocumentProxy.setRole(Role.ADMINISTRATOR);
        secureDocumentProxy.display();
    }
}
