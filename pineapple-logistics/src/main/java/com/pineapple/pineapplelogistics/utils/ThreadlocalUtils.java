package com.pineapple.pineapplelogistics.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 20:22 2023/4/23
 * @Modifier by:
 */
public class ThreadlocalUtils{

    private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    public static Object get(String key) {
        return getContextMap().get(key);
    }

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static Object removeKey(String key) {
        return getContextMap().remove(key);
    }


    public static void remove() {
        getContextMap().clear();
    }

    public static void clear() {
        THREAD_CONTEXT.remove();
    }
    private static Map<String, Object> getContextMap() {
        return THREAD_CONTEXT.get();
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(8) {

                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }

}
