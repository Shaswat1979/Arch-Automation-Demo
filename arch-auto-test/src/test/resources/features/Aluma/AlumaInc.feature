Feature: Aluma New Business

  @Sample
  Scenario: To create a New Business package policy for MidCorp LOB having products Property, General Liability
    Given Load Testdata file for test "Aluma Form Inc" from file "AlumaFormInc"
    When I login as MidCorp Package Account Technician
    And I create a new submission and complete details in Additional Information, Payment History Upload Information and Contact Information
    And I complete the details in UnderwritingChecks
    And I verify Address Doctor and GeoHazard interfaces
    And I complete the details of GL Operations stage by uploading classcode file
    And I add Employee Benefits Administration E&O coverage for product General Liability
    And I complete the actions in Loss Details
    And I move to Pricing stage, refresh class description at GL Operations and verify Core Logic Interface
    And I open Property quote and add Base coverage and Deductibles
    And I add Cat Perils Earth Movement, Flood, Wind Deductibles, NewlyAccuredLocationCoverage, UnamedLocationCoverage
    And I add Terrorism coverage
    And I Click on Extension Tab for the Property Product
    And I add the details for Mortgage Holder or Loss Payee provisions
    And I Click on Endorsements tab
    And I am not selecting the recommended endorsements
    And I Click on Load Forms
    And I fill details for endorsement form 250031
    And I add Equipment Breakdown and navigate to Reinsurance Summary
    And I select the Reinsurer for Equipment Breakdown at Reinsurance Summary and navigate to next stage
    And I run Risk Models and Sub Peril Risk Models
    And I rate Property product and verify Insbridge interface
    And I Complete the GL Operations for the GL Product
    And I complete the details in UW Rationale and Quote stages
    And I verify generation of Reinsurance policy numbers, Binder document, External Rating Worksheet and verify Thunderhead interface at Bind Stage
    And I generate Draft Policy and Book Policy at Handover, stage
    Then I verify whether the policy is Booked

