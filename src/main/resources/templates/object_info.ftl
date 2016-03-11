<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Object Detail</h1>
    <div>
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        <a href="${rc.contextPath}/${serverName}/${bucketName}/">Objects</a> >
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
