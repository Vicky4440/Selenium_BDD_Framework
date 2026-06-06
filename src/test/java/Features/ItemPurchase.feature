@Itempurchase
Feature: Placing the order
Scenario: Login to the application and verifying home page


Given Launch the application on browser
And login with 'valid' username and password
Then verify the title of the webpage
And verify the home page

Scenario: selecting items to the cart
When navigate to the home page
Then click on 'Add to cart' button for the selected items
And click on 'cart' icon
And verify the navigation to the 'Your Cart' page
And verify the selected items in the cart
And click on 'Checkout' button

Scenario: Address adding and checkout
Then enter 'First Name' into the field
And enter 'Last Name' into the field
And enter 'Zip/Postal Code' into the field
When click on 'Continue' button

Scenario: Verifying checkout overview page
Then verify the product name with 'Description'
And verify the 'cost' for the product
And verify the'payment' details
#And verify the 'shipping' details
#And verify the 'Total Price' based on products
#And verify the display of 'Finish' button
#And verify the display of 'Cancel' button

#Scenario: Placing order
#When click on 'Finish' button
#Then verify the checkout-complete page
#And verify the display of success message
#And verify the display of 'Back Home' button
#And verify the navigation into the home page 
#
  