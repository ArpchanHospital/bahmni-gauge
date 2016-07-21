package org.bahmni.gauge.common.home;

import org.bahmni.gauge.common.BahmniPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BahmniPage {

	public static final String URL = BASE_URL.concat("/home/#/dashboard");

	@FindBy(how= How.CSS, using = "i.icon-bahmni-registration")
    public WebElement registration;
	
	@FindBy(how= How.CSS, using = "i.icon-bahmni-documents")
    public WebElement patientDocuments;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-admin")
    public WebElement admin;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-reports")
    public WebElement exports;

	@FindBy(how= How.CSS, using = "i.icon-bahmni-program")
    public WebElement programs;

	@FindBy(how = How.CSS, using = "i.fa-caret-down")
	public WebElement user_info;

	@FindBy(how= How.CSS, using = "i.fa-power-off")
	public WebElement logout;

    public void clickRegistrationApp(){
    	registration.click();
    }

    public void clickClinicalApp() {
    	patientDocuments.click();
    }


	public void clickAdminApp() {
    	admin.click();
    }

	public void logout() {
		user_info.click();
		logout.click();
	}

    public void clickExportsApp(){
    	exports.click();
    }

    public void clickProgramsApp(){
    	programs.click();
    }

}
