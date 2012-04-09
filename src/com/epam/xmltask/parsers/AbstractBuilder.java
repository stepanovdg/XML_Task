package com.epam.xmltask.parsers;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;
import resources.ConfigurationManager;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:11 AM
 */
public abstract class AbstractBuilder {
    public static final String CATEGORY = "category";
    public static final String SUB_CATEGORY = "subCategory";
    public static final String UNIT = "unit";
    public static final String DATE_OF_ISSUE = "dateOfIssue";
    public static final String COLOR = "color";
    public static final String NOT_IN_STOCK = "notInStock";
    public static final String PRICE = "price";
    public static final String DATE_SPLITTER = ConfigurationManager.getProperty("DATE_SPLITTER");
    public static final String SQL_DATE_PATTERN = ConfigurationManager.getProperty("SQL_DATE_PATTERN");
    public static final String NAME = "name";
    public static final String MODEL = "model";
    public static final String PRODUCER = "producer";

    public abstract ArrayList<TableElement> build(String filePath) throws GoodsException;
}