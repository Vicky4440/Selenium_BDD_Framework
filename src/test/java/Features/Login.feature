@Login
Feature: Application Login

Scenario: Login to the application and verifying home page


Given Launch the application on browser
And login with 'valid' username and password
Then verify the title of the webpage
And verify the home page


