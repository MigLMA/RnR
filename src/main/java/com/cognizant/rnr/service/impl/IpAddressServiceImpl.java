package com.cognizant.rnr.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.stereotype.Service;
import com.cognizant.rnr.service.IpAddressService;

/**
 * This is the implementation class for service.
 * 
 * @author 628700
 * @since 0.0.0
 */

@Service
public class IpAddressServiceImpl implements IpAddressService {

  @Override
  public String getServerAddress() throws UnknownHostException {

    return InetAddress.getLocalHost().getHostAddress();

  }

}
