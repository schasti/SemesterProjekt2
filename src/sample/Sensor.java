package sample;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Sensor {
    private SerialPort sensor = new SerialPort("COM3");

    public Sensor() {
        //JSSC biblotek kommer fra  https://github.com/java-native/jssc/releases
        try {
            sensor.openPort();
            sensor.setParams(115200, 8, 1, 0);
            sensor.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            sensor.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);
        } catch (SerialPortException ex) {
            System.out.println("FEJL SERIALPORTEXCEPTION");
        }
    }

    public String maaling() {

        try {
            if (sensor.getInputBufferBytesCount() > 0) {
                return sensor.readString();
            } else {
                return null;
            }
        } catch (SerialPortException ex) {
            System.out.println("fejl: " + ex);
        }
        return null;
    }
}
