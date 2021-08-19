package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LongSword extends Weapon{

    public LongSword(FoodMaterial material) {
        super("Long Sword", "long_sword", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genGrip(this.bufferedImage,5,6,getMaterial().getColorMap());

        int[] guardCoords = Weapon.genGuard(hiltCoords,this.bufferedImage,3,3,getMaterial().getColorMap());

        genBlade(guardCoords, this.bufferedImage,8,10,1,getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);

        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);

        return true;
    }
}
