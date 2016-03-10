<#import "macro/layout.ftl" as my>
<@my.layout>
    <#list serverNameList as serverName>
        <div>
            <a href="${serverName}/buckets">${serverName}</a>
        </div>
    </#list>
</@my.layout>
