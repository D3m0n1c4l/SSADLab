import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static class File {
        public String path;
        public File(String path) { this.path = path; }
    }

    abstract static class EventListener {
        public EventListener() {}
        abstract void update(String eventType, File file);
    }

    static class EventManager {
        private final HashMap<String, ArrayList<EventListener>> listeners;

        public EventManager(ArrayList<String> operations) {
            listeners = new HashMap<>();
            for (String operation : operations) {
                listeners.put(operation, new ArrayList<>());
            }
        }

        public void subscribe(String eventType, EventListener listener) {
            listeners.get(eventType).add(listener);
        }

        public void unsubscribe(String eventType, EventListener listener) {
            listeners.get(eventType).remove(listener);
        }

        public void notify(String eventType, File file) {
            for (EventListener listener : listeners.get(eventType)) {
                listener.update(eventType, file);
            }
        }
    }

    static class Editor {
        public EventManager events;
        public File file;

        public Editor() {
            ArrayList<String> operations = new ArrayList<>();
            operations.add("open");
            operations.add("save");
            events = new EventManager(operations);
        }

        public void openFile(String filePath) {
            file = new File(filePath);
            events.notify("open", file);
        }

        public void saveFile() {
            if (file == null) {
                System.out.println("No file is open!");
                return;
            }
            events.notify("save", file);
        }
    }


    static class LoggingListener extends EventListener {
        @Override
        void update(String eventType, File file) {
            System.out.println("Logging: " + eventType + " operation on file: " + file.path);
        }
    }


    static class EmailNotificationListener extends EventListener {
        @Override
        void update(String eventType, File file) {
            System.out.println("Email Notification: " + eventType + " operation on file: " + file.path);
        }
    }

    public static void main(String[] args) {
        Editor editor = new Editor();

        LoggingListener loggingListener = new LoggingListener();
        EmailNotificationListener emailListener = new EmailNotificationListener();

        editor.events.subscribe("open", loggingListener);
        editor.events.subscribe("save", loggingListener);
        editor.events.subscribe("open", emailListener);
        editor.events.subscribe("save", emailListener);

        editor.openFile("document.txt");
        editor.saveFile();
        editor.openFile("image.png");
        editor.saveFile();
    }
}
