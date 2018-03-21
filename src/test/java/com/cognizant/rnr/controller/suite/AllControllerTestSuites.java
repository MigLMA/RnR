package com.cognizant.rnr.controller.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.cognizant.rnr.controller.sandbox.cors.test.CorsUnitTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({CorsUnitTest.class})
public class AllControllerTestSuites {
  // the class remains empty, used only as a holder for the above annotations
}
