package com.cepheid.dx.automation.core;

public class ApplicationFunctions extends Locators
{

  /*
   * Logs user into the Cepheid admin regardless if logged in or not
   */

  public void userLogin ()
  {

    if (exists(ADVANCED_SETUP_TAB)) {
      findJMenuItem(LOGOUT_BTN).click();
    }

    findJMenuItem(LOGIN_BTN).click();

    if (verifyLabel(getText(USER_NAME_LABEL), "User Name")) {
      findJTextField(USER_NAME_BOX).click();
      desktop.typeKeys("cepheid", 300);
      if (verifyLabel(getText(PASSWORD_LABEL), "Password")) {
        findPasswordField(PASSWORD_BOX).click();
        desktop.typeKeys("288952314", 300);
      }
      findJButton(LOGIN_OK_BTN).select();
    }

    waitForObject(TA_WND);
    if (exists(TA_NO_BTN)) {
      findJButton(TA_NO_BTN).click();
    }

  }

  // Returns a JLabel Text field
  public String getText (String text)
  {
    return desktop.find(locator(text)).getText();
  }

  // Navigates to the create test button
  public void navigateToCreateTest ()
  {
    clickHeader(CREATE_TEST_BTN);
  }

  // Navigates to the check status button
  public void navigateToCheckStatus ()
  {
    clickHeader(CHECK_STATUS_BTN);
  }

  // Navigates to the stop test button
  public void navigateToStopTest ()
  {
    clickHeader(STOP_TEST_BTN);
  }

  // Navigates to the view results button
  public void navigateToViewResults ()
  {
    clickHeader(VIEW_RESULTS_BTN);
  }

  // Navigate to the define assays button
  public void navigateToDefineAssays ()
  {
    clickHeader(DEFINE_ASSAYS_BTN);
  }

  // Navigates to the define graphs
  public void navigateToDefineGraphs ()
  {
    clickHeader(DEFINE_GRAPHS_BTN);
  }

  /*
   * 
   * Navigates to the maintenance tab
   */
  public void navigateToMaintenance ()
  {
    clickHeader(MAINTENANCE_BTN);
  }

  /*
   * 
   * Clicks on the selected tab based off an integer
   */
  public void clickTabbedPane (String locator, String tab)
  {

    findJTabbedPane(locator).select(tab);

  }

  // Verifies the JLabel with an expected text
  private boolean verifyLabel (String expected, String actual)
  {
    return expected.equals(actual);
  }

}
