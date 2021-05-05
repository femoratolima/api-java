package br.com.elotech.api.util;

import java.util.UUID;

public interface IdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

}
