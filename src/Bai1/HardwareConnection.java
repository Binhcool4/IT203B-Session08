package Bai1;
public class HardwareConnection {
    private static HardwareConnection instance;
    // constructor private de tranh tao object tu ben ngoai
    private HardwareConnection() {
        System.out.println("HardwareConnection: Đã kết nối phần cứng.");
    }
    public static HardwareConnection getInstance() {if (instance == null) {
            instance = new HardwareConnection();
        }
        return instance;
    }
    public void connect() {
        // chi mo phong
    }
    public void disconnect() {
        System.out.println("Ngắt kết nối phần cứng.");
    }
}