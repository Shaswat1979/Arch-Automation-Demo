package arch.auto.pageObjects.arch;

import arch.auto.utils.selenium.PageObjectUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends PageObjectUtil {
    @FindBy(xpath="//a[@class='Header_nav_Primary']") private WebElement startMenuLink;
    @FindBy(xpath="//span[@class='menu-item-title' and text()='Search']") private WebElement searchMenuOption;
    @FindBy(xpath="//a[contains(text(), 'Switch to Advanced Search')]") private WebElement advanceSearchLink;
    @FindBy(id="CaseID") private WebElement caseIdTextBox;
    @FindBy(xpath="//button[contains(text(),'Search In Progress')]") private WebElement searchInprogressBtn;
    @FindBy(xpath="//table[@id='bodyTbl_right' and contains(@pl_prop, 'InProgressList')]") private WebElement wipTable;

    By wipTableRows = By.xpath("./tbody/tr/td/parent::tr");

    public SearchPage() {
        initialise(this);
    }

    public void switchToAdvancedSearch() {
        getDriver().switchTo().frame("PegaGadget0Ifr");
//        switchFrame(By.id("PegaGadget0Ifr"));
        waitPageToLoad();
        waitUntilElementClickable(caseIdTextBox);
        sleep(5000L);
        scroll(advanceSearchLink);
        moverToElement(advanceSearchLink);
        advanceSearchLink.click();
        waitPageToLoad();
    }

    public void enterCaseID(String caseID) {
        scrollToTop();
        waitUntilElementClickable(caseIdTextBox);
        sleep(5000L);
        caseIdTextBox.clear();
        caseIdTextBox.sendKeys(caseID);
    }

    public void searchInProgress() {
        scroll(searchInprogressBtn);
        moverToElement(searchInprogressBtn);
        searchInprogressBtn.click();
        waitPageToLoad();
    }

    public String getStatusForCaseId(String caseId) {
        String status = "";
        List<WebElement> wipRows = wipTable.findElements(wipTableRows);
        for (WebElement row : wipRows) {
            moverToElement(row);
            scroll(row);
            if (row.findElement(By.xpath("./td[2]")).getText().trim().equalsIgnoreCase(caseId)) {
                WebElement statusCell = row.findElement(By.xpath("./td[12]"));
                moverToElement(statusCell);
                scroll(statusCell);
                return row.findElement(By.xpath("./td[12]")).getText().trim();
            }
        }

        return status;
    }
}

