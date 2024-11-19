Feature: Advanced Search

  @Demo
  Scenario: To create a New Business to verify advanced search
    Given Load Testdata file for test "Regression - AdvancedSearch" from file "AdvancedSearch"
    When I login as MidCorp Package Account Technician
    And I create a new submission and complete details in Additional Information, Upload Information, add Other Named Insured and complete Contact Information
    And I store case id into Testdata file for test "Regression - AdvancedSearch" from file "AdvancedSearch"
    And I search the policy in inprogress status


