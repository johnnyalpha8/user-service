package com.tyrell.replicant.user.service.utils;

import com.tyrell.replicant.user.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.tyrell.replicant.user.service.utils.InitialDataLoaderUtils.convertCSVFileToUserList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitialDataLoaderUtilsTest {

    @Value("${initial.user.data}")
    private String csvFile;

    @Test
    public void testConvertCSVFileToUserList() throws IOException {
        // given
        User expected = new User();
        String expectedFirstName = "Donald";
        String expectedLastName = "Trump";
        String expectedEmail = "princess-sparkly-rainbow-unicorn@gmail.com";

        // and
        expected.setFirstName(expectedFirstName);
        expected.setLastName(expectedLastName);
        expected.setEmail(expectedEmail);

        // when
        User actual = convertCSVFileToUserList(csvFile).get(0);

        // then
        String actualFirstName = actual.getFirstName();
        String actualLastName = actual.getLastName();
        String actualEmail = actual.getEmail();

        assertEquals(expectedFirstName, actualFirstName);
        assertEquals(expectedLastName, actualLastName);
        assertEquals(expectedEmail, actualEmail);
    }
}