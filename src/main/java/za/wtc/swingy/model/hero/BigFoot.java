package za.wtc.swingy.model.hero;


public class BigFoot extends Enemy {

    public BigFoot(int level) {
        super(level);
        this.name = "BigFoot";
        this.type = "BigFoot";
        this.attack = level + 5;
        this.defense = level + 2;
        this.hitPoints = level + 12;
    }
}