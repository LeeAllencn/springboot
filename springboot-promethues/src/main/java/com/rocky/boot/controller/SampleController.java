package com.rocky.boot.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by Rocky on 2017-12-08.
 */
@RestController
public class SampleController {

    // Counter
    static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();
    static final Counter failedRequests = Counter.build()
            .name("requests_failed_total").help("Total failed requests.").register();

    public void processRequest() {
        requests.inc();
        try {
            // Your code here.
        } catch (Exception e) {
            failedRequests.inc();
            throw e;
        }
    }

    // Gauge
//    static final Gauge inprogressRequests = Gauge.build()
//            .name("inprogress_requests").help("Inprogress requests.").register();
//
//    void processRequest() {
//        inprogressRequests.inc();
//        // Your code here.
//        inprogressRequests.dec();
//    }

    // Histogram
//    static final Histogram requestLatency = Histogram.build()
//            .name("requests_latency_seconds").help("Request latency in seconds.").register();
//
//    void processRequest(/*Request req*/) {
//        Histogram.Timer requestTimer = requestLatency.startTimer();
//        try {
//            // Your code here.
//        } finally {
//            requestTimer.observeDuration();
//        }
//    }

    // Summary
//    static final Summary receivedBytes = Summary.build()
//            .name("requests_size_bytes").help("Request size in bytes.").register();
//    static final Summary requestLatency2 = Summary.build()
//            .name("requests_latency_seconds").help("Request latency in seconds.").register();
//
//    void processRequest(Request req) {
//        Summary.Timer requestTimer = requestLatency2.startTimer();
//        try {
//            // Your code here.
//        } finally {
//            receivedBytes.observe(req.size());
//            requestTimer.observeDuration();
//        }
//    }




    // ===============================================================

//    private static Random random = new Random();
//
//
//    private static final Counter requestTotal = Counter.build()
//            .name("my_sample_counter")
//            .labelNames("status")
//            .help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus")
//            .register();
//
//    private static final Histogram requestLatency1 = Histogram.build()
//            .name("lee_requests_latency_seconds")
//            .help("lee Request latency in seconds.")
//            .register();
//
//    @RequestMapping("/endpoint")
//    public void endpoint() {
//        int flag = random.nextInt(2);
//        if (flag > 0) {
//            requestTotal.labels("success").inc();
//        } else {
//            requestTotal.labels("error").inc();
//        }
//    }
//
//    @RequestMapping("/histogramsimple")
//    public String histogramsimple() {
//        // Start the histogram timer
//        Histogram.Timer requestTimer = requestLatency1.startTimer();
//        try {
//            return "Hello Spring Boot World!";
//        } finally {
//            // Stop the histogram timer.
//            requestTimer.observeDuration();
//        }
//    }
}
