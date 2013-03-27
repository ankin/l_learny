package com.learny.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LearnyProperties {

    private final static Logger logger = LoggerFactory.getLogger(LearnyProperties.class);

    private static final String LOGIN_WITHOUT_CREDENTIALS_ENABLED = "login.without.credentials.enabled";
    private static final String LOGIN_WITHOUT_CREDENTIALS_EMAIL = "login.without.credentials.email";
    private static final String LOGIN_WITHOUT_CREDENTIALS_PASSWORD = "login.without.credentials.password";

    private Properties properties;

    private static LearnyProperties propertiesUtils;

    private LearnyProperties() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/learny.properties"));
        } catch (Exception e) {
            logger.error("Failed to load properties file!", e);
        }
    }

    private static synchronized LearnyProperties getInstance() {
        if (propertiesUtils == null) {
            propertiesUtils = new LearnyProperties();
        }
        return propertiesUtils;
    }

    public Properties getProperties() {
        return properties;
    }

    public static boolean isLoginWithoutCredentialsEnabled() {
        String value = getInstance().getProperties().getProperty(LOGIN_WITHOUT_CREDENTIALS_ENABLED);
        return Boolean.valueOf(value);
    }

    public static String getLoginWithoutCredentialsEmail() {
        return getInstance().getProperties().getProperty(LOGIN_WITHOUT_CREDENTIALS_EMAIL);
    }

    public static String getLoginWithoutCredentialsPassword() {
        return getInstance().getProperties().getProperty(LOGIN_WITHOUT_CREDENTIALS_PASSWORD);
    }

}
