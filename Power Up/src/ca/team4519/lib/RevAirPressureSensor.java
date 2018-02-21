package ca.team4519.lib;

import edu.wpi.first.wpilibj.AnalogInput;

public class RevAirPressureSensor {

    private final AnalogInput mAnalogInput;

    public RevAirPressureSensor(int analogInputNumber) {
        mAnalogInput = new AnalogInput(analogInputNumber);
    }

    public double getAirPressurePsi() {
        // taken from the datasheet
        return 250.0 * mAnalogInput.getVoltage() / 5.0 - 25.0;
    }

}