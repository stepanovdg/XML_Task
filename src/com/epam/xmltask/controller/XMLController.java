package com.epam.xmltask.controller;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
import resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/30/12
 * Time: 3:52 AM
 */
class XMLBackUpper extends Thread {

    private XMLController xmlController;
//    private final static long DELAY = 900000;
    private final static long DELAY = 90000;

    XMLBackUpper(XMLController xmlController) {
        this.xmlController = xmlController;
    }

    public void run() {
        while (true) {
            try {
                sleep(DELAY);
          //      xmlController.backUp(xmlController.getXml());
                xmlController.backUp(xmlController.getBuffer());
            } catch (IOException |
                   SAXException |
                    ClassNotFoundException |
                    IllegalAccessException |
                    InstantiationException |
                    InterruptedException ignored) {
            }
        }
    }
}

public class XMLController {
    private static XMLController ourInstance = new XMLController();
    private static ReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock readLock = rwl.readLock();
    static Lock writeLock = rwl.writeLock();
    private static DocumentBuilder parser;
    private Executor adder = Executors.newCachedThreadPool();

    static {
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ignored) {

        }
    }

    private static final String GOODS_XML = "GOODS_XML";
    private String currentPath;
    private AtomicReference<Document> xml = new AtomicReference<Document>();
    private AtomicReference<DOMSource> xmlSource = new AtomicReference<DOMSource>();
    private AtomicReference<ByteArrayBuffer> buffer = new AtomicReference<ByteArrayBuffer>();
//    private BoundedBuffer xmlBuffer;

    public ByteArrayBuffer getBuffer() {
        return buffer.get();
    }


    public StreamSource getSource() {
        return new StreamSource(getBuffer().newInputStream());
    }

    public StreamResult getResult() {
        return new StreamResult(getBuffer());
    }

    public boolean setBuffer(ByteArrayBuffer expect,ByteArrayBuffer update) {
        return this.buffer.compareAndSet(expect,update);
    }

    public void setBuffer(ByteArrayBuffer update) {
        this.buffer.set(update);
    }

    public Document getXml() {
        return xml.get();
    }

    private void setXml(Document xml) {
        this.xml.set(xml);
    }

    public DOMSource getXmlSource() {
        return xmlSource.get();
    }

    private void setXmlSource(DOMSource xmlSource) {
        this.xmlSource.set(xmlSource);
    }

    public void setXmlSource(StreamResult xmlResult) throws IOException, SAXException, TransformerException {
        Writer writer = xmlResult.getWriter();
        String xmlString = writer.toString();
        byte[] bytes = xmlString.getBytes();
        //  setBuffer(new ByteArrayBuffer(bytes));   //use if xml save in buffer cache
        InputStream inputStream = new ByteArrayInputStream(bytes);
        setXml(parser.parse(inputStream));
        setXmlSource(new DOMSource(getXml()));
    }

    private static DocumentBuilder getParser() {
        return parser;

    }

    public void parse(String xmlPath) throws IOException, SAXException, TransformerException {
        currentPath = xmlPath;
        setXml(getParser().parse(xmlPath));
        setXmlSource(new DOMSource(getXml()));
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        ByteArrayBuffer buff = new ByteArrayBuffer();
        StreamResult result = new StreamResult(buff);
        try {
            readLock.lock();
            transformer.transform(getXmlSource(),result);
        } finally {
            readLock.unlock();
        }
        setBuffer(buff);

    }

    public static XMLController getInstance() {
        return ourInstance;
    }

    private XMLController() {
        currentPath = ConfigurationManager.getProperty(GOODS_XML);
        XMLBackUpper backUpper = new com.epam.xmltask.controller.XMLBackUpper(this);

        adder.execute(backUpper);
    }

    public void backUp(Document xml) throws ClassNotFoundException, IllegalAccessException, InstantiationException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS domImplLS = (DOMImplementationLS) registry.getDOMImplementation("LS");
        LSSerializer ser = domImplLS.createLSSerializer();  // Create a serializer for the DOM
        LSOutput out = domImplLS.createLSOutput();
        FileOutputStream fileOutputStream = new FileOutputStream(currentPath);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        out.setCharacterStream(printWriter);
        ser.write(xml, out);
    }

    public void backUp(ByteArrayBuffer buffer) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SAXException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(currentPath);
        buffer.writeTo(fileOutputStream);
    }

    public void addTask(HttpServletRequest request) {
        Runnable producer = null;
        adder.execute(producer);
    }
}
