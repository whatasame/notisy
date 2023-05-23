package com.github.whatasame.notisy.key;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.github.whatasame.notisy.key.Key.NOTION_TOKEN;
import static com.github.whatasame.notisy.key.Key.TISTORY_ACCESS_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * KeyManage를 테스트한다.
 * 테스트를 시작하기 전에 실제 Key 파일은 별도로 백업한다.
 * 테스트에 사용되는 Key 파일은 테스트마다 알맞게 선언하여 사용한다.
 */
class KeyManagerTest {

    private final static Gson gson = new Gson();

    @BeforeAll
    public static void beforeAll() {
        backupKeyFile();
    }

    @AfterAll
    public static void afterAll() {
        recoverKeyFile();
    }

    @Test
    @DisplayName("Key 파일이 있을 때 Key 값 읽기")
    public void _01_test() {
        // given
        Map<Key, String> keys = new HashMap<>();
        for (Key key : Key.values()) {
            keys.put(key, "1234");
        }
        saveDummyKeyFile(gson.toJson(keys));

        // when
        KeyManager keyManager = new KeyManager();

        // then
        for (Key key : Key.values()) {
            assertNotNull(keyManager.readKey(key));
        }
    }

    @Test
    @DisplayName("Key 파일이 있으나 Key 이름이 비정상적인 경우 비어있는 토큰을 생성")
    public void _02_test() {
        // given
        String json = "{\"FAKE_TOKEN\":\"fake token\"}";
        saveDummyKeyFile(json);

        // when
        KeyManager keyManager = new KeyManager();

        // then
        for (Key key : Key.values()) {
            assertEquals(keyManager.readKey(key), "");
        }

    }


    @Test
    @DisplayName("Key 파일이 없을 때 Default keys 생성")
    public void _03_test() {
        // given

        // when
        KeyManager keyManager = new KeyManager();

        // then
        for (Key key : Key.values()) {
            assertEquals(keyManager.readKey(key), "");
        }
    }

    @Test
    @DisplayName("Key 업데이트")
    public void _04_test() {
        // given
        Map<Key, String> keys = new HashMap<>();
        for (Key key : Key.values()) {
            keys.put(key, "1234");
        }
        saveDummyKeyFile(gson.toJson(keys));

        // when
        String newKey = "7777";
        KeyManager keyManager = new KeyManager();
        keyManager.updateKey(TISTORY_ACCESS_TOKEN, newKey);
        keyManager.updateKey(NOTION_TOKEN, newKey);

        // then
        String tistoryToken = keyManager.readKey(TISTORY_ACCESS_TOKEN);
        String notionToken = keyManager.readKey(NOTION_TOKEN);
        assertEquals(tistoryToken, newKey);
        assertEquals(notionToken, newKey);
    }

    /**
     * Key 파일 백업
     */
    public static void backupKeyFile() {
        File original = new File(KeyManager.KEY_FILE);
        File backup = new File("_" + KeyManager.KEY_FILE);
        if (original.exists()) {
            original.renameTo(backup);
        }
    }

    /**
     * Key 파일 복원
     */
    public static void recoverKeyFile() {
        File original = new File(KeyManager.KEY_FILE);
        File backup = new File("_" + KeyManager.KEY_FILE);
        if (backup.exists()) {
            backup.renameTo(original);
        }
    }

    /**
     * 더미 키 파일을 생성
     *
     * @param json JSON string
     */
    private void saveDummyKeyFile(String json) {
        try (FileWriter fileWriter = new FileWriter(KeyManager.KEY_FILE)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}