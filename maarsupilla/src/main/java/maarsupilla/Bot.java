package maarsupilla;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    private static final String TOKEN = System.getenv("TOKEN");
    public static final String CMD_PREFIX = "mp!";

    public static void main(String[] args) {

        JDA maarsupilla = new Main();

    }

    private JDA prepareJDA() {
        try {
            JDA bot = JDABuilder.createDefault(TOKEN)
                    .setActivity(Activity.playing("Dead by Daylight"))
                    .addEventListeners()
                    .build()
                    .awaitReady();
            return bot;
        } catch (InterruptedException e) {
            System.out.println("Await ready failed");
            System.exit(-1);
        }
        return null;
    }
}