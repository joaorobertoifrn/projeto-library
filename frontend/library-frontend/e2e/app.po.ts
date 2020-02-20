import { browser, by, element } from 'protractor';

export class ClinicaUiPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('cli-root h1')).getText();
  }
}
