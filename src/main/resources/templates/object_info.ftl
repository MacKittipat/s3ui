<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Object Detail</h4>
    </div>
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        <a href="${rc.contextPath}/${serverName}/${bucketName}/">Objects</a> >
        Object Detail
    </div>
    <#list s3ObjectMetaDataMap?keys as key>
        <div class="col s3">
            ${key}
        </div>
        <div class="col s9">
            ${s3ObjectMetaDataMap[key]}
        </div>
    </#list>
</@my.layout>
