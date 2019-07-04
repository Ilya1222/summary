package ua.nure.shevchenko.provider.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class PasswordHashTest {

    @Test
    public void hash() {
        String expected = "BC1A4CB58877DA14029185360C59CF6F56FF18EA0BF2931A86C95A86436C1691BA6E133CC68EB57333F17CB43B666F37EC78A5DD541FE331E3E29D16A651D73C";

        String actual = null;

        try {
             actual = PasswordHash.hash("client1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expected, actual);
    }
}