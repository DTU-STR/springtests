package de.tomsu.springtests.auth.facebook;

import javax.swing.JOptionPane;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;

public class Application {
	
	public static void main(String[] args) {
		String appId = promptForInput("Enter your App ID: ");
		String appSecret = promptForInput("Pls enter you App Secret: ");
		String appToken = fetchApplicationAccessToken(appId, appSecret);
		AppDetails details = fetchApplicationData(appId, appToken);
		System.out.println("\n   APPLICATION DETAILS");
        System.out.println("=========================");
        System.out.println("ID:             " + details.getId());
        System.out.println("Name:           " + details.getName());
        System.out.println("Namespace:      " + details.getNamespace());
        System.out.println("Contact Email:  " + details.getContactEmail());
        System.out.println("Website URL:    " + details.getWebsiteUrl());

	}

	private static String fetchApplicationAccessToken(String appId, String appSecret) {
		OAuth2Operations oauth = new FacebookConnectionFactory(appId, appSecret).getOAuthOperations();
		// AccessGrant grant = oauth.exchangeCredentialsForAccess("facebook@google.de", "pwd", null); // create MultiValueMap with redirect_uri
		// String accessToken = grant.getAccessToken();
		return oauth.authenticateClient().getAccessToken();
	}

	private static AppDetails fetchApplicationData(String appId, String appToken) {
		Facebook fb = new FacebookTemplate(appToken);
		return fb.restOperations().getForObject("https://graph.facebook.com/{appId}?fields=name,namespace,contact_email,website_url", 
				AppDetails.class, appId);
	}

	private static String promptForInput(String message) {
		return JOptionPane.showInputDialog(message);
	}

}
