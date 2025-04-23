public class Main {
    public interface Handler {
        void setNext(Handler next);
        boolean handle(String request);
    }

    public static class AbstractHandler implements Handler {
        protected Handler next;
        public void setNext(Handler next) {
            this.next = next;
        }

        public boolean handle(String request) {
            if (next != null) {
                return next.handle(request);
            }
            return true;
        }
    }

    public static class AuthenticationHandler extends AbstractHandler {
        public boolean handle(String request) {
            if (!request.equals("valid")) {
                return false;
            }
            return super.handle(request);
        }
    }

    public static class RoleCheckHandler extends AbstractHandler {
        public boolean handle(String request) {
            if (request.equals("admin")) {
                return true;
            }
            return super.handle(request);
        }
    }

    static void processRequest(String request, Handler handler) {
        System.out.println("Processing: " + request);
        if (!handler.handle(request)) {
            System.out.println("Request denied.\n");
        } else {
            System.out.println("Request approved.\n");
        }
    }

    public static void main(String[] args) {
        Handler roleCheck = new RoleCheckHandler();
        Handler auth = new AuthenticationHandler();

        auth.setNext(roleCheck);
        processRequest("valid", auth);
        processRequest("invalid", auth);
        processRequest("admin", auth);
    }
}
