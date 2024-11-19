package arch.auto.steps;

import arch.auto.pageObjects.arch.HomePage;
import arch.auto.pageObjects.arch.LoginPage;
import arch.auto.pageObjects.arch.SearchPage;
import arch.auto.utils.helper.ExcelHelper;
import arch.auto.utils.selenium.PageObjectUtil;
import com.amazonaws.services.kendra.model.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

public class CommonSteps extends PageObjectUtil {

    private LoginPage loginPage = new LoginPage();
    private HomePage homepage = new HomePage();
    private SearchPage searchPage = new SearchPage();

    private Map<String, String> testData;

    @Given("Load Testdata file for test {string} from file {string}")
    public void loadTestdataFileForTestFromFile(String testName, String fileName) throws IOException {
        testData = ExcelHelper.getTestData(testName, fileName);
    }

    @When("I login as MidCorp Package Account Technician")
    public void iLoginAsMidCorpPackageAccountTechnician() {
        launchApp("ARCH");
        loginPage.login(testData.get("USERNAME"), testData.get("PASSWORD"));
    }

    @And("I create a new submission and complete details in Additional Information, Payment History Upload Information and Contact Information")
    public void iCreateANewSubmissionAndCompleteDetailsInAdditionalInformationPaymentHistoryUploadInformationAndContactInformation() {
        
    }

    @And("I complete the details in UnderwritingChecks")
    public void iCompleteTheDetailsInUnderwritingChecks() {
        
    }

    @And("I verify Address Doctor and GeoHazard interfaces")
    public void iVerifyAddressDoctorAndGeoHazardInterfaces() {
        
    }

    @And("I complete the details of GL Operations stage by uploading classcode file")
    public void iCompleteTheDetailsOfGLOperationsStageByUploadingClasscodeFile() {
        
    }

    @And("I add Employee Benefits Administration E&O coverage for product General Liability")
    public void iAddEmployeeBenefitsAdministrationEOCoverageForProductGeneralLiability() {
        
    }

    @And("I complete the actions in Loss Details")
    public void iCompleteTheActionsInLossDetails() {
        
    }

    @And("I move to Pricing stage, refresh class description at GL Operations and verify Core Logic Interface")
    public void iMoveToPricingStageRefreshClassDescriptionAtGLOperationsAndVerifyCoreLogicInterface() {
        
    }

    @And("I open Property quote and add Base coverage and Deductibles")
    public void iOpenPropertyQuoteAndAddBaseCoverageAndDeductibles() {
        
    }

    @And("I add Cat Perils Earth Movement, Flood, Wind Deductibles, NewlyAccuredLocationCoverage, UnamedLocationCoverage")
    public void iAddCatPerilsEarthMovementFloodWindDeductiblesNewlyAccuredLocationCoverageUnamedLocationCoverage() {
        
    }

    @And("I add Terrorism coverage")
    public void iAddTerrorismCoverage() {
        
    }

    @And("I Click on Extension Tab for the Property Product")
    public void iClickOnExtensionTabForThePropertyProduct() {
        
    }

    @And("I add the details for Mortgage Holder or Loss Payee provisions")
    public void iAddTheDetailsForMortgageHolderOrLossPayeeProvisions() {
        
    }

    @And("I Click on Endorsements tab")
    public void iClickOnEndorsementsTab() {
        
    }

    @And("I am not selecting the recommended endorsements")
    public void iAmNotSelectingTheRecommendedEndorsements() {
        
    }

    @And("I Click on Load Forms")
    public void iClickOnLoadForms() {
        
    }

    @And("I fill details for endorsement form {int}")
    public void iFillDetailsForEndorsementForm(int formId) {
        
    }

    @And("I add Equipment Breakdown and navigate to Reinsurance Summary")
    public void iAddEquipmentBreakdownAndNavigateToReinsuranceSummary() {
        
    }

    @And("I select the Reinsurer for Equipment Breakdown at Reinsurance Summary and navigate to next stage")
    public void iSelectTheReinsurerForEquipmentBreakdownAtReinsuranceSummaryAndNavigateToNextStage() {
        
    }

    @And("I run Risk Models and Sub Peril Risk Models")
    public void iRunRiskModelsAndSubPerilRiskModels() {
        Assert.fail("Fail reason");
    }

    @And("I rate Property product and verify Insbridge interface")
    public void iRatePropertyProductAndVerifyInsbridgeInterface() {
        
    }

    @And("I Complete the GL Operations for the GL Product")
    public void iCompleteTheGLOperationsForTheGLProduct() {
        
    }

    @And("I complete the details in UW Rationale and Quote stages")
    public void iCompleteTheDetailsInUWRationaleAndQuoteStages() {
        
    }

    @And("I verify generation of Reinsurance policy numbers, Binder document, External Rating Worksheet and verify Thunderhead interface at Bind Stage")
    public void iVerifyGenerationOfReinsurancePolicyNumbersBinderDocumentExternalRatingWorksheetAndVerifyThunderheadInterfaceAtBindStage() {
        
    }

    @And("I generate Draft Policy and Book Policy at Handover, stage")
    public void iGenerateDraftPolicyAndBookPolicyAtHandoverStage() {
        
    }

    @Then("I verify whether the policy is Booked")
    public void iVerifyWhetherThePolicyIsBooked() {

    }

    @And("I create a new submission and complete details in Additional Information, Upload Information, add Other Named Insured and complete Contact Information")
    public void iCreateANewSubmissionAndCompleteDetailsInAdditionalInformationUploadInformationAddOtherNamedInsuredAndCompleteContactInformation() {
        
    }

    @And("I store case id into Testdata file for test {string} from file {string}")
    public void iStoreCaseIdIntoTestdataFileForTestFromFile(String testName, String testFile) {
    }

    @And("I search the policy in inprogress status")
    public void iSearchThePolicyInInprogressStatus() {
        String caseId = "SUB-4";
        homepage.openSearchfromStartMenu();
        searchPage.switchToAdvancedSearch();
        searchPage.enterCaseID(caseId);
        searchPage.searchInProgress();
        String caseStatus = searchPage.getStatusForCaseId(caseId);
        Assert.assertEquals(caseStatus, "In-Progress", "Unable to verify Transaction status for Case ID: [" + caseId + "] ");
    }
}
