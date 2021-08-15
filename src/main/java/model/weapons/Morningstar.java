package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Morningstar extends Weapon {
    public Morningstar(FoodMaterial material) {
        super("Morningstar", "morningstar", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genHilt(bufferedImage,8,10,this.getMaterial().getColorMap());

        genSpike(hiltCoords[0], bufferedImage,this.getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
