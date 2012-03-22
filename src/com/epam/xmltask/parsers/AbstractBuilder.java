package com.epam.xmltask.parsers;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:11 AM
 */
public abstract class AbstractBuilder {
    public static AbstractBuilder getInstance(String st) {
        switch (st) {
            case "sax":
                return new SaxBuilder();
            case "stax":
                return new StAXBuilder();
            default:
                return new DomBuilder();
        }
    }

    public abstract ArrayList<TableElement> build(String filePath) throws GoodsException;
}