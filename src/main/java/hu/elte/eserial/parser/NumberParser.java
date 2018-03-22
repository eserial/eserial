package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NumberParser {
    public ParserEntry<String, Object> parser(String json) {
        ParserEntry<String, Object> entry;

        json = json.replaceAll("\"", "");
        String parts[] = json.split(":");

        if(StringUtils.isInteger(parts[1].trim())) {
            entry = new ParserEntry(parts[0].trim(), Long.parseLong(parts[1].trim()));
        } else {
            entry = new ParserEntry(parts[0].trim(), Double.parseDouble(parts[1].trim()));
        }

        return entry;
    }
}


