package com.bvk.springjwt.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EDR {
  private static final Logger log = LogManager.getLogger("SALVO_Logger");

  public static void record(String msg) { log.info(msg); }
}
