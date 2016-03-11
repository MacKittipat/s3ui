<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Objects</h1>
    <div>
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        Objects
    </div>
    <ul class="collection">
        <#list objectSummaryList as objectSummary>
            <a href="${rc.contextPath}/${serverName}/${bucketName}/object?objectKey=${objectSummary.key}" class="collection-item">${objectSummary.key}</a>
        </#list>
    </ul>
</@my.layout>
