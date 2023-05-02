package com.github.whatasame.syncnotiontistory.key;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KeyManager {

    private final File keyFile;
    public static final String KEY_FILE_PATH = "key.json";
    private static Map<String, String> keyMap;

    public KeyManager() {
        this.keyFile = new File(KEY_FILE_PATH);
        loadKeyFile();
    }

    private void loadKeyFile() {
        if (!keyFile.exists()) {
            setDefaultKeyMap();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(this.keyFile))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            keyMap = new Gson().fromJson(sb.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveKeyFile() {
        try (FileWriter writer = new FileWriter(this.keyFile)) {
            writer.write(new Gson().toJson(keyMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getKey(String key) {
        return keyMap.get(key);
    }

    public void setKey(String key, String value) {
        keyMap.put(key, value);
    }

    private void setDefaultKeyMap() {
        keyMap = new HashMap<>();
        for (Key KEY : Key.values()) {
            keyMap.put(KEY.getName(), "");
        }
    }

}
