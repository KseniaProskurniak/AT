package test.java.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import redmine.managers.Context;
import redmine.managers.Manager;

public class Hooks {

    @After
    public void afterAll(Scenario scenario) {
        Context.saveStashToAllure();
        Manager.driverQuit();
    }

}
