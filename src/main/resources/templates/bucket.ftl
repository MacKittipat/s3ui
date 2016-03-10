<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Bucket</h1>
    <div>
        <a href="${rc.contextPath}">Server</a> >
        Bucket
    </div>
    <div class="collection">
        <#list bucketList as bucket>
            <a href="${serverName}/${bucket.name}/" class="collection-item">${bucket.name}</a>
        </#list>
    </div>
</@my.layout>
