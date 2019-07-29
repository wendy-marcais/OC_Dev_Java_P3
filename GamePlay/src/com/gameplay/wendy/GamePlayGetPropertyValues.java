package com.gameplay.wendy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GamePlayGetPropertyValues {

    /**
     * @param parametre parametre
     * @return property
     * @throws IOException if a problem is found in property
     */
    public String getPropValues(String parametre) throws IOException {

        InputStream inputStream = null;
        String property = null;

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            property = prop.getProperty(parametre);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return property;
    }
}
