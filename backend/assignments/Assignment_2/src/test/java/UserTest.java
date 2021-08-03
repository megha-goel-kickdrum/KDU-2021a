import com.kdu.StartApp;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserTest {

    public static void main(String[] args)
    {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(2);

            StartApp user = new StartApp();

            Runnable user1 = new Runnable() {
                @Override
                public void run() {

                    try {
                        user.appQuery("japan");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };

            Runnable user2 = new Runnable() {
                @Override
                public void run() {
                    try {
                          user.appQuery("spain");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.submit(user1);
            executorService.submit(user2);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (executorService != null)
                executorService.shutdown();
        }
    }
}
