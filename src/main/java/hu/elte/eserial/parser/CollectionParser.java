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
        String value;

        json = json.substring(1, json.length());

        while(json.length() > 0) {
            if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
                int index = StringUtils.findNumber(json);
                value = json.substring(0, index + 1);
                json = json.substring(index + 1, json.length());
                list.add(new NumberParser(value).parser());
            } else if(json.startsWith("\"")) {
                json = json.substring(1, json.length());
                int index = json.indexOf("\"");
                value = json.substring(0, index);
                json = json.substring(index + 1, json.length());
                list.add(new StringParser(value).parser());
            } else if(json.startsWith("false")) {
                json = json.substring(5, json.length());
                list.add(new BooleanParser("false").parser());
            } else if(json.startsWith("true")) {
                json = json.substring(4, json.length());
                list.add(new BooleanParser("true").parser());
            } else if(json.startsWith("null")) {
                json = json.substring(4, json.length());
                list.add(new NullParser("null").parser());
            } else if(json.startsWith("[")) {
                int index = StringUtils.findSquareBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index, json.length());
                list.add(new CollectionParser(value).parser());
            } else if(json.startsWith("{")) {
                int index = StringUtils.findClosingCurlyBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index, json.length());
                list.add(new ObjectParser(value).parser());
            } else {
                json = json.substring(1, json.length());
            }
        }

        return list;
    }
}
