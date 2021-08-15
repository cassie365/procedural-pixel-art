package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mace extends Weapon{
    public Mace(FoodMaterial material) {
        super("Mace", "mace", material);
    }

    @Override
    public boolean genImage() throws IOException {

        int[] hiltCoords = genHilt(bufferedImage,8,10,this.getMaterial().getColorMap());

        genMaceHead(hiltCoords[0], bufferedImage,this.getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
