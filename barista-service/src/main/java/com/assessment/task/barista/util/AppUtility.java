package com.assessment.task.barista.util;

import java.util.ResourceBundle;

public class AppUtility {

    public static String getAPIUrl(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("api-url");
        return resourceBundle.getString(key);
    }
}
