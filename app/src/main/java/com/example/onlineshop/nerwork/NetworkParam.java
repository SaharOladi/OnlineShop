package com.example.onlineshop.nerwork;

import java.util.HashMap;
import java.util.Map;

public class NetworkParam {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_25ab27ab6ca4b8e10d375708ed1c92815d25223f";
    public static final String CONSUMER_SECRET = "cs_7060364df3f4e5c3fab11ce7c545c0357d7baf8b";

    public static final String USER_NAME = "sahar.olady@gmail.com";
    public static final String PASSWORD = "xV6T5dGJ3bBa";

    public static final Map<String, String> BASE = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);

    }};


}
