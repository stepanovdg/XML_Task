package com.epam.xmltask.controller;

import com.epam.xmltask.entity.TableElement;
import com.epam.xmltask.exception.GoodsException;
import com.epam.xmltask.parsers.ParserBuilderFactory;
import resources.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/19/12
 * Time: 10:32 AM
 *
 *
 */
public final class ParserServlet extends HttpServlet {
    private static final String REQ_PARAM_BUILDER = "REQ_PARAM_BUILDER" ;
    private static final String REQ_ATR_TABLE = "REQ_ATR_TABLE";
    private static final String PATH = "PATH";
    private static final String PARSER_JSP = "PARSER_JSP";
    private static final String ERROR_JSP = "ERROR_JSP";

    public ParserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String parameter = request.getParameter(ConfigurationManager.getProperty(REQ_PARAM_BUILDER));
        try {
            String path = ConfigurationManager.getProperty(PATH);
            ArrayList<TableElement> table = ParserBuilderFactory.getInstance(parameter).build(path);
            request.setAttribute(ConfigurationManager.getProperty(REQ_ATR_TABLE), table);
            page = ConfigurationManager.getProperty(PARSER_JSP);
        } catch (GoodsException e) {
            page = ConfigurationManager.getProperty(ERROR_JSP);
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}