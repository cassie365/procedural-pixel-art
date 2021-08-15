package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rapier extends Weapon {
    public Rapier(FoodMaterial material) {
        super("Rapier", "rapier", material);
    }

    @Override
    public boolean genImage() throws IOException {

        int[] hiltCoords = genHilt(bufferedImage,5,5,this.getMaterial().getColorMap());

        int guardLoc = genGuard(hiltCoords,bufferedImage,4,4,this.getMaterial().getColorMap());

        genBlade(guardLoc, bufferedImage,12,12,0,this.getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
