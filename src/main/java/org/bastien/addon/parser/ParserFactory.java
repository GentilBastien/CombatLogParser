package org.bastien.addon.parser;

import org.bastien.addon.parser.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class ParserFactory {
    private static final Map<Class<? extends Parser<?>>, Parser<?>> MAP = new HashMap<>();

    static {
        ParserFactory.registerParser(TimestampParser.class);
        ParserFactory.registerParser(EncochedNumberParser.class);
        ParserFactory.registerParser(AbsorbedParser.class);
        ParserFactory.registerParser(ParenthesisNumberParser.class);
        ParserFactory.registerParser(DamageParser.class);
        ParserFactory.registerParser(HealParser.class);
        ParserFactory.registerParser(ChargesParser.class);
        ParserFactory.registerParser(PlayerParser.class);
        ParserFactory.registerParser(MobParser.class);
        ParserFactory.registerParser(PetParser.class);
        ParserFactory.registerParser(AbilityParser.class);
        ParserFactory.registerParser(DisciplineParser.class);
        ParserFactory.registerParser(EventParser.class);
        ParserFactory.registerParser(EffectParser.class);
    }

    private ParserFactory() {
    }

    private static <T extends Parser<?>> void registerParser(Class<T> parserClass) {
        try {
            Parser<?> parser = parserClass.getDeclaredConstructor().newInstance();
            ParserFactory.MAP.put(parserClass, parser);
        } catch (ReflectiveOperationException ignored) {
        }
    }

    public static <T extends Parser<?>> T getInstance(Class<T> parserClass) {
        Parser<?> instance = ParserFactory.MAP.get(parserClass);
        return parserClass.cast(instance);
    }
}
