package com.epam.xmltask.controller;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import org.xml.sax.SAXException;
import resources.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.epam.xmltask.controller.XMLController.getInstance;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/26/12
 * Time: 8:41 AM
 */
@WebServlet(name = "XSLTServlet")
public class XSLTServlet extends HttpServlet {
    private static final String REQ_PARAM_XSL = "REQ_PARAM_XSL";
    private static final String GOODS_XML = "GOODS_XML";
    private static final String ERROR_JSP = "ERROR_JSP";
    private static final String TRANSFORMER_PARAMETER = "TRANSFORMER_PARAMETER";
    private static final String TRANSFORMER_PARAMETER_VALUE = "TRANSFORMER_PARAMETER_VALUE";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String SUB_CATEGORY_NAME = "subCategoryName";
    private static final String UNIT_NAME = "unitName";
    private static final String ALL_XSL = "ALL_XSL";
    private static final String ADD_XSL = "ADD_XSL";
    private static final String SHOW_ADD = "showAdd";
    private static final String UNIT_PRODUCER = "unitProducer";
    private static final String UNIT_MODEL = "unitModel";
    private static final String UNIT_DATE = "unitDate";
    private static final String UNIT_COLOR = "unitColor";
    private static final String UNIT_STOCK = "unitStock";
    private static final String UNIT_PRICE = "unitPrice";
    private static final String ADD = "Add";
    private static final String YES = "yes";
    private static final String VALIDATE_MESSAGE = "validateMessage";
    private static final String REQUEST_CHECK = "check";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String sourceId = ConfigurationManager.getProperty(GOODS_XML);
        try {
            getInstance().parse(sourceId);
        } catch (TransformerException | IOException | SAXException e) {
            e.printStackTrace();
        }


    }

    private void processRequestVariant2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String parameter = request.getParameter(ConfigurationManager.getProperty(REQ_PARAM_XSL));
        String showAdd = request.getParameter(SHOW_ADD);
        Validator validator = new Validator();
        if (ADD.equals(parameter)) {
            try {
                Transformer transformer = TransformerBuilder.getValTransform();
                transformer.setParameter(TRANSFORMER_PARAMETER, TRANSFORMER_PARAMETER_VALUE);
                transformer.setParameter(VALIDATE_MESSAGE, validator);
                settingParameters(request, transformer);
                StringWriter stringWriter = new StringWriter();
                StreamResult result = new StreamResult(stringWriter);
                transformer.transform(getInstance().getXmlSource(), result);
                if ("".equals(validator.getMessage())) {
                    getInstance().addTask(request);
                } else {
                    showAdd = String.valueOf(true);
                }
            } catch (TransformerException e) {
                String page = ConfigurationManager.getProperty(ERROR_JSP);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
                e.printStackTrace();
            }
        }
        PrintWriter os = response.getWriter();
        try {
            Transformer transformer = TransformerBuilder.getAllTransform();
            transformer.setParameter(TRANSFORMER_PARAMETER, TRANSFORMER_PARAMETER_VALUE);
            settingParameters(request, transformer);
            if (validator.getMessage() != null) {
                transformer.setParameter(VALIDATE_MESSAGE, validator.getMessage());
            }
            if (showAdd != null) {
                transformer.setParameter(SHOW_ADD, showAdd);
            }
            transformer.setOutputProperty(OutputKeys.INDENT, YES);
            /*  transformer.transform(getInstance().getSource(), new StreamResult(os));
           os.close(); */
            transformer.transform(getInstance().getXmlSource(), new StreamResult(os));
            os.close();
        } catch (TransformerException e) {
            String page = ConfigurationManager.getProperty(ERROR_JSP);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
            e.printStackTrace();
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String parameter = request.getParameter(ConfigurationManager.getProperty(REQ_PARAM_XSL));
        String showAdd = request.getParameter(SHOW_ADD);
        String reqCheck = request.getParameter(REQUEST_CHECK);
        if (reqCheck == null){
           request.getSession().setAttribute(REQUEST_CHECK, reqCheck);
        }
        Boolean refreshCheck = true;
        Object check = request.getSession().getAttribute(REQUEST_CHECK);
        if (check != null) {
            refreshCheck = (Boolean) check;
        }
        Validator validator = new Validator();
        if (ADD.equals(parameter) && refreshCheck) {
            try {
                Transformer transformer = TransformerBuilder.getFullAddTransform();
                transformer.setParameter(TRANSFORMER_PARAMETER, TRANSFORMER_PARAMETER_VALUE);
                transformer.setParameter(VALIDATE_MESSAGE, validator);
                settingParameters(request, transformer);
                //StringWriter stringWriter = new StringWriter();
                //StreamResult result = new StreamResult(stringWriter);
                //transformer.transform(getInstance().getXmlSource(), result);
                ByteArrayBuffer expect;
                ByteArrayBuffer buff;
                Boolean until;
                do {
                    expect = getInstance().getBuffer();
                    StreamSource source = new StreamSource(expect.newInputStream());
                    buff = new ByteArrayBuffer();
                    StreamResult result = new StreamResult(buff);
                 //   try {
                   //     XMLController.readLock.lock();
                        transformer.transform(source, result);
            //        } finally {
              //          XMLController.readLock.unlock();
                //    }
                    if ("".equals(validator.getMessage())) {
                        //  getInstance().setXmlSource(result);
                        until = !getInstance().setBuffer(expect, buff);
                        refreshCheck = Boolean.FALSE;
                        request.getSession().setAttribute(REQUEST_CHECK, refreshCheck);
                    } else {
                        until = false;
                        showAdd = String.valueOf(true);
                    }
                } while (until);


            } catch (/*SAXException |*/ TransformerException e) {
                String page = ConfigurationManager.getProperty(ERROR_JSP);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
                e.printStackTrace();
            }
        }
        PrintWriter os = response.getWriter();
        try {
            Transformer transformer = TransformerBuilder.getAllTransform();
            transformer.setParameter(TRANSFORMER_PARAMETER, TRANSFORMER_PARAMETER_VALUE);
            settingParameters(request, transformer);
            if (validator.getMessage() != null) {
                transformer.setParameter(VALIDATE_MESSAGE, validator.getMessage());
            }
            if (showAdd != null) {
                transformer.setParameter(SHOW_ADD, showAdd);
            }
            transformer.setOutputProperty(OutputKeys.INDENT, YES);
          //  try {
            //    XMLController.readLock.lock();
                transformer.transform(getInstance().getSource(), new StreamResult(os));
           // } finally {
             //   XMLController.readLock.unlock();
            //}

            os.close();
            // transformer.transform(getInstance().getXmlSource(), new StreamResult(os));
            // os.close();
        } catch (TransformerException e) {
            String page = ConfigurationManager.getProperty(ERROR_JSP);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
            e.printStackTrace();
        }
    }

    public void settingParameters(HttpServletRequest request, Transformer transformer) {
        String parameter;
        String categoryName = request.getParameter(CATEGORY_NAME);
        String subCategoryName = request.getParameter(SUB_CATEGORY_NAME);


        if (categoryName != null) {
            transformer.setParameter(CATEGORY_NAME, categoryName);
        }
        if (subCategoryName != null) {
            transformer.setParameter(SUB_CATEGORY_NAME, subCategoryName);
        }
        parameter = request.getParameter(UNIT_NAME);
        if (parameter != null) {
            transformer.setParameter(UNIT_NAME, parameter);
        }
        parameter = request.getParameter(UNIT_PRODUCER);
        if (parameter != null) {
            transformer.setParameter(UNIT_PRODUCER, parameter);
        }
        parameter = request.getParameter(UNIT_MODEL);
        if (parameter != null) {
            transformer.setParameter(UNIT_MODEL, parameter);
        }
        parameter = request.getParameter(UNIT_DATE);
        if (parameter != null) {
            transformer.setParameter(UNIT_DATE, parameter);
        }
        parameter = request.getParameter(UNIT_COLOR);
        if (parameter != null) {
            transformer.setParameter(UNIT_COLOR, parameter);
        }
        parameter = request.getParameter(UNIT_STOCK);
        if (parameter != null) {
            transformer.setParameter(UNIT_STOCK, parameter);
        }
        parameter = request.getParameter(UNIT_PRICE);
        if (parameter != null) {
            transformer.setParameter(UNIT_PRICE, parameter);
        }
    }
}
