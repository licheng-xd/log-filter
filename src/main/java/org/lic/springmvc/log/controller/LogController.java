package org.lic.springmvc.log.controller;

import org.lic.springmvc.log.meta.ResponseCode;
import org.lic.springmvc.log.meta.ResponseEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController {
    private static final Logger logger = LoggerFactory
        .getLogger(LogController.class);

    @ResponseBody
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    private ResponseEntry receive(@RequestBody String jsonBody) {

        logger.info("receive jsonBody={}", jsonBody);
        return new ResponseEntry(ResponseCode.RES_OK, "ok");
    }

}
