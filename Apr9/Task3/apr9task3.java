interface ITransport {
    void deliver();
}
enum TransportType {
    Truck, Ship, Plane
}
class Truck implements ITransport {
    private final String address;
    public Truck(String address) { this.address = address; }
    public void deliver() {
        System.out.println("[+] A truck is delivering to address: " + this.address);
    }
}
class Ship implements ITransport {
    private final String country;
    public Ship(String country) { this.country = country; }
    public void deliver() {
        System.out.println("[+] A ship is delivering to country: " + this.country);
    }
}
class Plane implements ITransport {
    private final String country;
    public Plane(String country) { this.country = country; }
    public void deliver() {
        System.out.println("[+] A plane is delivering to country: " + this.country);
    }
}
class TransportFactory {
    public ITransport getTransport(TransportType transportType, String filler) {
        if (transportType == TransportType.Plane) return new Plane(filler);
        if (transportType == TransportType.Ship) return new Ship(filler);
        if (transportType == TransportType.Truck) return new Truck(filler);
        return null;
    }
}
public class Main {
    public static void main(String[] args) {
        TransportFactory transportFactory = new TransportFactory();
        Truck truck1 = (Truck) transportFactory.getTransport(TransportType.Truck,
                "Innopolis, Universitetskaya Str., 1");
        Truck truck2 = (Truck) transportFactory.getTransport(TransportType.Truck,
                "Kazan, Pravobulachnaya Str., 16");
        Plane plane1 = (Plane) transportFactory.getTransport(TransportType.Plane,
                "Tatarstan Republic");
        Plane plane2 = (Plane) transportFactory.getTransport(TransportType.Plane,
                "Kiribati");
        Ship ship1 = (Ship) transportFactory.getTransport(TransportType.Ship,
                "Belarus");
        Ship ship2 = (Ship) transportFactory.getTransport(TransportType.Ship,
                "Egypt");

        truck1.deliver();
        truck2.deliver();
        plane1.deliver();
        plane2.deliver();
        ship1.deliver();
        ship2.deliver();
    }
}
