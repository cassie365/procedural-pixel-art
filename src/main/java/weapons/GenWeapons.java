package weapons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import weapons.model.materials.FoodMaterial;
import weapons.model.weapons.*;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenWeapons {
    private static Map<String,String> locale = new HashMap<>();
    private static final String modID = "edibleweapons";

    public static void main(String[] args) throws IOException {
        generateWeapons();
    }

    public static void generateWeapons() throws IOException {
        List<Weapon> weapons = new ArrayList<>();
        for(FoodMaterial f:FoodMaterial.values()){
            weapons.add(new Dagger(f));
            weapons.add(new LongSword(f));
            weapons.add(new Mace(f));
            weapons.add(new Morningstar(f));
            weapons.add(new Rapier(f));
            weapons.add(new Spear(f));
            weapons.add(new Staff(f));
        }

        for(Weapon w: weapons){
            System.out.println(w.genImage());
            locale.put("item."+modID+"."+w.getRegName(),w.getLocaleName());
        }

        generateLocale();
    }

    private static void generateLocale(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a writer
            Writer writer = new FileWriter("lang/en_us.json");

            // convert map to JSON File
            gson.toJson(locale, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
