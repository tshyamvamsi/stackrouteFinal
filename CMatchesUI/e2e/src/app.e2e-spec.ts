import { AppPage } from './app.po';
import { browser, by, logging, element } from 'protractor';

describe('Match UI App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CMatchesUI');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(2000);
  });  


  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('super');
    browser.element(by.id('lastName')).sendKeys('super');
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.register-user')).click();
    // expect(browser.getCurrentUrl()).toContain('/login');
    browser.driver.sleep(1000);
  });

  it('should be able to login user', () => {
    browser.element(by.id('userId')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.login-click')).click();
    browser.driver.sleep(1000);
  });


  it('should able to login and navigate to all matches screen', () => {
   // browser.element(by.css('.login-click')).click();
    expect(browser.getCurrentUrl()).toContain('/matches/all');
  });
  
  it('should able to add matches to favouritelist', () => {
    browser.driver
      .manage()
      .window()
      .maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(1000);
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);    
    browser.driver.sleep(1000);
  });

  it('should able to login and navigate to favouritelist matches screen', () => {
    
    browser.element(by.css('.mat-favourite-list')).click();
    expect(browser.getCurrentUrl()).toContain('matches/favouritelist');
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
   });


   it('should be able to delete data from WishList', () => {
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(1000);
    browser.element(by.css('.deletebutton')).click();
    browser.driver.sleep(1000);
  });

  it('should able to show matchrecommendation', () => {
    browser.element(by.css('.mat-match-recommendation')).click();
    expect(browser.getCurrentUrl()).toContain('matches/matchrecommendation');
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
  });

  it('should able to show quickview', () => {
    browser.element(by.css('.mat-match-quickview')).click();
    expect(browser.getCurrentUrl()).toContain('matches/quickview');
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
  });

  it('should be able to logout from application', () => {
    browser.driver.sleep(500);
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(500);
  });
});
