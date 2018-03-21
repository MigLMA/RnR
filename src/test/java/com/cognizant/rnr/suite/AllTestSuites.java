package com.cognizant.rnr.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.cognizant.rnr.controller.suite.AllControllerTestSuites;
import com.cognizant.rnr.service.suite.AllServiceTestSuites;



@RunWith(Suite.class)
@Suite.SuiteClasses({AllControllerTestSuites.class, AllServiceTestSuites.class})
public class AllTestSuites {
  // the class remains empty, used only as a holder for the above annotations
}
