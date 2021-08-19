package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Dagger extends Weapon {
    public Dagger(FoodMaterial material) {
        super("Dagger", "dagger", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] hiltCoords = genGrip(this.bufferedImage,4,4,getMaterial().getColorMap());

        int[] guardCoords = genGuard(hiltCoords,this.bufferedImage,1,3,getMaterial().getColorMap());

        genBlade(guardCoords, this.bufferedImage,4,6,1,getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
