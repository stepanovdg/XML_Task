package com.epam.xmltask.exception;

import com.epam.xmltask.out.GoodsOutManager;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/20/12
 * Time: 5:18 AM
 */
@SuppressWarnings({"ALL"})
public class GoodsException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param msg
     * @param e
     */

    public GoodsException(String msg, Exception e) {
        GoodsOutManager.getInstance().println(e + " " + msg);
    }
}
