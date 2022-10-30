import java.util.ArrayList;
import java.util.List;


public class Controller {
    private List<Profile> profiles = new ArrayList<>();
    private int thread = 0;

    public Controller() {
    }

    public void setThread(int thread) {
        if (thread > 0) this.thread = thread;
        else this.thread = 0;
    }

    public void openChrome() {
        for (int i = 0; i < thread; i++) {
            profiles.add(new Profile());
        }
    }

    public void submit() {
        for (int i = 0; i < thread; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    profiles.get(finalI).submit();
                }
            });
            thread.start();
        }
    }

    public void close() {
        for (int i = 0; i < thread; i++) {
            profiles.get(i).close();
        }
    }
}