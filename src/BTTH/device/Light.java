package BTTH.device;

public class Light extends AbstractDevice {

    public Light(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return name + " - " + (on ? "Đang bật" : "Đang tắt");
    }
}
