<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Buckets</h4>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers [ ${serverName} ]</a> >
        Buckets
    </div>
</div>
<div class="row">
    <div class="col s12">
        <div class="collection">
            <#list bucketList as bucket>
                <a href="${rc.contextPath}/${serverName}/${bucket.name}/" class="collection-item">${bucket.name}</a>
            </#list>
        </div>
    </div>
</div>
</@my.layout>
