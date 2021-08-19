package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rapier extends Weapon {
    public Rapier(FoodMaterial material) {
        super("Rapier", "rapier", material);
    }

    @Override
    public boolean genImage() throws IOException {

        int[] gripCoords = genGrip(bufferedImage,5,5,this.getMaterial().getColorMap());

        int[] guardLoc = genGuard(gripCoords,bufferedImage,4,4,this.getMaterial().getColorMap());

        genBlade(guardLoc, bufferedImage,12,12,0,this.getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
