import java.util.Scanner;

public class Main {
    public Controller controller = new Controller();
    Scanner sc = new Scanner(System.in);

    public void run() {
        controller.setThread(1);
        controller.openChrome();
        controller.submit();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
