package com.IanFlanagan;

import com.rollbar.api.payload.data.Person;
import com.rollbar.notifier.provider.Provider;

import java.util.Random;

public class MyPersonProvider implements Provider<Person> {

   // @Override
    public Person provide() {

        int max = 2;
        int min = 0;

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
     //   System.out.println(randomNum);

        Person command = null;

        if (randomNum == 0) {

            command = new Person.Builder().id(MyConfiguration.id).email(MyConfiguration.email).username(MyConfiguration.username).build();

        } else if (randomNum == 1) {

            command = new Person.Builder().id(MyConfiguration.idfb).email(MyConfiguration.emailfb).username(MyConfiguration.usernamefb).build();

        } else if (randomNum == 2) {

            command = new Person.Builder().id(MyConfiguration.idiv).email(MyConfiguration.emailiv).username(MyConfiguration.usernameiv).build();
        }
          return command;
     //   return new Person.Builder().id(MyConfiguration.id).email(MyConfiguration.email).username(MyConfiguration.username).build();

    }
}