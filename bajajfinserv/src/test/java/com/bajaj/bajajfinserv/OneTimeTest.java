package com.bajaj.bajajfinserv;

import com.bajaj.bajajfinserv.service.WebhookService;
import org.junit.jupiter.api.Test;

public class OneTimeTest {

    @Test
    public void runFlowOnce() {
        WebhookService svc = new WebhookService();
        svc.executeFlow();
    }

}
