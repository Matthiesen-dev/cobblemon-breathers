package dev.matthiesen.common.cobblemon_breathers.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class ModConfig {
    @SerializedName("key")
    public String key = "value";

    public static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
}
