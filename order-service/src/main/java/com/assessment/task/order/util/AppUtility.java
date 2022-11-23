package com.assessment.task.order.util;

import java.util.ResourceBundle;

public class AppUtility {

    public static String getAPIUrl(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("api-url");
        return resourceBundle.getString(key);
    }
}
