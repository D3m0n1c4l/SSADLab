import java.util.ArrayList;

abstract class Device {
    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
    public abstract void setMediator(SmartHouseMediator mediator);
}

class MotionSensor extends Device {
    private SmartHouseMediator mediator;

    public void sendMessage(String message) {
        mediator.mediate(this, message);
    }

    public void receiveMessage(String message) {
        System.out.println("MotionSensor received a " + message
                + ", the owner will be notified that they expect a visitor");
    }

    public void setMediator(SmartHouseMediator med) {
        this.mediator = med;
    }
}

class TemperatureSensor extends Device {
    private SmartHouseMediator mediator;

    public void sendMessage(String message) {
        mediator.mediate(this, message);
    }

    public void receiveMessage(String message) {
        System.out.println("TemperatureSensor received a " + message
                + ", the room temperature will be adjusted");
    }

    public void setMediator(SmartHouseMediator med) {
        this.mediator = med;
    }
}

class SmartHouseMediator {
    private final ArrayList<Device> devices = new ArrayList<>();

    public void addDevice(Device device) {
        devices.add(device);
        device.setMediator(this);
    }

    public void mediate(Device sender, String message) {
        for (Device device : devices) {
            if (!device.equals(sender)) {
                device.receiveMessage(message);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SmartHouseMediator mediator = new SmartHouseMediator();
        MotionSensor motion = new MotionSensor();
        TemperatureSensor temp = new TemperatureSensor();

        mediator.addDevice(motion);
        mediator.addDevice(temp);

        motion.sendMessage("Motion detected");
        temp.sendMessage("Motion detected");
    }
}
