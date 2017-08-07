package utilities;

public enum VariablesInstance {
	OFFICE365("https://login.microsoftonline.com/common/oauth2/authorize?client_id=4345a7b9-9a63-4910-a426-35363201d503&response_mode=form_post&response_type=code+id_token&scope=openid+profile&state=OpenIdConnect.AuthenticationProperties%3d3vaMpXw6fwovLa2RL5k9vlHL41hToSVPBZmsqZIrM9t7XyyviUsMYaOO0ePkt9gWHum96fA-a8KtExIICHr9l51gpvzmPqyEhf8KUrC6Uvz7c6yQvrhhht02u0pDM9Gc&nonce=636355501519266578.YjNmZDIyNGEtYTYzNS00MTQ3LWE4MGUtOGFjMzgzYjY2NmRmMjY3ODIyNTktYjg2NC00OGJlLTllOWUtNDRhM2ZjNzg1ZTVi&redirect_uri=https%3a%2f%2fwww.office.com%2flanding&ui_locales=en-US&mkt=en-US&client-request-id=0a9c7c6c-19da-4e6c-aee2-40980f2bc7b1"),
	SCREENSHOTS("screenshots/"),
	FIREFOX("firefox"),
	CHROME("chrome"),
	USERID("skuye@planittesting.com"),
	USERPWD(""),
	DBNAME("openhab_db"),
	MEASURE("office"),
	TAG("host"),
	TAGVALUE("office365"),
	ADDFIRST("logging_in"),
	ADDSECOND("logging_out"),
	OFFICEHOME("1"),
	OFFICESIGNIN("2"),
	UNKNOWN("");

	
	private String variable_string;
	
	VariablesInstance(String variable_string) {
		this.variable_string = variable_string;
	}
	
	public String variable_string() {
		return variable_string;
	}
}
