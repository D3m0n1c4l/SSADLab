public class Main {
    public enum ThermostatMode {
        ECO, COMFORTABLE
    }
    public enum ActivationState {
        ON, OFF
    }
    public enum SmartHomeScenario {
        LeavingHome, ArrivingHome
    }

    public static class Light {
        private ActivationState lightState;
        public Light(ActivationState lightState) { this.lightState = lightState; }
        public void on() { this.lightState = ActivationState.ON; }
        public void off() { this.lightState = ActivationState.OFF; }
    }
    public static class Thermostat {
        private ThermostatMode thermostatMode;
        public Thermostat(ThermostatMode thermostatMode) { this.thermostatMode = thermostatMode; }
        public void setTemperature(ThermostatMode thermostatMode) {
            this.thermostatMode = thermostatMode;
        }
    }
    public static class SecurityCamera {
        private ActivationState isActivated;
        public SecurityCamera(ActivationState isActivated) { this.isActivated = isActivated; }
        public void activate() { this.isActivated = ActivationState.ON; }
        public void deactivate() { this.isActivated = ActivationState.OFF; }
    }

    public static class SmartHomeFacade {
        private Light lightSystem;
        private Thermostat thermostatSystem;
        private SecurityCamera securitySystem;

        public SmartHomeFacade() {
            lightSystem = new Light(ActivationState.ON);
            thermostatSystem = new Thermostat(ThermostatMode.COMFORTABLE);
            securitySystem = new SecurityCamera(ActivationState.ON);
        }

        private void executeScenario(SmartHomeScenario scenario) {
            if (scenario == SmartHomeScenario.ArrivingHome) {
                this.lightSystem.on();
                this.thermostatSystem.setTemperature(ThermostatMode.COMFORTABLE);
                this.securitySystem.deactivate();
            } else {
                this.lightSystem.off();
                this.thermostatSystem.setTemperature(ThermostatMode.ECO);
                this.securitySystem.activate();
            }
        }

        public void leavingHome() { executeScenario(SmartHomeScenario.LeavingHome); }
        public void arrivingHome() { executeScenario(SmartHomeScenario.ArrivingHome); }
        public void status() {
            System.out.println("SmartHome={lights={" + lightSystem.lightState + "},temperature={" +
                    thermostatSystem.thermostatMode + "},security={" + securitySystem.isActivated + "}}");
        }
    }

    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();
        smartHome.leavingHome();
        smartHome.status();
        smartHome.arrivingHome();
        smartHome.status();
    }
}
