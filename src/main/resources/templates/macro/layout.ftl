<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>s3ui</title>
    <link type="text/css" rel="stylesheet" href="${rc.contextPath}/assets/materialize/css/materialize.min.css"  media="screen,projection">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container">
        <#nested />
    </div>
</body>
</html>
</#macro>