package org.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {
    private final String filePath;
    private final Object data;
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    public JSONHandler(String filePath) {
        this.filePath = filePath;
        this.data = loadJSON();
    }

    private Object loadJSON() {
        try {
            logger.warn("trying to open file for json handler");
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();
            return gson.fromJson(json, Object.class);
        } catch (IOException e) {
            logger.error("Error: File {} not found.", filePath);
            return null;
        } catch (JsonSyntaxException e) {
            logger.error("Error: {} is not a valid JSON file.", filePath);
            return null;
        }
    }

    public String getValue(String key) {
        if (data != null) {
            logger.warn("trying to get data from {} key ",key);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(data).getAsJsonObject();
            if (jsonObject.has(key)) {
                logger.info("got {} data from {} key",data,key);
                return jsonObject.get(key).getAsString();
            } else {
                logger.error("Error: Key '{}' not found in the JSON file.", key);
                return null;
            }
        } else {
            logger.error("Error: data is null");
            return null;
        }
    }

}
