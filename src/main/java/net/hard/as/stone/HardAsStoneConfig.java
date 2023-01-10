package net.hard.as.stone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HardAsStoneConfig {

    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create(); // got a bunch of cool config options

    public float woodToolMiningSpeed = 2f;
    public float stoneToolMiningSpeed = 3f;
    public float ironToolMiningSpeed = 4f;
    public float goldToolMiningSpeed = 9f;
    public float diamondToolMiningSpeed = 5.5f;
    public float netheriteToolMiningSpeed = 7f;


    public static HardAsStoneConfig readConfig() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("hard_as_stone.json");
        HardAsStoneConfig config = null;
        if (Files.exists(path)) {
            System.out.println("The config file exists...");
            // The config file exists, so we can read it
            try {
                // Read the file into a byte array
                byte[] data = Files.readAllBytes(path);

                // Deserialize the byte array into an object
                Gson gson = new Gson();
                config = gson.fromJson(new String(data), HardAsStoneConfig.class);
            } catch (IOException e) {
                // Handle the exception
            }
        } else {
            // The config file does not exist, so we need to create it
            System.out.println("The config file does NOT exist...");

            try {
                // Create the file
                Files.createFile(path);

                // Create a new config object with default values
                config = new HardAsStoneConfig();

                // Serialize the config object to a JSON string
                String json = new Gson().toJson(config);

                // Write the JSON string to the file
                Files.writeString(path, json);
            } catch (IOException e) {
                // Handle the exception
            }
        }
        return config;
    }


    public void writeConfig() {
        Path configFile = FabricLoader.getInstance().getConfigDir().resolve("hard_as_stone.json");
        try {
            Files.writeString(configFile, GSON.toJson(this));
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
