package za.wtc.swingy.model.hero;

import lombok.Getter;
import lombok.Setter;

import za.wtc.swingy.view.MapView;
import za.wtc.swingy.model.artifact.Helm;
import za.wtc.swingy.model.artifact.Armor;
import za.wtc.swingy.model.artifact.Weapon;
import za.wtc.swingy.model.artifact.Artifact;
import za.wtc.swingy.model.artifact.ArtifactEnum;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import static za.wtc.swingy.tools.Log.*;
import static za.wtc.swingy.tools.Colors.*;


@Getter
@Setter
public abstract class Hero {

    @NotNull
    @Size(min = 2, max = 30)
    protected String name;

    protected int level;
    protected int attack;
    protected int defense;
    protected String type;
    protected int hitPoints;
    protected int experience;
    protected int xCoordinate;
    protected int yCoordinate;
    protected Armor armor;
    protected Helm helm;
    protected Weapon weapon;
    private MapView observer;

    Hero() {

    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public Helm getHelm() {
        return helm;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Armor getArmor() {
        return armor;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }
    public int getYCoordinate() {
        return this.yCoordinate;
    }
    public void setYCoordinate(int coordinate) {
        this.yCoordinate = coordinate;
    }
    public void setXCoordinate(int coordinate) {
        this.xCoordinate = coordinate;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }
    public int getHitPoints() {
        return this.hitPoints;
    }

    public String getType() {
        return this.type;
    }


    Hero(String name) {
        this.name = name;
        this.level = 1;
        this.experience = 0;

        Armor defaultArmor = new  Armor("Default Armor", 1);
        Helm defaultHelm = new Helm("Default Helm", 1);
        Weapon defaultWeapon = new Weapon("Default Weapon", 1);

        equipHero(defaultArmor, ArtifactEnum.ARMOR);
        equipHero(defaultHelm, ArtifactEnum.HELM);
        equipHero(defaultWeapon, ArtifactEnum.WEAPON);
    }

	public void register(MapView map) {
       observer = map; 
    }
    
    /**
     * After updating the hero's position,

     */
    private void updateMap() {
        observer.updateHeroPosition();
    }

    /**
     * Set the x and the y coordinates
     */
    public void setPosition(int x, int y) {
        this.xCoordinate += x;
        this.yCoordinate += y;
        updateMap();
    }

    public void attack(Hero enemy) {
        int earnedExperience = 0;

        enemy.defend(this.attack);
        if (enemy.getHitPoints() <= 0) {
            if (enemy.getType().equals("Dragon")) {
                earnedExperience = (int) (Math.ceil((float)this.level / 2) * 750);
                this.experience += earnedExperience;
            } else if (enemy.getType().equals("BigFoot")) {
                earnedExperience = (int) (Math.ceil((float)this.level / 2) * 500);
                this.experience += earnedExperience;
            }
            log(ANSI_CYAN + " >>> Well Done, Your Earned " + earnedExperience + "XP" + ANSI_RESET);
            if (this.experience >= (this.level * 1000 + Math.pow(this.level - 1, 2) * 450)) {
                levelUp();
            }
        }
    }


    public void defend(int enemyDamage) {
        int damage = enemyDamage - this.defense;

        if (damage <= 0) {
            damage = 1;
        }
        if (damage > 0) {
            this.hitPoints -= damage;
        }
    }

    /**
     * Level up the hero.
     */
    private void levelUp() {
        int levelUp;
 
        this.level++;
        levelUp = this.level;
        this.attack += levelUp;
        this.hitPoints += levelUp;
        this.defense += 1;
    }

	public void equipHero(Artifact artifact, ArtifactEnum type) {
        switch (type) {
            case ARMOR:
                if (armor != null) {
                    defense -= armor.getDefense(); 
                } else {
                    armor = (Armor)artifact;
                    defense += armor.getDefense();
                }
                break;
            case HELM:
                if (helm != null) {
                    hitPoints -= helm.getHitPoints();
                } else {
                    helm = (Helm)artifact;
                    hitPoints += helm.getHitPoints();
                }
                break;
            case WEAPON:
                if (weapon != null) {
                    attack -= weapon.getAttack();
                } else {
                    weapon = (Weapon)artifact;
                    attack += weapon.getAttack();
                }
                break;
        }
    }
}
