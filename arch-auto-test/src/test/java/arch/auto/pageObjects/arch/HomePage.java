package arch.auto.pageObjects.arch;

import arch.auto.utils.selenium.DriverContext;
import arch.auto.utils.selenium.PageObjectUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends PageObjectUtil {
    @FindBy(xpath="//a[@class='Header_nav_Primary']") private WebElement startMenuLink;
    @FindBy(xpath="//span[@class='menu-item-title' and text()='Search']") private WebElement searchMenuOption;

    public HomePage() {initialise(this);}


    public void openStartMenu() {
        startMenuLink.click();
        waitPageToLoad();
    }

    public void openSearchfromStartMenu() {
        openStartMenu();
        waitUntilElementVisible(searchMenuOption);
        searchMenuOption.click();
        waitPageToLoad();
    }
    

}

