<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Object Detail : <b>${key}</b></h4>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        <a href="${rc.contextPath}/${serverName}/${bucketName}/">Objects</a> >
        Object Detail
    </div>
</div>
<#list s3ObjectMetaDataMap?keys as key>
    <div class="row">
        <div class="col s2">
        <b>${key}</b>
        </div>
        <div class="col s10">
        : ${s3ObjectMetaDataMap[key]}
        </div>
    </div>
</#list>
</@my.layout>
