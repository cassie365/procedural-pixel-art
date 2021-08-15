package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Spear extends Weapon {
    public Spear(FoodMaterial material) {
        super("Spear", "spear", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genHilt(bufferedImage,12,14,this.getMaterial().getColorMap());

        int guardLoc = genGuard(hiltCoords,bufferedImage,2,3,this.getMaterial().getColorMap());

        genSpike(guardLoc, bufferedImage,this.getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
