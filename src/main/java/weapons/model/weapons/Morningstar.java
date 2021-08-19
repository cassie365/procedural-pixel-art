package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Morningstar extends Weapon {
    public Morningstar(FoodMaterial material) {
        super("Morningstar", "morningstar", material);
    }

    @Override
    public boolean genImage() throws IOException {
        int[] gripCoords = genGrip(bufferedImage,8,10,this.getMaterial().getColorMap());

        genSpike(gripCoords, bufferedImage,this.getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
