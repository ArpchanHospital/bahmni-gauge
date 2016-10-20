package org.bahmni.gauge.common.specs;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.bahmni.gauge.common.BahmniPage;
import org.bahmni.gauge.common.DriverFactory;
import org.bahmni.gauge.common.PageFactory;
import org.bahmni.gauge.common.registration.RegistrationFirstPage;
import org.bahmni.gauge.common.registration.RegistrationSearch;
import org.bahmni.gauge.common.registration.domain.Patient;
import org.bahmni.gauge.common.registration.domain.Visit;
import org.bahmni.gauge.data.StoreHelper;
import org.bahmni.gauge.rest.BahmniRestClient;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class RegistrationFirstPageSpec {

	private final WebDriver driver;

	public RegistrationFirstPage registrationFirstPage;

	public RegistrationFirstPageSpec() {
		this.driver = DriverFactory.getDriver();
		this.registrationFirstPage = PageFactory.getRegistrationFirstPage();
	}

	@BeforeClassSteps
	public void waitForAppReady() {
		BahmniPage.waitForSpinner(driver);
	}

	@Step("On the new patient creation page")
	public void navigateToPatientCreatePage() {
		driver.get(RegistrationFirstPage.URL);
	}

	@Step("Create the following patient <table>")
	public void createPatients(Table table) throws Exception {
		registrationFirstPage.createPatients(table);
	}

	@Step("Create patient with manual id <table>")
	public void createPatientWithId(Table table) throws Exception {
		registrationFirstPage.createPatientWithId(table);
	}

	@Step("Create the following patient with ID as recently created Patient <table>")
	public void createPatientsWithExistingID(Table table) throws Exception {
        RegistrationSearch registrationSearch = PageFactory.get(RegistrationSearch.class);
		String recentlyCreatedPatientID = registrationSearch.getPatientFromSpecStore().getIdNumber();
		table.getTableRows().get(0).addCell("idNumber", recentlyCreatedPatientID);
		table.getColumnNames().add(1,"idNumber");
		registrationFirstPage.createPatientWithId(table);
	}

	@Step("Click on search patient link")
	public void navigateToPatientSearch() {
		registrationFirstPage.navigateToSearchPage();
	}

	@Step("Validate that the patient edit page is opened for previously created patient")
	public void validateThePatientPageIsOpened() {
		registrationFirstPage.verifyPatientWithIdentifierAndName();
	}

	@Step("Ensure that the patient edit page is opened for previously created patient")
	public void ensureThePatientPageIsOpened() {
		registrationFirstPage.verifyPatientWithIdentifierAndName();
	}

	@Step("Start a visit <visit>")
	public void startPatientVisit(String visit) throws InterruptedException {
		registrationFirstPage.showAllVisitTypeOptions();
		registrationFirstPage.findVisit(visit).click();
	}

	@Step("Select check to enter patient ID manually")
	public void selectCheckToEnterPatientID() {
		registrationFirstPage.selectEnterPatientID();
	}

	@Step("Enter Visit Details Page")
	public void enterVisitDetailsPage() {
		registrationFirstPage.enterVisitDetailsPage();
	}
	@Step("Enter Visit Details for Admitted Patient")
	public void enterVisitDetailsForAdmittedPatient() {
		registrationFirstPage.enterVisitDetailButton.click();
	}

	@Step("Enter visit details")
	public void enterVisitDetails() {
		registrationFirstPage.enterVisitDetails();
	}

	@Step("Create the following patient using api <table>")
	public void createPatientThroughAPI(Table table) throws Exception {
		registrationFirstPage.createPatientUsingApi(table);
	}

	@Step("Verify the patient creation fails")
	public void verifyPatientCreationWithSameID() {
		new BahmniPage().validateSystemException(driver);
	}

	@Step("Open visit for previous patient using api")
	public void openVisitThroughApi(){
		Visit visit=new Visit();
		visit.setPatient(StoreHelper.getEntityInSpectStore(Patient.class));
		visit.setLocation("c1f25be5-3f10-11e4-adec-0800271c1b75");
		visit.setType("c22a5000-3f10-11e4-adec-0800271c1b75");
		BahmniRestClient.get().create(visit);
	}

	@Step("Verify <buttonText> button is <displayOption>")
	public void verifyButtonDisplayed(String buttonText, String displayOption){
		if (displayOption.toLowerCase().equals("displayed"))
			Assert.assertTrue(buttonText+" button is not displayed",registrationFirstPage.findButtonByText(buttonText).isDisplayed());
		else
			Assert.assertTrue(buttonText+" button is displayed",registrationFirstPage.findButtonByText(buttonText).isDisplayed());

	}
}
