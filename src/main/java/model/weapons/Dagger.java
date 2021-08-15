package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Dagger extends Weapon {
    public Dagger(FoodMaterial material) {
        super("Dagger", "dagger", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genHilt(this.bufferedImage,4,4,getMaterial().getColorMap());

        int guardLoc = genGuard(hiltCoords,this.bufferedImage,1,3,getMaterial().getColorMap());

        genBlade(guardLoc, this.bufferedImage,4,6,1,getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
