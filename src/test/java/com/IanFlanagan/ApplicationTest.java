package com.IanFlanagan;

import com.rollbar.api.payload.data.Level;
import org.junit.Test;
import com.rollbar.notifier.Rollbar;


import java.util.HashMap;
import java.util.Map;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

public class ApplicationTest {

    private Rollbar rollbar;
    private static Map<String, Object> myRollbarMap = new HashMap<>();
    private static Level WARNING;
    private static Level SEVERE;
    private static Level INFO;
    private static Level CONFIG;

    @Test
    public void generateDataRB() throws Exception {

        System.out.println("Starting rollbar test to generate Data");

        /*
        TO obtain the Rollbar Access token follow these steps
        Login to Rollbar.com
        Click on settings
        click on  your project
        Project access tokens
        Copy the token next to where it says 'post_server_item'
         */

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

        try
        {
            throw new RuntimeException("Major Issue");
        } catch (Exception e) {

            for (int i=0;i<=50; i++ ) {

                rollbar.log("Hello, Rollbar this is message number: " +i+ " in case you were wondering");
                rollbar.critical(e, " Critical error: " +i+ " now");
                rollbar.info("Theres a problem");
                rollbar.warning(e);
                rollbar.log(e,"Error log");
                rollbar.error(e);
                rollbar.critical(e,myRollbarMap, "Custom Field Test");
                rollbar.log(e,"RuntimeException");
                rollbar.log(e, myRollbarMap, "Big time issue", SEVERE, true);

            //    rollbar.error();


            }
        } finally {

            rollbar.close(true);
        }
     //   rollbar.log("Hello, Rollbar");

       // Application.GenerateErrors();


       /*
        Application app = new Application();
        app.execute();*/

    }

    @Test
    public void printName() {
        System.out.println("calling printName method test");
    }

}
