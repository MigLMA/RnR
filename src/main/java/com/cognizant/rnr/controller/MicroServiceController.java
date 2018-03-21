package com.cognizant.rnr.controller;

import java.net.UnknownHostException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.rnr.service.IpAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * This is the controller class with the endpoints of a CRUD.
 *
 * @author 628700
 * @since 0.0.0
 */

@RestController
@RequestMapping("/api")
@Api(value = "R&R microservice controller",
    description = "This API provides functionality for Rewards & Recognition")
public class MicroServiceController {

  private static final Log log = LogFactory.getLog(MicroServiceController.class);

  @Autowired
  private IpAddressService ipAddressService;


  /**
   * Get server ip
   *
   * @return a ResponseEntity<String>
   *
   * @since 0.0.0
   */
  @RequestMapping(method = RequestMethod.GET, value = "/ip")
  @ApiOperation(value = "Get server address.",
      notes = "Return ip address where the server is running from.")
  public ResponseEntity<String> getServerAddress() {
    log.info("Get server address.");
    String serverAddress = "";
    try {
      serverAddress = this.ipAddressService.getServerAddress();
    } catch (UnknownHostException e) {
      serverAddress = "<HOST IP NOT FOUND>";
    }
    String ipAddress = new StringBuilder().append("Running from IP address: ").append(serverAddress)
        .append("\n").toString();
    return ResponseEntity.ok(ipAddress);
  }



}
