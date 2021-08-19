package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Spear extends Weapon {
    public Spear(FoodMaterial material) {
        super("Spear", "spear", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] gripCoords = genGrip(bufferedImage,12,14,this.getMaterial().getColorMap());

        int[] guardLoc = genGuard(gripCoords,bufferedImage,2,3,this.getMaterial().getColorMap());

        genSpike(guardLoc, bufferedImage,this.getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
