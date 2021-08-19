package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mace extends Weapon{
    public Mace(FoodMaterial material) {
        super("Mace", "mace", material);
    }

    @Override
    public boolean genImage() throws IOException {

        int[] gripCoords = genGrip(bufferedImage,8,10,this.getMaterial().getColorMap());

        genMaceHead(gripCoords, bufferedImage,this.getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);
        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
