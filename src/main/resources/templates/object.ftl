<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Object Detail</h1>
    <div>
        <a href="${rc.contextPath}">Server</a> >
        <a href="${rc.contextPath}/${serverName}">Bucket</a> >
        <a href="${rc.contextPath}/${serverName}/${bucketName}">Object</a> >
        Object Detail
    </div>
    <div>
        <#list s3ObjectMetaDataMap?keys as key>
            <div>
                ${key} = ${s3ObjectMetaDataMap[key]}
            </div>
        </#list>
    </div>
</@my.layout>
