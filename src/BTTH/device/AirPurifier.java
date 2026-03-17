package BTTH.device;

public class AirPurifier extends AbstractDevice {

    public AirPurifier(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return name + " - " + (on ? "Đang bật" : "Đang tắt") + ", chế độ lọc tiêu chuẩn";
    }
}
