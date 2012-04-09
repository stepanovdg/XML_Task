package resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 3/23/12
 * Time: 4:17 AM
 */
public final class ConfigurationManager {
    private static final String BUNDLE_NAME = "resources." + "config";
    private static ResourceBundle resourceBundle;
    private static ConfigurationManager instance;

    //класс извлекает информацию из файла config.properties
    static {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("", ""));
        ConfigurationManager instance = new ConfigurationManager();
    }

    public ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}