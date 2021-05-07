package lib;

public class Utils {

    public static void sleep(int sleepTimeInSeconds) {
        try {
            Thread.sleep(sleepTimeInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
