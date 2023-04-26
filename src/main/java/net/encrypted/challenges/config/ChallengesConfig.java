package net.encrypted.challenges.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.encrypted.challenges.ChallengesMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ChallengesConfig {
    public SpawnConfig SpawnSettings = new SpawnConfig();
    public ArrayList<String> ChallengeDimensions = new ArrayList<>(List.of(
            "minecraft:overworld",
            "minecraft:the_nether"
    ));

    public boolean ReadFromFile() {
        Path path = FabricLoader.getInstance().getConfigDir();
        var file = path.resolve("challenges.json").toFile();
        if (file.exists()) {
            var gson = new Gson();
            try {
                var temp = gson.fromJson(new FileReader(file), this.getClass());
                SpawnSettings = temp.SpawnSettings;
                ChallengeDimensions = temp.ChallengeDimensions;
            } catch (FileNotFoundException e) {
                ChallengesMod.LOGGER.error("Failed to read a config file.");
                e.printStackTrace();
                return false;
            }
        } else
            return false;

        return true;
    }

    public void SaveToFile() {
        Path path = FabricLoader.getInstance().getConfigDir();
        var gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            var directory = path.toFile();
            if (!directory.exists())
                if (!directory.mkdirs()) throw new Exception("Unable to create directory to store config files.");
            try (PrintWriter writer = new PrintWriter(directory.getPath() + "/challenges.json")) {
                writer.println(gson.toJson(this));
            }
        } catch (Exception e) {
            ChallengesMod.LOGGER.error("Failed to save a config file.");
            e.printStackTrace();
        }
    }
}


