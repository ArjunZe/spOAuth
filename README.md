# spOAuth
App to get Refresh Token from Microsoft SharePoint.
1.	Register your app in SharePoint by Navigate to https://your_site_name.com/your-sub-site/_layouts/15/appregnew.aspx 
2.	The redirect URL must be https://localhost:8443/spOAuth/callback.jsp 
3.	Save Client ID & Client Secret.
4.	Download zip from https://raw.githubusercontent.com/ArjunZe/spOAuth/master/SpOAuth.zip
5.	Extract the zip.
6.	Double Click Start.bat.
7.	Open https://localhost:8443/spOAuth/ in browser.
8.	Fill in Site Name, Sub Site, Client Id and Scope & Click Authorize.
9.	Refer scopes -https://spshell.blogspot.com/2015/03/sharepoint-online-o365-oauth.html
10.	It will launch to SharePoint, then login and click trust it.
11.	Once Redirected back to https://localhost:8443/spOAuth/callback.jsp, fill in Client Id, Client Secret, Site Name and Click Generate.
12.	Once Generated, Click Copy.
