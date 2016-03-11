<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Objects</h4>
    </div>
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        Objects
    </div>
    <div class="col s12">
        <ul class="collection">
            <#list objectSummaryList as objectSummary>
                <a href="${rc.contextPath}/${serverName}/${bucketName}/object?key=${objectSummary.key}" class="collection-item">${objectSummary.key}</a>
            </#list>
        </ul>
    </div>
</@my.layout>
