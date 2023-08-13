package com.inatel.gamemanager.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class TimeFormatterUtilTest {

    @Test
    @DisplayName("Test correct format")
    public void testValidISODateFormat() {
        assertTrue(TimeFormatterUtil.isISODateFormat("2023-08-12"));
    }

    @Test
    @DisplayName("Test nearly correct format")
    public void testInvalidISODateFormat() {
        assertFalse(TimeFormatterUtil.isISODateFormat("2023/08/12"));
    }

    @Test
    @DisplayName("Test empty string")
    public void testEmptyInput() {
        assertFalse(TimeFormatterUtil.isISODateFormat(""));
    }
}
