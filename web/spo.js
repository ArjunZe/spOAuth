/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var authCodeURL;
function getRealm()
{
    //alert('cc');
    var baseurl = "https://cors-anywhere.herokuapp.com/https://" + document.getElementById("user-form:your_site").value + ".sharepoint.com/_vti_bin/client.svc";
    // var baseurl = "https://na2.ai.dm-us.informaticacloud.com/active-bpel/public/rt/dIjE3p8O2kuj9gfDoNfsuE/Ze-GetPodInfoForOrgs?inOrgIds=0GkxwvoUUbfhYQMFDIS1O2";
    var xmlhttp = new XMLHttpRequest();
    //xmlhttp.setRequestHeader("User-Agent","PostmanRuntime/7.24.1");
    xmlhttp.open("GET", baseurl, true);
    xmlhttp.setRequestHeader('Access-Control-Allow-Origin', '*');
    xmlhttp.setRequestHeader('Authorization', 'Bearer ');
    xmlhttp.setRequestHeader('Access-Control-Allow-Methods', 'GET, POST, PATCH, PUT, DELETE, OPTIONS');
    xmlhttp.setRequestHeader('Access-Control-Allow-Headers', 'x-requested-with, Content-Type, origin, authorization, accept, client-security-token');
    xmlhttp.send();
    xmlhttp.onload = function () {
        // alert('c');
        if (xmlhttp.readyState === 4) {
            var contentType = xmlhttp.getResponseHeader("WWW-Authenticate");
            var bearer = contentType.toString().replace(/"/g, "").split(",")[0].split("=")[1];
            var clientID = contentType.toString().replace(/"/g, "").split(",")[1].split("=")[1];
            //alert(bearer+clientID);
            document.getElementById("berer").innerHTML = "Bearer realm=" + bearer + " and client_id=" + clientID;
            //Get the Authorization code from Azure Access Control Service
            authCodeURL = "https://" + document.getElementById("user-form:your_site").value + ".sharepoint.com/_layouts/oauthauthorize.aspx?client_id=";
            document.getElementById("authcode").innerHTML = authCodeURL;
        }
    };
}
function Authorize() {
    var iscope = document.getElementById("scope").value;
    var iclientId = document.getElementById("clientid").value;
    var AutorzeURL = "https://" + document.getElementById("site").value + ".sharepoint.com/_layouts/oauthauthorize.aspx?client_id=" + iclientId + "&scope=" + iscope + "&response_type=code&redirect_uri=" + encodeURIComponent('https://localhost:8443/spOAuth/callback.jsp');
    alert("Opening "+AutorzeURL);
    window.open(AutorzeURL,'Auth');
}
