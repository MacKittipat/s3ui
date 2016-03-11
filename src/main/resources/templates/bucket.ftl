<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Buckets</h1>
    <div>
        <a href="${rc.contextPath}/">Servers</a> >
        Buckets
    </div>
    <div class="collection">
        <#list bucketList as bucket>
            <a href="${rc.contextPath}/${serverName}/${bucket.name}/" class="collection-item">${bucket.name}</a>
        </#list>
    </div>
</@my.layout>
