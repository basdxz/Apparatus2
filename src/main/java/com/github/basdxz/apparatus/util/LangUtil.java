package com.github.basdxz.apparatus.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class LangUtil {
    public static final String DEFAULT_LOCALE = "en_US";
    private static final ThreadLocal<HashMap<String, String>> tempMap = ThreadLocal.withInitial(HashMap::new);

    public static void defaultLocalization(@NonNull Map<String, String> localeMap) {
        localeMap.forEach(LangUtil::defaultLocalization);
    }

    public static void defaultLocalization(@NonNull String key, @NonNull String value) {
        if (!LanguageRegistry.instance().getStringLocalization(key, DEFAULT_LOCALE).isEmpty())
            return;
        val map = tempMap.get();
        map.put(key, value);
        LanguageRegistry.instance().injectLanguage(DEFAULT_LOCALE, map);
        map.clear();
    }
}
