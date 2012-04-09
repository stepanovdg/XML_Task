package delete;


import com.epam.xmltask.entity.TableElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/30/12
 * Time: 4:57 AM
 */
public class GoodsManager {
    private Document document;

    public GoodsManager(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void add(TableElement tableElement) {
        Element unit = createElement("unit");
        unit.setAttribute("name",tableElement.getUnit());
        unit.setAttribute("producer",tableElement.getProvider());
        unit.setAttribute("model",tableElement.getModel());
        createEl(unit, "dateOfIssue", tableElement.getDateOfIssue().toString());
        createEl(unit,"color",tableElement.getColor());
        if(tableElement.getStock()){
           createEl(unit,"notInStock","true");
        }else{
           createEl(unit,"price",tableElement.getPrice().toString());
        }
        NodeList nodes = document.getElementsByTagName(tableElement.getSubCategory());
        Element subCategory = (Element) nodes.item(0);
        subCategory.appendChild(unit);

    }

    private void createEl(Element root, String teg, String text) {
        root.appendChild(createElement(teg, text));
    }

    private Element createElement(String teg, String text) {
        Element item;
        item = document.createElementNS(null, teg);
        item.appendChild(document.createTextNode(text));
        return item;
    }
    private Element createElement(String teg) {
        Element item;
        item = document.createElementNS(null, teg);
        return item;
    }
}

