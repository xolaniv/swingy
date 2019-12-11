package za.wtc.swingy.model.hero;


public class Dragon extends Enemy {

    public Dragon(int level) {
        super(level);
        this.name = "Big Belly Dragon";
        this.type = "Dragon";
        this.attack = level + 6;
        this.defense = level + 2;
        this.hitPoints = level + 15; 
    }
}