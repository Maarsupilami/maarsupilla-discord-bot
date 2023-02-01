package maarsupilla.model.dto;

import com.google.gson.annotations.SerializedName;

public class ShrinePerk {
    @SerializedName(value="perkName", alternate = "id")
    private String perkName;
    @SerializedName(value="bloodPoints", alternate = "bloodpoints")
    private int bloodPoints;
    @SerializedName(value="shardPoints", alternate = "shards")
    private int shardPoints;

    public ShrinePerk(String perkName, int bloodPoints, int shards) {
        this.perkName = perkName;
        this.bloodPoints = bloodPoints;
        this.shardPoints = shards;
    }

    public String getPerkName() {
        return perkName;
    }

    public void setPerkName(String perkName) {
        this.perkName = perkName;
    }

    public int getBloodPoints() {
        return bloodPoints;
    }

    public void setBloodPoints(int bloodPoints) {
        this.bloodPoints = bloodPoints;
    }

    public int getShards() {
        return shardPoints;
    }

    public void setShards(int shards) {
        this.shardPoints = shards;
    }

    @Override
    public String toString() {
        return "Perk{" +
                "perkName='" + perkName + '\'' +
                ", bloodPoints=" + bloodPoints +
                ", shards=" + shardPoints +
                '}';
    }
}
