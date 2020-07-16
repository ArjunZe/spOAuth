# spOAuth
App to get Refresh Token from Microsoft SharePoint.

Steps:
1.	Register your app in SharePoint by Navigate to https://your_site_name.com/your-sub-site/_layouts/15/appregnew.aspx and the redirect URL must be https://localhost:8443/spOAuth/callback.jsp .
    Save Client ID & Client Secret.
2.	Download zip from https://raw.githubusercontent.com/ArjunZe/spOAuth/master/SpOAuth.zip
3.	Extract the zip.
4.	Double Click Start.bat .
5.	Open https://localhost:8443/spOAuth/ in browser.
6.	Fill in Site Name, Sub Site, Client Id and Scope & Click Authorize.
	Refer scopes -https://spshell.blogspot.com/2015/03/sharepoint-online-o365-oauth.html
7.	It will launch to SharePoint, then  login and click trust it.
8.	Once Redirected back to https://localhost:8443/spOAuth/callback.jsp , fill in Client Id, Client Secret, Site Name and Click Generate.
9.	Once Generated, Click Copy.