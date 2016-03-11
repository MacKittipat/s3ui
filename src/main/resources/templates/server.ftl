<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Servers</h4>
    </div>
    <div class="col s12">
        <div class="collection">
            <#list serverNameList as serverName>
                <a href="${rc.contextPath}/${serverName}/" class="collection-item">${serverName}</a>
            </#list>
        </div>
    </div>
</div>
</@my.layout>
