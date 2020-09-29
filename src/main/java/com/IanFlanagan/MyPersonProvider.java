package com.IanFlanagan;

import com.rollbar.api.payload.data.Person;
import com.rollbar.notifier.provider.Provider;

public class MyPersonProvider implements Provider<Person> {

   // @Override
    public Person provide() {
      //  return new Person.Builder().id("84935784903").email("ian.flanagan@rollbar.com").username("ianianf").build();
        return new Person.Builder().id(MyConfiguration.id).email(MyConfiguration.email).username(MyConfiguration.username).build();
    }
}