Feature: User service

  Scenario: Users can be retrieved from the database:
    Given a running environment
    When I make a request for all users
    Then the following users should be returned:
      | firstName | lastName | email                   | phoneNumber | jobTitle  | addressLine1        | addressLine2 | town          | county     | postCode | country |
      | Joffa     | Smith    | jonothan.smith@test.com | 212 555555  | Legend    | 545 57th Street     |              | Manhattan     | N/A        | EX2 8BR  | England |
      | Clive     | Townsend | clive.townsend@test.com | 315 555555  | Ninja     | 192 Broadway        |              | Manchester    | Manchester | EX2 9SD  | England |
      | Matthew   | Smith    | matthew.smith@test.com  | 347 555555  | Miner     | 199 Madison Avenue  |              | Exeter        | Devon      | EX1 4HJ  | England |
      | Chris     | Stamper  | chris.stamper@test.com  | 917 555555  | Swordsman | 66 Lexington Avenue |              | Newton Abbott | Devon      | TA12 4BS | England |

  Scenario: Users can be persisted to the database:
    Given a running environment
    And I make requests to create the following users:
      | firstName | lastName | email                    | phoneNumber | jobTitle                  | addressLine1               | addressLine2 | town    | county | postCode | country |
      | Bill      | Gates    | bill.gates@microsoft.com | 100 555 555 | Technologu Advisor for MS | Ridiculously huge mnansion |              | Seattle | N/A    | 98101    | USA     |
    When I make a request for all users
    Then the following users should be included:
      | firstName | lastName | email                    | phoneNumber | jobTitle                  | addressLine1               | addressLine2 | town    | county | postCode | country |
      | Bill      | Gates    | bill.gates@microsoft.com | 100 555 555 | Technologu Advisor for MS | Ridiculously huge mnansion |              | Seattle | N/A    | 98101    | USA     |

  Scenario: Users can be removed from the database:
    Given a running environment
    And I make a request for all users
    And the following users should be returned:
      | firstName | lastName | email                   | phoneNumber | jobTitle  | addressLine1        | addressLine2 | town          | county     | postCode | country |
      | Joffa     | Smith    | jonothan.smith@test.com | 212 555555  | Legend    | 545 57th Street     |              | Manhattan     | N/A        | EX2 8BR  | England |
      | Clive     | Townsend | clive.townsend@test.com | 315 555555  | Ninja     | 192 Broadway        |              | Manchester    | Manchester | EX2 9SD  | England |
      | Matthew   | Smith    | matthew.smith@test.com  | 347 555555  | Miner     | 199 Madison Avenue  |              | Exeter        | Devon      | EX1 4HJ  | England |
      | Chris     | Stamper  | chris.stamper@test.com  | 917 555555  | Swordsman | 66 Lexington Avenue |              | Newton Abbott | Devon      | TA12 4BS | England |
    And I make requests to delete the following users:
      | firstName | lastName | email                  | phoneNumber | jobTitle  | addressLine1        | addressLine2 | town          | county | postCode | country |
      | Chris     | Stamper  | chris.stamper@test.com | 917 555555  | Swordsman | 66 Lexington Avenue |              | Newton Abbott | Devon  | TA12 4BS | England |
    When I make a request for all users
    Then the following users should be returned:
      | firstName | lastName | email                   | phoneNumber | jobTitle | addressLine1       | addressLine2 | town       | county     | postCode | country |
      | Joffa     | Smith    | jonothan.smith@test.com | 212 555555  | Legend   | 545 57th Street    |              | Manhattan  | N/A        | EX2 8BR  | England |
      | Clive     | Townsend | clive.townsend@test.com | 315 555555  | Ninja    | 192 Broadway       |              | Manchester | Manchester | EX2 9SD  | England |
      | Matthew   | Smith    | matthew.smith@test.com  | 347 555555  | Miner    | 199 Madison Avenue |              | Exeter     | Devon      | EX1 4HJ  | England |

  Scenario: Users can be updated:
    Given a running environment
    And I make requests to create the following users:
      | firstName | lastName | email                      | phoneNumber | jobTitle | addressLine1               | addressLine2 | town    | county | postCode | country |
      | Alan      | Sugar    | ams@youAreHavingALaugh.com | 100 555 555 | Lord     | Ridiculously huge mnansion |              | Hackney | N/A    | LE5 NP2  | UK      |
    And I make a request to update user with id "ams@youAreHavingALaugh.com" as follows:
      | firstName | lastName | email                      | phoneNumber | jobTitle          | addressLine1               | addressLine2 | town    | county | postCode | country |
      | Alan      | Sugar    | ams@youAreHavingALaugh.com | 100 555 555 | Starbucks Barista | Ridiculously huge mnansion |              | Hackney | N/A    | LE5 NP2  | UK      |
    When I make a request for all users
    Then the following users should be included:
      | firstName | lastName | email                      | phoneNumber | jobTitle          | addressLine1               | addressLine2 | town    | county | postCode | country |
      | Alan      | Sugar    | ams@youAreHavingALaugh.com | 100 555 555 | Starbucks Barista | Ridiculously huge mnansion |              | Hackney | N/A    | LE5 NP2  | UK      |