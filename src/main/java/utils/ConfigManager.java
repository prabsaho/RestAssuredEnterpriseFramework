package utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final String DEFAULT_CONFIG_FILE = "config.properties";
    private static final String RUNTIME_CONFIG_FILE = "runtime-config.properties";
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load default config", e);
        }

        File runtimeFile = new File(RUNTIME_CONFIG_FILE);
        if (runtimeFile.exists()) {
            try (InputStream input = new FileInputStream(runtimeFile)) {
                props.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load runtime config", e);
            }
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        props.setProperty(key, value);
        try (OutputStream output = new FileOutputStream(RUNTIME_CONFIG_FILE)) {
            props.store(output, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save property", e);
        }
    }
}