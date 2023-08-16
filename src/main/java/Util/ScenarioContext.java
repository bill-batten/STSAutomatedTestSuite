package Util;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static Map<String, String> contextMap = new HashMap<>();

    public static void setContext(String key, String value) {
        contextMap.put(key, value);
    }

    public static String getContext(String key) {
        return contextMap.get(key);
    }

    public static void removeContext(String key) {
        contextMap.remove(key);
    }

    public static void clearContext(){
        contextMap.clear();
    }
}
