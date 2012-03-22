package com.epam.xmltask.parsers;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:13 AM
 */
public class StAXBuilder extends AbstractBuilder {
    private static final String SQL_DATE_PATTERN = "{0}-{1}-{2}";
    private static final String DON_T_EXIST_SUCH_FILE_OF_INIT_VEGET_XML_IN_THIS_DIRECTORY = "Don't exist such file of initVeget.xml in this directory";
    private static final String PRICE = "price";
    private static final String NOT_IN_STOCK = "notInStock";
    private static final String COLOR = "color";
    private static final String DATE_OF_ISSUE = "dateOfIssue";
    private static final String MODEL = "model";
    private static final String PRODUCER = "producer";
    private static final String NAME = "name";
    private static final String UNIT = "unit";
    private static final String SUB_CATEGORY = "subCategory";
    private static final String CATEGORY = "category";
    private String currentElement;
    TableElement tableElement;
    ArrayList<TableElement> table = new ArrayList<>();

    private void parse(InputStream input) {
        XMLInputFactory inputFactory =
                XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader =
                    inputFactory.createXMLStreamReader(input);
            process(reader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings({"ConstantConditions"})
    private void process(XMLStreamReader reader)
            throws XMLStreamException {
        String qName;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    qName = reader.getLocalName();
                    switch (qName) {
                        case CATEGORY:
                            tableElement = new TableElement();
                            tableElement.setCategory(reader.getAttributeValue(null, NAME));
                            break;
                        case SUB_CATEGORY:
                            if (currentElement == null) {
                                tableElement = new TableElement(tableElement);
                            }
                            tableElement.setSubCategory(reader.getAttributeValue(null, NAME));
                            break;
                        case UNIT:
                            if (currentElement == null) {
                                tableElement = new TableElement(tableElement);
                                tableElement.setStock(false);
                            }
                            tableElement.setUnit(reader.getAttributeValue(null, NAME));
                            tableElement.setProvider(reader.getAttributeValue(null, PRODUCER));
                            tableElement.setModel(reader.getAttributeValue(null, MODEL));
                            break;
                        default:
                            currentElement = qName;
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    qName = reader.getLocalName();
                    switch (qName) {
                        case CATEGORY:
                            currentElement = null;
                            break;
                        case SUB_CATEGORY:
                            currentElement = null;
                            break;
                        case UNIT:
                            table.add(tableElement);
                            currentElement = null;
                            break;
                        default:
                            currentElement = null;
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS: {
                    if (currentElement != null) {
                        switch (currentElement) {
                            case DATE_OF_ISSUE:
                                String date = reader.getText();
                                String[] dateArr = date.split("-");
                                date = MessageFormat.format(SQL_DATE_PATTERN, dateArr[2], dateArr[1], dateArr[0]);
                                tableElement.setDateOfIssue(Date.valueOf(date));
                                break;
                            case COLOR:
                                tableElement.setColor(reader.getText());
                                break;
                            case NOT_IN_STOCK:
                                tableElement.setStock(Boolean.valueOf(reader.getText()));
                                break;
                            case PRICE:
                                tableElement.setPrice(Integer.valueOf(reader.getText()));
                                break;
                            default:
                                break;
                        }
                    }
                }
                break;
                default:
                    break;
            }
        }
    }


    @Override
    public ArrayList<TableElement> build(String filePath) throws GoodsException {
        try {
            InputStream input ;
            input = new FileInputStream(filePath);
            this.parse(input);
        } catch (FileNotFoundException e) {
            String msg = DON_T_EXIST_SUCH_FILE_OF_INIT_VEGET_XML_IN_THIS_DIRECTORY;
            throw new GoodsException(msg, e);
        }
        return table;
    }
}
