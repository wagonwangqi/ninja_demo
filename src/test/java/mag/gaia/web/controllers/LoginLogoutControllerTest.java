
package mag.gaia.web.controllers;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import ninja.NinjaTest;

import org.junit.Test;

import com.google.common.collect.Maps;

public class LoginLogoutControllerTest extends NinjaTest {

    @Test
    public void testLogingLogout() {

        Map<String, String> headers = Maps.newHashMap();

        String response = ninjaTestBrowser.makeRequest(getServerAddress()
                + "article/new", headers);
        System.out.println(response);
        assertTrue(response.contains("Error. Forbidden."));


        response = ninjaTestBrowser.makeRequest(getServerAddress()
                + "article/new", headers);
        System.out.println(response);
        assertTrue(response.contains("Error. Forbidden."));

    }

}
