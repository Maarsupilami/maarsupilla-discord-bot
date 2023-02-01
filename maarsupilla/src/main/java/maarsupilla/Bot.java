package maarsupilla;

import io.github.cdimascio.dotenv.Dotenv;
import maarsupilla.controller.APIController;
import maarsupilla.listener.MessageListener;
import maarsupilla.model.dto.Shrine;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.IOException;

/**
 * JDA-5.0.0 Discord Bot
 * This is the main class where we initialize the bot.
 *
 * @author maarsupilami
 */
public class Bot {

    private final Dotenv config;
    public static final String CMD_PREFIX = "mp!";
    private final ShardManager shardManager;

    public static void main(String[] args) throws IOException {

        Bot maarsupilla;
        try {
            maarsupilla = new Bot();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided token is invalid!");
        }

    }

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */
    public Bot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        shardManager = DefaultShardManagerBuilder.createDefault(
                                        token,
                                        GatewayIntent.GUILD_MEMBERS,
                                        GatewayIntent.GUILD_MESSAGES,
                                        GatewayIntent.GUILD_VOICE_STATES,
                                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                                        GatewayIntent.SCHEDULED_EVENTS,
                                        GatewayIntent.MESSAGE_CONTENT,
                                        GatewayIntent.DIRECT_MESSAGES,
                                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                                        GatewayIntent.GUILD_MESSAGE_TYPING
                                    )
                                    .addEventListeners(
                                            new MessageListener()
                                    )
                                    .setStatus(OnlineStatus.ONLINE)
                                    .setActivity(Activity.playing("Dead by Daylight"))
                                    .build();
    }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot.
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * Retrieves the bot environment variables.
     * @return the Dotenv instance for the bot.
     */
    public Dotenv getConfig() {
        return config;
    }
}