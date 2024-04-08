package pageComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeighingsComponent extends BaseComponent{

    @FindBy(xpath = "//div[@class='game-info']//li")   
    List<WebElement> weighingList;

    public WeighingsComponent(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getWeighingList() {
        return weighingList;
    }

    public WebElement getLastWeigh() {
        return weighingList.get(weighingList.size()-1);
    }

 

}
