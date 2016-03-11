<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Buckets</h4>
    </div>
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers</a> >
        Buckets
    </div>
    <div class="col s12">
        <div class="collection">
            <#list bucketList as bucket>
                <a href="${rc.contextPath}/${serverName}/${bucket.name}/" class="collection-item">${bucket.name}</a>
            </#list>
        </div>
    </div>
</@my.layout>
