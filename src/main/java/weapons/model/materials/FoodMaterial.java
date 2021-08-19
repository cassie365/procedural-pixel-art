package weapons.model.materials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents different types of Edible food materials and their properties
 */
public enum FoodMaterial {
    WHEAT("Wheat","wheat","forge:crops/wheat"),
    POTATO("Potato","potato","forge:crops/potato"),
    CARROT("Carrot","carrot","forge:crops/carrot"),
    BEETROOT("Beetroot","beetroot","forge:crops/beetroot");

    // Replace below with directory for material colors. Do not include end slash.
    private static final String DIRECTORY = "material_colors";

    private final String displayName;
    private final String name;
    private final String forgeTag;
    private final Map<String, Color> colorMap;

    private FoodMaterial(String displayName, String name, String forgeTag){
        this.displayName = displayName;
        this.name = name;
        this.forgeTag = forgeTag;
        this.colorMap = importColors();
    }

    /**
     * Get the user friendly name
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get the identifier used for this material
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the forge tag associated with this material
     * In otherwords, what item makes up this material?
     *
     * i.e. forge:ingots/iron = iron
     * @return
     */
    public String getForgeTag() {
        return forgeTag;
    }

    /**
     * Return the color map for this material
     * @return
     */
    public Map<String, Color> getColorMap() {
        return colorMap;
    }

    /**
     * Use this method to load material colors.
     * @return the color map which will be assigned in the constructor
     */
    private Map<String, Color> importColors(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Reader reader = Files.newBufferedReader(Paths.get(DIRECTORY+"/"+name+".json"));

            Map<String, String> map = gson.fromJson(reader, Map.class);
            Map<String,Color> output = new HashMap<>();

            for (Map.Entry<String, String> entry: map.entrySet())
            {
                output.put(entry.getKey(),Color.decode(entry.getValue()));
            }

            reader.close();

            return output;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
