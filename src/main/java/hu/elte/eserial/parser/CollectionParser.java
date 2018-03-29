package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.LinkedList;

public class CollectionParser {
    public LinkedList<Object> parser(String json) {
        LinkedList<Object> list = new LinkedList<>();

        for(String part : json.split(",")) {
            part = part.trim();
            if(StringUtils.isNumeric(part)) {
                list.add(new NumberParser().parser(part));
            } else if(StringUtils.isBoolean(part)){
                list.add(new BooleanParser().parser(part));
            } else if(part.equals("null")) {
                list.add(new NullParser().parser(part));
            } else {
                list.add(new StringParser().parser(part));
            }
        }

        return list;
    }
}
