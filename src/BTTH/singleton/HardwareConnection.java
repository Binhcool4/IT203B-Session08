package BTTH.singleton;

public class HardwareConnection {

    private static final HardwareConnection INSTANCE = new HardwareConnection();

    private HardwareConnection() {
        System.out.println("[Singleton] Đã khởi tạo kết nối phần cứng duy nhất.");
    }

    public static HardwareConnection getInstance() {
        return INSTANCE;
    }

    public void sendCommand(String deviceName, String action) {
        System.out.println("[Hardware] " + deviceName + " -> " + action);
    }
}
