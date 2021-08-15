package model.weapons;

import model.materials.FoodMaterial;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Staff extends Weapon {
    public Staff(FoodMaterial material) {
        super("Staff", "staff", material);
    }

    @Override
    public boolean genImage() throws IOException {
        genHilt(bufferedImage,12,14,this.getMaterial().getColorMap());

        colorRamp(this.bufferedImage,Weapon.WIDTH,Weapon.HEIGHT);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
