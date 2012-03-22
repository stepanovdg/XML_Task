package com.epam.xmltask.controller;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;
import com.epam.xmltask.parsers.AbstractBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/19/12
 * Time: 10:32 AM
 * <!--<goods xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="goods.xsd">-->
 * <!--<!DOCTYPE goods SYSTEM "goods.dtd">-->
 */
public final class ControllerServlet extends HttpServlet {
    private static final String PARSER_JSP = "/view/parser.jsp";
    private static final String ERROR_JSP = "/view/error.jsp";
    //    String path = "C:\\Documents and Settings\\Dzmitry_Stsiapanau\\Documents\\Java\\Intelig.Idea\\XML Task\\src\\com\\epam\\xmltask\\xml\\goods.xml";
    String path = "C:" + File.separator + "Documents and Settings"
            + File.separator + "Dzmitry_Stsiapanau" +
            File.separator + "Documents" + File.separator +
            "Java" + File.separator + "Intelig.Idea" +
            File.separator + "XML Task" + File.separator +
            "src" + File.separator + "com" + File.separator +
            "epam" + File.separator + "xmltask" + File.separator
            + "xml" + File.separator + "goods.xml";

    public ControllerServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String parameter = request.getParameter("builder");
//        String parameter = "";
        try {
            ArrayList<TableElement> table = AbstractBuilder.getInstance(parameter).build(path);
            request.setAttribute("table", table);
            page = PARSER_JSP;
        } catch (GoodsException e) {
            page = ERROR_JSP;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //вызов страницы ответа на запрос
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}