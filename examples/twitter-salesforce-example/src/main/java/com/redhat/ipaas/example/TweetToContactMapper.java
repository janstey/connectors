/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.ipaas.example;

import com.redhat.ipaas.connector.salesforce.Contact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.User;

/**
 * To transform a tweet {@link Status} object into a salesforce {@link Contact} object.
 */
@Component
public class TweetToContactMapper implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();

        Status status = exchange.getIn().getBody(Status.class);

        User user = status.getUser();
        String name = user.getName();
        String screenName = user.getScreenName();

        Contact contact = new Contact();
        contact.setLastName(name);
        contact.setTwitterScreenName__c(screenName);

        in.setBody(contact);
    }

}
