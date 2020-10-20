package com.IanFlanagan;

import com.rollbar.api.payload.data.Level;
import com.rollbar.notifier.provider.Provider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rollbar.notifier.Rollbar;
import org.junit.runners.Parameterized;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.*;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

public class ApplicationTest {

    private static Rollbar rollbar;
    private static HashMap<String,Object> myRollbarMap = new HashMap<>();
    private static Level WARNING;
    private static Level SEVERE;
    private static Level INFO;
    private static Level CONFIG;
    private static Object test_id;
    private static Object server_id;

      /*
        TO obtain the Rollbar Access token follow these steps
        Login to Rollbar.com
        Click on settings
        click on  your project
        Project access tokens
        Copy the token next to where it says 'post_server_item'
         */


    @Test
    public void createErrorsAccessException() throws UnknownHostException {

        String hostname = Utils.GetHostName();

        /*
        myRollbarMap.put("test_id", test_id);
        myRollbarMap.put("test_server",String.format("test_server_%d", server_id));
        */

        myRollbarMap.put(hostname, test_id);
        myRollbarMap.put("test_server",String.format("test_server_%d", server_id));

        System.out.println("Starting rollbar test to call createMoreErrors() method");

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .language("Java")
                .framework("Junit")
                .platform("MacOS")
                .handleUncaughtErrors(true)
                .build());

        try
        {
            throw new IllegalAccessException("Illegal Access Exception");

        } catch (Exception e) {

            for (int i=0; i<=100; i++) {

                rollbar.critical(e,"createErrorsAccessException() " +i+  " Illegal Access exception");
                rollbar.critical(e,myRollbarMap);
            }
        }
    }

    @Test
    public void createFileNotFoundErrors() {

        System.out.println("Starting rollbar test to call createMoreErrors() method");

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

        try
        {
            throw new FileNotFoundException("New File not found exception");

        } catch (Exception e) {

            for (int i=0; i<=10; i++) {

                rollbar.critical(e,"FileNotFoundException() " +i+  " File not Found exception");
            }


        }
    }

    @Test
    public void createMoreSQLExErrors() {

        System.out.println("Starting rollbar test to call createMoreErrors() method");

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

        try
        {
            throw new SQLException("New SQL exception");

        } catch (Exception e) {

            for (int i=0; i<=5; i++) {

                rollbar.critical(e,"createMoreSQLExErrors() " +i+  " SQL exception");
            }


        }
    }

    @Test
    public void createMoreIOErrors() {

        System.out.println("Starting rollbar test to call createMoreErrors() method");

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .build());

        try
        {
            throw new IOException("New IO exception");

        } catch (Exception e) {

            for (int i=0; i<= 200; i++) {

                rollbar.critical(e,"createMoreIOErrors() " +i+  " IO exception");
            }


        }
    }

    @Test
    public void createMoreErrors() {

        System.out.println("Starting rollbar test to call createMoreErrors() method");

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

            rollbar.critical(e,"createMoreErrors() runtime exception");

        }
    }

    @Test
    public void createErrorsRB() throws Exception {

        System.out.println("Starting rollbar test to call creatErrorsRB() method");

        Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                .environment(System.getenv("ENVIRONMENT"))
                .codeVersion(System.getenv("CODE_VERSION"))
                .person(new MyPersonProvider())
                .server(new ServerProvider())
                .framework("JUnit")
                .language("Java")
                .platform("MacOS")
             //   .custom((Provider<Map<String, Object>>) myRollbarMap)
                .build());

      //  Rollbar rollbar = Utils.createRBinstanceConfig(System.getenv("ROLLBAR_ACCESS_TOKEN"),System.getenv("ENVIRONMENT"),System.getenv("CODE_VERSION"));

        try
        {
            throw new RuntimeException("New Runtime exception");
        } catch (Exception e) {

            for (int i=0;i<=10; i++ ) {

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

        System.out.println("Starting rollbar test to generateData() test method");

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
            System.out.println("Calling before setup() method to create rollbar instance");
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

        System.out.println("Rollbar instance created");

    }
    @After
    public void teardown()  {

        try
        {
            System.out.println("Closing rollbar instance");
            rollbar.close(true);
        } catch (Exception e) {
            System.out.printf("problem with calling teardown() after method " +e.getMessage());
            e.printStackTrace();
        }
    }
*/
}
