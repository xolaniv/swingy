package za.wtc.swingy.controller;

import za.wtc.swingy.model.hero.Hero;
import za.wtc.swingy.model.hero.Crab;
import za.wtc.swingy.model.hero.Octopus;
import za.wtc.swingy.model.hero.HeroEnum;
import za.wtc.swingy.model.hero.Dragon;
import za.wtc.swingy.model.hero.BigFoot;
import za.wtc.swingy.model.hero.Cuttlefish;


public abstract class HeroFactory {

    private static Hero newHero;
    private static Hero newEnemy;

    public static Hero newHero(String name, HeroEnum type) {
        switch (type) {
            case CRAB:
                newHero = new Crab(name);
                break;
            case CUTTLEFISH:
                newHero = new Cuttlefish(name);
                break;
            case OCTOPUS:
                newHero = new Octopus(name);
                break;
            default:
                break;
        }
        return newHero;
    }


    public static Hero newEnemy(Hero hero, HeroEnum type) {
        switch (type) {
            case DRAGON:
                newEnemy = new Dragon(hero.getLevel());
                break;
            case BIGFOOT:
                newEnemy = new BigFoot(hero.getLevel());
                break;
            default:
                break;
        }
        return newEnemy;
    }
}