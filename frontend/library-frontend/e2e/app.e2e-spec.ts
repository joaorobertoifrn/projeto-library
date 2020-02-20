import { ClinicaUiPage } from './app.po';

describe('clinica-ui App', () => {
  let page: ClinicaUiPage;

  beforeEach(() => {
    page = new ClinicaUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to cli!');
  });
});
