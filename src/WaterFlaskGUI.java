import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaterFlaskGUI {
    private JFrame frame;
    private JLabel temperatureLabel, volumeLabel, flaskDisplayLabel;
    private JTextField temperatureField, volumeField;
    private JButton heatButton, coolButton, fillButton, drinkButton;
    private WaterFlask flask;

    public WaterFlaskGUI() {
        flask = new WaterFlask(25, 250); // Initialize with 25°C and 250 ml

        frame = new JFrame("Water Flask");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a panel for inputs and buttons
        JPanel controlPanel = new JPanel(new GridLayout(2, 4, 5, 5)); // GridLayout with gaps
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Labels and text fields
        temperatureLabel = new JLabel("Temperature (°C):");
        temperatureField = new JTextField();
        volumeLabel = new JLabel("Volume (ml):");
        volumeField = new JTextField();

        // Buttons
        heatButton = new JButton("Heat");
        coolButton = new JButton("Cool");
        fillButton = new JButton("Fill");
        drinkButton = new JButton("Drink");

        // Set button sizes
        Dimension buttonSize = new Dimension(60, 30);
        heatButton.setPreferredSize(buttonSize);
        coolButton.setPreferredSize(buttonSize);
        fillButton.setPreferredSize(buttonSize);
        drinkButton.setPreferredSize(buttonSize);

        // Add components to the control panel
        controlPanel.add(temperatureLabel);
        controlPanel.add(temperatureField);
        controlPanel.add(volumeLabel);
        controlPanel.add(volumeField);
        controlPanel.add(heatButton);
        controlPanel.add(coolButton);
        controlPanel.add(fillButton);
        controlPanel.add(drinkButton);

        // Display label for the flask
        flaskDisplayLabel = new JLabel("", SwingConstants.CENTER);
        flaskDisplayLabel.setFont(new Font("Monospaced", Font.PLAIN, 16)); // Bigger font for ASCII art
        flaskDisplayLabel.setPreferredSize(new Dimension(600, 250));

        // Add action listeners
        heatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(temperatureField.getText());
                flask.heat(temp);
                updateDisplay();
            }
        });

        coolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(temperatureField.getText());
                flask.cool(temp);
                updateDisplay();
            }
        });

        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double volume = Double.parseDouble(volumeField.getText());
                flask.fill(volume);
                updateDisplay();
            }
        });

        drinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double volume = Double.parseDouble(volumeField.getText());
                flask.drink(volume);
                updateDisplay();
            }
        });

        // Add components to the frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(flaskDisplayLabel, BorderLayout.CENTER);

        updateDisplay();
        frame.setVisible(true);
    }

    private void updateDisplay() {
        flaskDisplayLabel.setText("<html>" + flask.getFlaskDisplay() + "</html>");

        // Check thresholds and update buttons visibility
        if (flask.getCurrentVolume() >= flask.getCapacity()) {
            fillButton.setVisible(false); // Hide fill button if volume is 500 ml or more
        } else {
            fillButton.setVisible(true); // Show fill button if volume is less than 500 ml
        }

        if (flask.getCurrentVolume() <= 0) {
            drinkButton.setVisible(false); // Hide drink button if volume is 0 ml
        } else {
            drinkButton.setVisible(true); // Show drink button if volume is more than 0 ml
        }
    }

    public static void main(String[] args) {
        new WaterFlaskGUI();
    }

    private class WaterFlask {
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
        }

        // Method to cool the flask
        public void cool(double degrees) {
            temperature -= degrees;
        }

        // Method to fill the flask
        public void fill(double volume) {
            if (currentVolume + volume <= capacity) {
                currentVolume += volume;
            } else {
                currentVolume = capacity;
            }
        }

        // Method to drink from the flask
        public void drink(double volume) {
            if (currentVolume - volume >= 0) {
                currentVolume -= volume;
            } else {
                currentVolume = 0;
            }
        }

        // Method to get flask display based on temperature and volume
        public String getFlaskDisplay() {
            StringBuilder display = new StringBuilder();
            display.append("Current Temperature: ").append(temperature).append("°C<br>");
            display.append("Current Volume: ").append(currentVolume).append(" ml / ").append(capacity).append(" ml<br>");
            if (temperature < 0) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   :::&nbsp; |<br>");
                display.append("  |  ICE&nbsp;  |<br>");
                display.append("  | :::&nbsp; |<br>");
                display.append("   |______|<br>");
            } else if (temperature >= 0 && temperature <= 20) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ....   |<br>");
                display.append("  |  COOL   |<br>");
                display.append("  | ....   |<br>");
                display.append("   |______|<br>");
            } else if (temperature > 20 && temperature <= 25) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ....   |<br>");
                display.append("  |  ROOM   |<br>");
                display.append("  | TEMP    |<br>");
                display.append("   |______|<br>");
            } else if (temperature > 25 && temperature <= 40) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ,,,,   |<br>");
                display.append("  |  WARM   |<br>");
                display.append("  | ,,,,    |<br>");
                display.append("   |______|<br>");
            } else {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ~~~~   |<br>");
                display.append("  |  HOT!   |<br>");
                display.append("  | ~~~~    |<br>");
                display.append("   |______|<br>");
            }
            return display.toString();
        }

        public double getCurrentVolume() {
            return currentVolume;
        }

        public double getCapacity() {
            return capacity;
        }
    }
}
