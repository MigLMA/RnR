package com.cognizant.rnr.service;

import java.net.UnknownHostException;

/**
 * This  is the service class.
 * @author 628700
 * @since 0.0.0
 */
public interface IpAddressService {

    String getServerAddress() throws UnknownHostException;

}
