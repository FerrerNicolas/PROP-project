package permanencia.ObjectSaverRecords;


import java.io.Serializable;

public class Tuple implements Serializable{ //Author:Luis

    private String playerName = "";
    private Float value;

    public Tuple (String playerName, Float value) {
        this.playerName = playerName;
        this.value = value;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Float getValue() {
        return value;
    }

}
