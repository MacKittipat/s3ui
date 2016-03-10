<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Object</h1>
    <div>
        <a href="${rc.contextPath}">Server</a> >
        <a href="${rc.contextPath}/${serverName}">Bucket</a> >
        Object
    </div>
    <ul class="collection">
        <#list objectSummaryList as objectSummary>
            <li class="collection-item">${objectSummary.key}</li>
        </#list>
    </ul>
</@my.layout>
