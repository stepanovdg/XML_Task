package com.epam.xmltask.parsers;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/27/12
 * Time: 5:48 AM
 */
public class ParserBuilderFactory {
    private static final StAXBuilder ST_AX_BUILDER = new StAXBuilder();
    private static final DomBuilder DOM_BUILDER = new DomBuilder();
    private static final String SAX = "sax";
    private static final String STAX = "stax";


    public static AbstractBuilder getInstance(String st) {
        switch (st) {
            case SAX:
                return new SaxBuilder();
            case STAX:
                return ST_AX_BUILDER;
            default:
                return DOM_BUILDER;
        }
    }
}
