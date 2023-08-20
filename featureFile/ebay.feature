Feature:
Validate the search functionality of ebay shoping app with different scenarios

Background:
Given user Launch URL in browser "https://www.ebay.com/"

   @Test1
   Scenario: Access a Product via category after applying multiple filter
   Given  user Click on Shop by category
   When  user Navigate and Click to Cell Phones & Accessories
   And user click Cell Phones & Smartphones
   And user click See All
   And user Add three filters Screen Size,Price and Item location
   Then user Verify filters tags are applied
   And  user Close the browser
   
   @Test2
   Scenario: Access a Product via Search
   Given  user Enter HP Laptop in search box "HP Laptop"
   When  user Change the search category
   And user Click on search button
   Then user Verify page load Completely
   And user Verify first result name match with the search string
   And user Close the browser 