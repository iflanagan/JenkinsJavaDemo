package com.IanFlanagan;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

public class Utils {

    private static Rollbar rollbar;
    private static String accessToken;
    private static String env;
    private static String version;


    public static Long GetTimestamp() {

        long mytimeStamp = 0L;
        String timeStamp = null;

        try
        {
            mytimeStamp = Long.parseLong(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

        } catch (Exception e)  {
            System.out.println("Can't execute GetTimestamp() method:" +e.getMessage());
            e.printStackTrace();
        }

        return mytimeStamp;
    }

    public static String GetHostName() {

        String myhost = null;

        try
        {
            InetAddress id = InetAddress.getLocalHost();
            String myHost = id.getHostName();
            System.out.println(myHost);

        } catch (UnknownHostException e) {
            System.out.println("Can't get hostname: " +e.getMessage());

            e.printStackTrace();
        }

        return myhost;
    }

    public static Rollbar createRBinstanceConfig(String accessToken, String environment, String version) {


        if (accessToken == null) {
            System.out.printf("Please enter a valid rollbar Access Token");
            return null;
        }

        try
        {
            Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                    .environment(System.getenv("ENVIRONMENT"))
                    .codeVersion(System.getenv("CODE_VERSION"))
                    .person(new MyPersonProvider())
                    .server(new ServerProvider())
                    .build());

        } catch (Exception e) {

            System.out.printf("can't create rollbar instance: " +e.getMessage());
            e.printStackTrace();
            rollbar = null;
        }
        return rollbar;
    }
}
