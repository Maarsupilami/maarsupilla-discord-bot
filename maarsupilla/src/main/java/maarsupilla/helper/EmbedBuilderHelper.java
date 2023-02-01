package maarsupilla.helper;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

/**
 * In progress.
 */
public class EmbedBuilderHelper extends EmbedBuilder {

    public EmbedBuilderHelper(Color color, String title, String description) {
        setColor(color);
        setTitle(title);
        setDescription(description);
    }

    public void newField(final String name, final String value, final boolean inline) {
        addField(name, value, inline);
    }

    public void newBlankField(boolean inline) {
        addBlankField(inline);
    }

}
