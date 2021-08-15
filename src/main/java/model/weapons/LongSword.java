package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LongSword extends Weapon{

    public LongSword(FoodMaterial material) {
        super("Long Sword", "long_sword", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genHilt(this.bufferedImage,5,6,getMaterial().getColorMap());

        int guardLoc = Weapon.genGuard(hiltCoords,this.bufferedImage,3,3,getMaterial().getColorMap());

        genBlade(guardLoc, this.bufferedImage,8,10,1,getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);

        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);

        return true;
    }
}
