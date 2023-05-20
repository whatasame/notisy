package com.github.whatasame.syncnotiontistory.key;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KeyManager {

    public static final String KEY_FILE = "key.json";
    private Map<Key, String> keyData;

    public KeyManager() {
        loadKeys();
    }

    private void loadKeys() {
        try (Reader reader = new FileReader(KEY_FILE)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            /* Json 데이터 유효성 검증 */
            boolean isValid = true;
            this.keyData = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                try {
                    Key key = Key.valueOf(entry.getKey());
                    String value = entry.getValue().getAsString();
                    keyData.put(key, value);
                } catch (IllegalArgumentException e) {
                    isValid = false;
                    e.printStackTrace();
                }
            }

            if (!isValid) { // 유효하지 않은 키 초기화
                for (Key key : Key.values()) {
                    if (!keyData.containsKey(key)) {
                        keyData.put(key, "");
                    }
                }
                saveKeys();
            }

        } catch (FileNotFoundException e) {
            this.keyData = getDefaultKeyData();
            saveKeys();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveKeys() {
        try (Writer writer = new FileWriter(KEY_FILE)) {
            Gson gson = new Gson();
            gson.toJson(this.keyData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readKey(Key key) {
        return keyData.get(key);
    }

    public void updateKey(Key key, String keyValue) {
        this.keyData.put(key, keyValue);
        saveKeys();
    }

    /**
     * @return Key enum들을 Key로, 빈 문자열을 Value로 갖는 Map 생성
     */
    private Map<Key, String> getDefaultKeyData() {
        Map<Key, String> tmp = new HashMap<>();
        for (Key key : Key.values()) {
            tmp.put(key, "");
        }
        return tmp;
    }
}
