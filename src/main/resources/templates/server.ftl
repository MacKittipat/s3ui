<#import "macro/layout.ftl" as my>
<@my.layout>
    <h1>Server</h1>
    <div class="collection">
        <#list serverNameList as serverName>
            <a href="${serverName}" class="collection-item">${serverName}</a>
        </#list>
    </div>
</@my.layout>
