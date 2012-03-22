package com.epam.xmltask.parsers;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:13 AM
 */
public class SaxBuilder extends AbstractBuilder implements ContentHandler {
    private static final String COM_SUN_ORG_APACHE_XERCES_INTERNAL_PARSERS_SAXPARSER = "com.sun.org.apache.xerces.internal.parsers.SAXParser";
    private static final String THE_MISTAKE_OF_SAX_PARSER_IN_SAX = "The mistake of SAX Parser in SAX";
    private static final String DON_T_EXIST_SUCH_FILE_OF_INIT_VEGET_XML_IN_THIS_DIRECTORY = "Don't exist such file of in this directory";
    private static final String THE_MISTAKE_OF_I_O_STREAM_IN_SAX_ANALYZING_INIT_VEGET_XML = "The mistake of I/O stream in sax analyzing xml ";
    private static final String SQL_DATE_PATTERN = "{0}-{1}-{2}";
    private static final String CATEGORY = "category";
    private static final String SUB_CATEGORY = "subCategory";
    private static final String UNIT = "unit";
    private static final String DATE_OF_ISSUE = "dateOfIssue";
    private static final String COLOR = "color";
    private static final String NOT_IN_STOCK = "notInStock";
    private static final String PRICE = "price";

    private String currentElement;
    TableElement tableElement;
    ArrayList<TableElement> table = new ArrayList<>();


    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case CATEGORY:
                tableElement = new TableElement();
                tableElement.setCategory(atts.getValue(0));
                break;
            case SUB_CATEGORY:
                  if (currentElement == null) {
                     tableElement =new TableElement(tableElement);
                }
                tableElement.setSubCategory(atts.getValue(0));
                break;
            case UNIT:
                if (currentElement == null) {
                     tableElement =new TableElement(tableElement);
                    tableElement.setStock(false);
                }
                tableElement.setUnit(atts.getValue(0));
                tableElement.setProvider(atts.getValue(1));
                tableElement.setModel(atts.getValue(2));
                break;
            default:
                currentElement = qName;
                break;
        }

        //получение и вывод информации об атрибутах элемента


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (currentElement != null) {
            switch (currentElement) {
                case DATE_OF_ISSUE:
                    String date = new String(ch, start, length);
                    String[] dateArr = date.split("-");
                    date = MessageFormat.format(SQL_DATE_PATTERN, dateArr[2], dateArr[1], dateArr[0]);
                    tableElement.setDateOfIssue(Date.valueOf(date));
                    break;
                case COLOR:
                    tableElement.setColor(new String(ch, start, length));
                    break;
                case NOT_IN_STOCK:
                    tableElement.setStock(Boolean.valueOf(new String(ch, start, length)));
                    break;
                case PRICE:
                    tableElement.setPrice(Integer.valueOf(new String(ch, start, length)));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }


    @Override
    public ArrayList<TableElement> build(String filePath) throws GoodsException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader(COM_SUN_ORG_APACHE_XERCES_INTERNAL_PARSERS_SAXPARSER);
            reader.setContentHandler(this);
            reader.parse(filePath);
        } catch (SAXException e) {
            String msg = THE_MISTAKE_OF_SAX_PARSER_IN_SAX ;
            throw new GoodsException(msg, e);
        } catch (FileNotFoundException e) {
            String msg = DON_T_EXIST_SUCH_FILE_OF_INIT_VEGET_XML_IN_THIS_DIRECTORY;
            throw new GoodsException(msg, e);
        } catch (IOException e) {
            String msg = THE_MISTAKE_OF_I_O_STREAM_IN_SAX_ANALYZING_INIT_VEGET_XML;
            throw new GoodsException(msg, e);
        }
        return table;
    }
}
