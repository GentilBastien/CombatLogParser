package org.bastien.addon.model.parser.impl;

import org.bastien.addon.model.entities.Ability;

import java.util.regex.Matcher;

public class AbilityParser extends RegExpParser<Ability> {

    @Override
    public Ability parse(String source) {
        if (source.equals("[]"))
            return null;
        final Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            long id = Long.parseLong(matcher.group(2));
            String name = matcher.group(1);
            return new Ability(id, name, false); //todo check en BDD pour savoir si oui on non c'est un buff
        }
        return null;
    }

    @Override
    public String getRegex() {
        return "\\[(.*?)\\s\\{(\\d+)}\\]";
    }
}
