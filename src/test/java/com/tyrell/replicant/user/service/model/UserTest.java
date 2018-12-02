package com.tyrell.replicant.user.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private static User underTest = new User();

    @Test
    public void testGetFirstName() {
        // given
        String expected = "Chris";
        underTest.setSomeField(expected);

        // when
        String actual = underTest.getSomeField();

        // then
        assertEquals(expected, actual);
    }

}