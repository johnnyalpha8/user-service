package com.tyrell.replicant.user.service.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private static User underTest = new User();

    @Test
    public void testGetFirstName() {
        // given
        String expected = "Chris";
        underTest.setFirstName(expected);

        // when
        String actual = underTest.getFirstName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLastName() {
        // given
        String expected = "Curry";
        underTest.setLastName(expected);

        // when
        String actual = underTest.getLastName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEmail() {
        // given
        String expected = "ChrisCurry@sinclair-radionics.com";
        underTest.setLastName(expected);

        // when
        String actual = underTest.getLastName();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhoneNumber() {
        // given
        String expected = "+1 555 555";
        underTest.setPhoneNumber(expected);

        // when
        String actual = underTest.getPhoneNumber();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetJobTitle() {
        // given
        String expected = "inventor/entrepreneur";
        underTest.setJobTitle(expected);

        // when
        String actual = underTest.getJobTitle();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAddressLine1() {
        // given
        String expected = "Behind a dumpster";
        underTest.setAddressLine1(expected);

        // when
        String actual = underTest.getAddressLine1();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAddressLine2() {
        // given
        String expected = "outside the crab restaurant";
        underTest.setAddressLine2(expected);

        // when
        String actual = underTest.getAddressLine2();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTown() {
        // given
        String expected = "Beer";
        underTest.setTown(expected);

        // when
        String actual = underTest.getTown();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCounty() {
        // given
        String expected = "Devon";
        underTest.setCounty(expected);

        // when
        String actual = underTest.getCounty();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPostCode() {
        // given
        String expected = "ABC 123";
        underTest.setPostCode(expected);

        // when
        String actual = underTest.getPostCode();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCountry() {
        // given
        String expected = "UK";
        underTest.setCountry(expected);

        // when
        String actual = underTest.getCountry();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testEquals_ShouldBeTrue() {
        // given
        String csvFile = "src/test/resources/test.csv";
        User user1 = new User();
        User user2 = new User();
        String email = "princess-sparkly-rainbow-unicorn@gmail.com";

        // and
        user1.setEmail(email);
        user2.setEmail(email);

        // when
        boolean shouldBeTrue = user1.equals(user2);

        // then
        assertTrue(shouldBeTrue);
    }

    @Test
    public void testEquals_ShouldBeFalse() {
        // given
        String csvFile = "src/test/resources/test.csv";
        User user1 = new User();
        User user2 = new User();
        String email = "princess-sparkly-rainbow-unicorn@gmail.com";
        String completelyDifferentEmail = "nigel-farage@brussels.eu.gov";

        // and
        user1.setEmail(email);
        user2.setEmail(completelyDifferentEmail);

        // when
        boolean shouldBeFalse = user1.equals(user2);

        // then
        assertFalse(shouldBeFalse);
    }

    @Test
    public void testHashcode_WhenObjectsAreTheSame() {
        // given
        String csvFile = "src/test/resources/test.csv";
        User user1 = new User();
        User user2 = new User();
        String email = "princess-sparkly-rainbow-unicorn@gmail.com";

        // and
        user1.setEmail(email);
        user2.setEmail(email);

        // when
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        // then
        assertTrue(hashcode1 == hashcode2);
    }

    @Test
    public void testHashcode_WhenObjectsAreDifferent() {
        // given
        String csvFile = "src/test/resources/test.csv";
        User user1 = new User();
        User user2 = new User();
        String email = "princess-sparkly-rainbow-unicorn@gmail.com";
        String completelyDifferentEmail = "nigel-farage@brussels.eu.gov";

        // and
        user1.setEmail(email);
        user2.setEmail(completelyDifferentEmail);

        // when
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        // then
        assertFalse(hashcode1 == hashcode2);
    }
}