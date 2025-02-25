package seedu.anilist.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void getLogLevel_nonNull_getNonNullLogLevel() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig.getLogLevel());
    }

    @Test
    public void getUserPrefsFilePath_nonNull_getNonNullFilePath() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig.getUserPrefsFilePath());
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        Config otherDefaultConfig = new Config();

        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
        assertTrue(defaultConfig.equals(otherDefaultConfig));
        assertFalse(defaultConfig.equals(null));
    }


}
