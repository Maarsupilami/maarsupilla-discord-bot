package maarsupilla.listener;

import maarsupilla.Bot;
import maarsupilla.controller.APIController;
import maarsupilla.model.Currency;
import maarsupilla.model.dto.Shrine;
import maarsupilla.model.dto.ShrinePerk;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        System.out.println(event.getMessage().getContentRaw());
        if (event.getMessage().getContentRaw().startsWith(Bot.CMD_PREFIX)) {
            answerQuestion(event);
        }
    }

    private static void answerQuestion(MessageReceivedEvent event) {
        if (isShrine(event)) {
            Shrine shrine = getShrine();
            List<ShrinePerk> perks = shrine.getShrinePerks();
            StringBuilder sb = new StringBuilder("```\n");
            for (ShrinePerk perk : perks) {
                createResponse(sb, perk);
            }
            sb.append("```");
            TextChannel textChannel = event.getGuild().getTextChannelsByName(event.getChannel().getName(),true).get(0);
            textChannel.sendMessage(sb.toString()).queue();
        } else {
            User user = event.getAuthor();
            String text = event.getMessage().getContentRaw();
            String channelMention = event.getChannel().getAsMention();
            String message =
                    user.getAsMention() + " wrote something in the " + channelMention + " channel!";
            TextChannel textChannel = event.getGuild().getTextChannelsByName(event.getChannel().getName(), true).get(0);
            textChannel.sendMessage(message).queue();

        }
    }

    private static Shrine getShrine() {
        Shrine shrine;
        try {
            shrine = APIController.setupShrine("https://dbd.tricky.lol/api/shrine");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shrine;
    }

    private static boolean isShrine(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw().replace(Bot.CMD_PREFIX, "").trim().equalsIgnoreCase("shrine");
    }

    private static void createResponse(StringBuilder sb, ShrinePerk perk) {
        sb.append(perk.getPerkName()).append("\n");
        sb.append("\t").append(Currency.BLOODPOINTS.getName()).append(": ").append(perk.getBloodPoints()).append("\n");
        sb.append("\t").append(Currency.SHARDS.getName()).append(": ").append(perk.getShards()).append("\n");
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(Objects.requireNonNull(event.getUser()).isBot()) return;

        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpUrl = event.getJumpUrl();

        String message =
                user.getAsMention() + " reacted to a message with " + emoji + " in the " + channelMention + " channel!";

        TextChannel textChannel = Objects.requireNonNull(event.getGuild().getDefaultChannel()).asTextChannel();
        textChannel.sendMessage(message).queue();
    }
}
