package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.LinkedList;

public class CollectionParser extends AbstractParser{

    CollectionParser(String json) {
        super(json);
    }

    @Override
    LinkedList<Object> parser() {
        LinkedList<Object> list = new LinkedList<>();

        for(String part : json.split(",")) {
            part = part.trim();
            if(StringUtils.isNumeric(part)) {
                list.add(new NumberParser(part).parser());
            } else if(StringUtils.isBoolean(part)){
                list.add(new BooleanParser(part).parser());
            } else if(part.equals("null")) {
                list.add(new NullParser(part).parser());
            } else {
                list.add(new StringParser(part).parser());
            }
        }

        return list;
    }
}
