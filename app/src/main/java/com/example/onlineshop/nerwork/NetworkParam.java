package com.example.onlineshop.nerwork;

import java.util.HashMap;
import java.util.Map;

public class NetworkParam {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String USER_NAME = "ck_f025265e3479f7bee8e93bffe5685517b93ec27d";
    public static final String USER_PASSWORD = "cs_27b19e572ac9cf1333d4d53f7082a15e9fb6a2b0";

    public static final Map<String, String> PRODUCT = new HashMap<String, String>() {{
        put("consumer_key", USER_NAME);
        put("consumer_secret", USER_PASSWORD);
    }};


}
