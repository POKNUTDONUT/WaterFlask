public class WaterFlask {
    private double temperature; // Current temperature in Celsius
    private double capacity; // Maximum capacity in ml
    private double currentVolume; // Current volume in ml

    public WaterFlask(double initialTemperature, double initialVolume) {
        this.temperature = initialTemperature;
        this.capacity = 500; // Setting the maximum capacity to 500 ml
        if (initialVolume <= capacity) {
            this.currentVolume = initialVolume;
        } else {
            this.currentVolume = capacity; // Ensuring the initial volume does not exceed the capacity
        }
    }

    // Method to heat the flask
    public void heat(double degrees) {
        temperature += degrees;
        System.out.println("Heated by " + degrees + "°C.");
        display();
    }

    // Method to cool the flask
    public void cool(double degrees) {
        temperature -= degrees;
        System.out.println("Cooled by " + degrees + "°C.");
        display();
    }

    // Method to fill the flask
    public void fill(double volume) {
        if (currentVolume + volume <= capacity) {
            currentVolume += volume;
            System.out.println("Added " + volume + " ml of water.");
        } else {
            System.out.println("Cannot add " + volume + " ml. Exceeds capacity!");
            currentVolume = capacity;
        }
        display();
    }

    // Method to drink from the flask
    public void drink(double volume) {
        if (currentVolume - volume >= 0) {
            currentVolume -= volume;
            System.out.println("Drank " + volume + " ml of water.");
        } else {
            System.out.println("Cannot drink " + volume + " ml. Not enough water!");
            currentVolume = 0;
        }
        display();
    }

    // Method to display the flask with ASCII art based on temperature and volume
    public void display() {
        System.out.println("\nCurrent Temperature: " + temperature + "°C");
        System.out.println("Current Volume: " + currentVolume + " ml / " + capacity + " ml");
        if (temperature < 0) {
            displayCold();
        } else if (temperature >= 0 && temperature <= 20) {
            displayCool();
        } else if (temperature > 20 && temperature <= 25) {
            displayRoomTemp();
        } else if (temperature > 25 && temperature <= 40) {
            displayWarm();
        } else {
            displayHot();
        }
        System.out.println(); // Extra line for readability
    }

    // ASCII Art for Cold temperatures
    private void displayCold() {
        System.out.println("    * * * * *");
        System.out.println("   *         *");
        System.out.println("  *  ICE    *");
        System.out.println("  *         *");
        System.out.println("   *       *");
        System.out.println("    * * * *");
        System.out.println("      |||");
        System.out.println("      |||");
        System.out.println("      |||");
    }

    // ASCII Art for Cool temperatures
    private void displayCool() {
        System.out.println("    _______");
        System.out.println("   /       \\");
        System.out.println("  |  COOL   |");
        System.out.println("  |         |");
        System.out.println("   \\_______/");
        System.out.println("      |||");
        System.out.println("      |||");
        System.out.println("      |||");
    }

    // ASCII Art for Room Temperature
    private void displayRoomTemp() {
        System.out.println("    _______");
        System.out.println("   /       \\");
        System.out.println("  |  ROOM   |");
        System.out.println("  | TEMP    |");
        System.out.println("   \\_______/");
        System.out.println("      |||");
        System.out.println("      |||");
        System.out.println("      |||");
    }

    // ASCII Art for Warm temperatures
    private void displayWarm() {
        System.out.println("    _______");
        System.out.println("   /       \\");
        System.out.println("  |  WARM   |");
        System.out.println("  |         |");
        System.out.println("   \\_______/");
        System.out.println("      |||");
        System.out.println("     ~|||~");
        System.out.println("    ~ ||| ~");
    }

    // ASCII Art for Hot temperatures
    private void displayHot() {
        System.out.println("    _______");
        System.out.println("   /       \\");
        System.out.println("  |  HOT    |");
        System.out.println("  |         |");
        System.out.println("   \\_______/");
        System.out.println("      |||");
        System.out.println("    ~ ~|||~ ~");
        System.out.println("   ~ ~ ||| ~ ~");
        System.out.println("  ~ ~  |||  ~ ~");
    }

    // Main method to demonstrate the WaterFlask class
    public static void main(String[] args) {
        WaterFlask flask = new WaterFlask(25, 250); // Initialize with 25°C and 250 ml
        flask.display();

        // Example interactions
        flask.heat(10);   // Increase to 35°C
        flask.fill(100);  // Add 100 ml
        flask.drink(50);  // Drink 50 ml
        flask.heat(10);   // Increase to 45°C
        flask.cool(20);   // Decrease to 25°C
        flask.drink(400); // Attempt to drink 400 ml (more than available)
        flask.cool(30);   // Decrease to -5°C
    }
}