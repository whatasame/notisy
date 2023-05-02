package com.github.whatasame.syncnotiontistory.key;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class KeyManagerTest {

    KeyManager keyManager;

    @BeforeEach
    public void beforeEach() {
        keyManager = new KeyManager();
    }

    @Test
    public void 키_파일이_없을때_파일_읽기() {
        /* 기존 파일 삭제 */
        File file = new File(KeyManager.KEY_FILE_PATH);
        if (file.exists()) {
            file.renameTo(new File("_" + KeyManager.KEY_FILE_PATH)); // 이름 앞에 _를 붙여 삭제한 것처럼 동작
        }

        assertNotNull(keyManager.getKey(Key.TISTORY_ACCESS_TOKEN.getName()));
    }

    @Test
    public void 티스토리_API_키_읽기() {
        String tistoryAccessToken = keyManager.getKey(Key.TISTORY_ACCESS_TOKEN.getName());

        assertNotNull(tistoryAccessToken);
    }

    @Test
    public void 키_업데이트() {
        keyManager.setKey(Key.TISTORY_ACCESS_TOKEN.getName(), "1234");

        assertEquals("1234", keyManager.getKey(Key.TISTORY_ACCESS_TOKEN.getName()));
    }

    @Test
    public void 키_파일_저장() {
        /* 기존 파일 삭제 */
        File file = new File(KeyManager.KEY_FILE_PATH);
        if (file.exists()) {
            file.renameTo(new File("_" + KeyManager.KEY_FILE_PATH)); // 이름 앞에 _를 붙여 삭제한 것처럼 동작
        }

        keyManager.saveKeyFile();

        assertTrue(new File("key.json").exists());
    }

    @AfterEach
    public void 키_파일_이름_복구() {
        File file = new File("_" + KeyManager.KEY_FILE_PATH);
        if (file.exists()) {
            file.renameTo(new File(KeyManager.KEY_FILE_PATH));
        }
    }
}