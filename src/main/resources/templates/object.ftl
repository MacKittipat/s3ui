<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Objects</h4>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <a href="${rc.contextPath}/">Servers</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets</a> >
        Objects
    </div>
</div>

<#if nextMarker??>
    <div class="row">
        <div class="col s12">
            <a href="${rc.contextPath}/${serverName}/${bucketName}/?nextMarker=${nextMarker}">Next</a>
        </div>
    </div>
</#if>

<div class="row">
    <div class="col s12">
        <ul class="collection">
            <#list objectSummaryList as objectSummary>
                <a href="${rc.contextPath}/${serverName}/${bucketName}/object?key=${objectSummary.key}" class="collection-item">${objectSummary.key}</a>
            </#list>
        </ul>
    </div>
</div>
</@my.layout>
