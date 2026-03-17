package Bai5;
public class Fan implements Observer {
    public void lowSpeed() {
        System.out.println("Quạt: Chạy tốc độ thấp");
    }
    public void highSpeed() {
        System.out.println("Quạt: Chạy tốc độ mạnh");
    }
    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }
}