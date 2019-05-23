
 <%
   String userId = (String)session.getAttribute("_userId");
   String token = (String)session.getAttribute("token");

   %>
<html>
<head>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="../../css/styles.css" />

<script type="text/javascript">  var currentUserId = "<%=userId%>"
var userAccessToken = "<%=token%>"

</script>
</head>
    <body>


<div id="auth-app"></div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/client.min.js"></script>

</html>