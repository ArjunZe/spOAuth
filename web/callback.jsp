<%-- 
    Document   : callback
    Created on : Jul 10, 2020, 8:53:10 AM
    Author     : mreddy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="spOAuth.Auth"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Callback Receive & Generate Refresh Token</title>
        <meta charset="utf-8"></meta>
        <script src="spo.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form class="well form-horizontal" method="post"  id="contact_form">
                <fieldset>
                    <legend><center><h2><b>Get the Access token and Refresh token</b></h2></center></legend><br/>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Client ID</label>  
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="clientid" placeholder="Client ID" class="form-control"  type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Client Secret</label>  
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-eye-close"></i></span>
                                <input  name="clientsecret" placeholder="Client Secret" class="form-control"  type="text"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Site</label>  
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
                                <input  name="site" placeholder="Site" class="form-control"  type="text"/>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="code2" name="code" value="<%= request.getParameter("code")%>">
                    <div class="form-group">
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4"><br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="btn" type="submit" class="btn btn-warning" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Generate Tokens <span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <footer align="center"> <p>AuthCode&nbsp;&nbsp;:&nbsp;&nbsp;<% out.print(request.getParameter("code"));%></p> <br/>
            Response&nbsp;&nbsp;:&nbsp;&nbsp;
            <%
                if (request.getParameter("btn") != null) //check button click event not null button name is "btn_check"
                {
                    //out.print(request.getParameter("site"));
                    Auth mc = new Auth(); //create a java class and this class contain calling return type "check()" function 
                    String s = request.getParameter("site");
                    String cid = request.getParameter("clientid");
                    String csec = request.getParameter("clientsecret");
                    String acode = request.getParameter("code");
                    String validate = mc.check(s, cid, csec, acode);
                    out.print("<p id=\"resp\">"+validate+"</p>");
                    out.print("<button onclick=\"copy()\">Copy</button>");
                }
            %>
        </footer>

    </body>
</html>
