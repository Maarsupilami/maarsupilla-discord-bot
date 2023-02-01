package maarsupilla.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Shrine {
    @SerializedName(value = "shrinePerks", alternate = "perks")
    private List<ShrinePerk> shrinePerks;

    public List<String> getPerkNames() {
        List<String> perkNames = new ArrayList<>();
        for (ShrinePerk shrinePerk : shrinePerks) {
            perkNames.add(shrinePerk.getPerkName());
        }
        return perkNames;
    }

    public List<ShrinePerk> getShrinePerks() {
        return shrinePerks;
    }

    public int getSize() {
        return shrinePerks.size();
    }

    @Override
    public String toString() {
        return "Shrine{" +
                "shrinePerks=" + shrinePerks +
                '}';
    }
}
