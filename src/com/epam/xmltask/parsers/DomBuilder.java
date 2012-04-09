package com.epam.xmltask.parsers;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
public class DomBuilder extends AbstractBuilder {
    private static final String THE_MISTAKE_OF_SAX_PARSER_IN_XERCES = "The mistake of SAX Parser in xerces";
    private static final String THE_MISTAKE_OF_I_O_STREAM_IN_XERCES_ANALYZING = "The mistake of I/O stream in xerces analyzing ";




    private ArrayList<TableElement> analyzeGoods(Element root) throws GoodsException {
        NodeList goodsNodes = root.getChildNodes();
        ArrayList<TableElement> table = new ArrayList<>();
        for (int i = 0; i < goodsNodes.getLength(); i++) {
            Node node = goodsNodes.item(i);

            Element goodsElement;
            if (node.getNodeType() == Node.TEXT_NODE) {
                continue;
            } else {
                goodsElement = (Element) goodsNodes.item(i);
            }
            NodeList subNodes = goodsElement.getChildNodes();
            for (int k = 0; k < subNodes.getLength(); k++) {
                node = subNodes.item(k);
                Element subElement;
                if (node.getNodeType() == Node.TEXT_NODE) {
                   continue;
                } else {
                    subElement = (Element) subNodes.item(k);
                }
                NodeList unitNodes = subElement.getChildNodes();
                for (int u = 0; u < unitNodes.getLength(); u++) {
                     node = unitNodes.item(u);
                Element unitElement;
                if (node.getNodeType() == Node.TEXT_NODE) {
                    continue;
                } else {
                   unitElement = (Element) unitNodes.item(u);
                }
                    TableElement tableElement = new TableElement();
                    tableElement.setCategory(goodsElement.getAttribute(NAME));
                    tableElement.setSubCategory(subElement.getAttribute(NAME));
                    tableElement.setUnit(unitElement.getAttribute(NAME));
                    tableElement.setProvider(unitElement.getAttribute(PRODUCER));
                    tableElement.setModel(unitElement.getAttribute(MODEL));
                    String date =  getBabyValue(unitElement, DATE_OF_ISSUE);
                    String[] dateArr = date.split(DATE_SPLITTER);
                    date = MessageFormat.format(SQL_DATE_PATTERN, dateArr[2], dateArr[1], dateArr[0]);
                    tableElement.setDateOfIssue(Date.valueOf(date));
                    tableElement.setColor((getBabyValue(unitElement, COLOR)));
                    if (unitElement.getElementsByTagName(PRICE).getLength() == 0) {
                        tableElement.setStock(true);
                    } else {
                        tableElement.setPrice(Integer.valueOf(getBabyValue(unitElement, PRICE)));
                        tableElement.setStock(false);
                    }
                    table.add(tableElement);
                }
            }
        }
        return table;
    }


    private Element getBaby(Element parent, String childName) {
        NodeList nlist =
                parent.getElementsByTagName(childName);
        return (Element) nlist.item(0);
    }


    private String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }

    @Override
    public ArrayList<TableElement> build(String filePath) throws GoodsException {
        try {
            DOMParser parser = new DOMParser();
            parser.parse(filePath);
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            return this.analyzeGoods(root);

        } catch (SAXException e) {
            String msg = THE_MISTAKE_OF_SAX_PARSER_IN_XERCES;
            throw new GoodsException(msg, e);
        } catch (IOException e) {
            String msg = THE_MISTAKE_OF_I_O_STREAM_IN_XERCES_ANALYZING;
            throw new GoodsException(msg, e);
        }
    }
}
