package com.IanFlanagan;

import com.rollbar.api.payload.data.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rollbar.notifier.Rollbar;


import java.util.HashMap;
import java.util.Map;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

public class ApplicationTest {

    private static Rollbar rollbar;
    private static Map<String, Object> myRollbarMap = new HashMap<>();
    private static Level WARNING;
    private static Level SEVERE;
    private static Level INFO;
    private static Level CONFIG;

      /*
        TO obtain the Rollbar Access token follow these steps
        Login to Rollbar.com
        Click on settings
        click on  your project
        Project access tokens
        Copy the token next to where it says 'post_server_item'
         */

    @Test
    public void createErrorsRB() throws Exception {

        System.out.println("Starting rollbar test to generate Data");
        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

      //  Rollbar rollbar = Utils.createRBinstanceConfig(System.getenv("ROLLBAR_ACCESS_TOKEN"),System.getenv("ENVIRONMENT"),System.getenv("CODE_VERSION"));

        try
        {
            throw new RuntimeException("New Runtime exception");
        } catch (Exception e) {

            for (int i=0;i<=5; i++ ) {

                rollbar.critical(e, " Ian test: now");
                rollbar.log("Hello, Rollbar this is message number: " +i+ " in case you were wondering");
                rollbar.critical(e, " Ian test: " +i+ " now");
                rollbar.info("Theres a problem");
                rollbar.warning(e);
                rollbar.log(e,"Error log");
                rollbar.error(e);
                rollbar.critical(e,myRollbarMap, "Custom Field Test");
                rollbar.log(e,"RuntimeException");
                rollbar.log(e, myRollbarMap, "Big time issue", SEVERE, true);

            }
        } finally {

            rollbar.close(true);
        }



    }

    @Test
    public void generateData() throws Exception {

      //  Rollbar rollbar = Utils.createRBinstanceConfig(System.getenv("ROLLBAR_ACCESS_TOKEN"),System.getenv("ENVIRONMENT"),System.getenv("CODE_VERSION"));

        System.out.println("Starting rollbar test to generate Data");
        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

        try
        {
            throw new RuntimeException("New Runtime exception");
        } catch (Exception e) {

            for (int i=0;i<=5; i++ ) {

                rollbar.critical(e, " Ian test: now");
                rollbar.log("Hello, Rollbar this is message number: " +i+ " in case you were wondering");
                rollbar.critical(e, " Ian test: " +i+ " now");
                rollbar.info("Theres a problem");
                rollbar.warning(e);
                rollbar.log(e,"Error log");
                rollbar.error(e);
                rollbar.critical(e,myRollbarMap, "Custom Field Test");
                rollbar.log(e,"RuntimeException");
                rollbar.log(e, myRollbarMap, "Big time issue", SEVERE, true);

            }
        } finally {

            rollbar.close(true);
        }

    }

    @Test
    public void printName() {
        System.out.println("calling printName method test");
    }

   /* @Before
    public void setup() {

        try
        {
            System.out.println("Starting rollbar test to generate Data");
            Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                    .environment(System.getenv("ENVIRONMENT"))
                    .codeVersion(System.getenv("CODE_VERSION"))
                    .person(new MyPersonProvider())
                    .server(new ServerProvider())
                    .build());

        } catch (Exception e) {
            System.out.printf("problem with before method:  " +e.getMessage());
            e.printStackTrace();
        }
    }*/
   /* @After
    public void teardown() throws Exception {

        try
        {
            rollbar.close(true);
        } catch (Exception e) {
            System.out.printf("problem with tear down " +e.getMessage());
            e.printStackTrace();
        }
    }*/

}
