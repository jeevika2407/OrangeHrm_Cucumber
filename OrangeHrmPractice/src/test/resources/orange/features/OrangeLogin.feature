Feature: OrangeHRM Login and Quick Launch Options

  Background:
    Given User is on the login page

  Scenario: User logs into OrangeHRM
    When User enters username "Admin"
    And User enters password "admin123"
    And User clicks on the login button
    Then User should see "Dashboard"

  Scenario Outline: Verify Quick Launch options on Dashboard
    Given User is logged into OrangeHRM
    When User checks for the quick launch option "<option>"
    Then The quick launch option "<option>" should be displayed

    Examples:
      | option|
      | Assign Leave|
      | Leave List|
      | Timesheets|
      | Apply Leave|
      | My Leave|
      | My Timesheet|
