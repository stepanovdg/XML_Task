package com.epam.xmltask.controller;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 4/3/12
 * Time: 9:18 AM
 */
public class Validator {


    private static final int MODEL_LENGTH = 5;
    private static final int DATE_LENGTH = 10;
    private static final String MODEL_REGEX = "[a-zA-ZА-Яа-я]{2}[0-9]{3}";
    private static final String DATE_REGEX = "(((0[1-9]|[12][0-9]|3[01])([-])(0[13578]|10|12)([-])(\\d{4}))|(([0][1-9]|[12][0-9]|30)([-])(0[469]|11)([-])(\\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-])(02)([-])(\\d{4}))|((29)(\\.|-|\\/)(02)([-])([02468][048]00))|((29)([-])(02)([-])([13579][26]00))|((29)([-])(02)([-])([0-9][0-9][0][48]))|((29)([-])(02)([-])([0-9][0-9][2468][048]))|((29)([-])(02)([-])([0-9][0-9][13579][26])))";
    private static final String PRICE_REGEX = "^[0-9]+([,|.]{0,1}[0-9]{2}){0,1}$";
    private static final String NAME_MESSAGE = "Name is not valid";
    private static final String PRODUCER_MESSAGE = "Producer is not valid";
    private static final String MODEL_MESSAGE = "Model is not valid";
    private static final String DATE_MESSAGE = "Date is not valid";
    private static final String COLOR_MESSAGE = "Color is not valid";
    private static final String PRICE_MESSAGE = "Price is not valid";
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Validator() {
        this.message = "";
    }

    public static boolean validateName(String name) {

        return !name.equals("");
    }

    public static boolean validateProducer(String producer) {

        return !producer.equals("");
    }

    public static boolean validateModel(String model) {

        return !model.equals("") &&
                model.matches(MODEL_REGEX) &&
                model.length() == MODEL_LENGTH;
    }

    public static boolean validateDate(String date) {

        return !date.equals("") &&
                date.matches(DATE_REGEX) &&
                date.length() == DATE_LENGTH;
    }

    public static boolean validateColor(String color) {
        return !color.equals("");
    }

    public static boolean validatePrice(String price) {

        return !price.equals("") &&
                price.matches(PRICE_REGEX);
    }

    public static String validate(Validator validator, String name, String producer,
                                  String model, String date,
                                  String color, String stock, String price) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!validateName(name)) stringBuilder.append(NAME_MESSAGE);
        if (!validateProducer(producer)) stringBuilder.append(PRODUCER_MESSAGE);
        if (!validateModel(model)) stringBuilder.append(MODEL_MESSAGE);
        if (!validateDate(date)) stringBuilder.append(DATE_MESSAGE);
        if (!validateColor(color)) stringBuilder.append(COLOR_MESSAGE);
        if (!validatePrice(price) && !Boolean.valueOf(stock)) stringBuilder.append(PRICE_MESSAGE);
        validator.setMessage(stringBuilder.toString());
        return validator.getMessage();
    }
}
