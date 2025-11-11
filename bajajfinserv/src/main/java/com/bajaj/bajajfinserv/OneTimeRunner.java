package com.bajaj.bajajfinserv;

import com.bajaj.bajajfinserv.service.WebhookService;

public class OneTimeRunner {

    public static void main(String[] args) {
        WebhookService svc = new WebhookService();
        svc.executeFlow();
        System.out.println("OneTimeRunner finished.");
        // exit explicitly
        System.exit(0);
    }

}
