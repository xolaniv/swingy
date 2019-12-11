package za.wtc.swingy.model.artifact;

import lombok.Getter;

@Getter
public class Helm extends Artifact {

    private int hitPoints;

    public int getHitPoints() {
        return hitPoints;
    }
    public Helm(String name, int hitPoints) {
        super(name);
        this.type = ArtifactEnum.HELM;
        this.hitPoints = hitPoints;
    }
}