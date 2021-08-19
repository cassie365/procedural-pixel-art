package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.Shader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Staff extends Weapon {
    public Staff(FoodMaterial material) {
        super("Staff", "staff", material);
    }

    @Override
    public boolean genImage() throws IOException {
        genGrip(bufferedImage,12,14,this.getMaterial().getColorMap());

        Shader.colorRamp(this.bufferedImage);

        File file = new File(getImagePath());

        ImageIO.write(this.bufferedImage, "png", file);
        return true;
    }
}
