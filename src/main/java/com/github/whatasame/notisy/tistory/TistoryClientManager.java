package com.github.whatasame.notisy.tistory;

import com.github.whatasame.notisy.key.Key;
import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.tistory.api.TistoryClient;

public class TistoryClientManager {

    public static TistoryClient getClient() {
        String token = new KeyManager().readKey(Key.TISTORY_ACCESS_TOKEN);

        return new TistoryClient(token);
    }

}
