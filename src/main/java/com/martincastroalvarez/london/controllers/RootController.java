package com.martincastroalvarez.london;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    // --------------------------------------------------------------------
    // Root API Resource.
    //
    // This controllers handles requests to the `/` endpoint.
    // --------------------------------------------------------------------

    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private GlobalProperties config;

	@GetMapping("/")
	public HashMap index() {
        logger.info("API | Root");
        HashMap response = new HashMap<String, String>();
        response.put("environment", config.getEnvironment());
        response.put("debug", config.getDebug().toString());
        return response;
	}

}
