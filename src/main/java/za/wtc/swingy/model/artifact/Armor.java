package za.wtc.swingy.model.artifact;

import lombok.Getter;


@Getter
public class Armor extends Artifact {
    
    private int defense;

    public int getDefense() {
        return defense;
    }
    public Armor(String name, int defense) {
        super(name);
        this.type = ArtifactEnum.ARMOR;
        this.defense = defense; 
    }
}