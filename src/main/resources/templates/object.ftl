<#import "macro/layout.ftl" as my>
<@my.layout>
<div class="row">
    <div class="col s12">
        <h4>Objects</h4>
    </div>
</div>

<div class="row">
    <div class="col s10">
        <a href="${rc.contextPath}/">Servers [ ${serverName} ]</a> >
        <a href="${rc.contextPath}/${serverName}/">Buckets [ ${bucketName} ]</a> >
        Objects
    </div>
    <div class="col s2 right-align">
        <#if nextMarker??>
            <a href="${rc.contextPath}/${serverName}/${bucketName}/?nextMarker=${nextMarker}">Next</a>
        </#if>
    </div>
</div>

<div class="row">
    <div class="col s12">
        <ul class="collection">
            <#list objectSummaryList as objectSummary>
                <a href="${rc.contextPath}/${serverName}/${bucketName}/object?key=${objectSummary.key}" class="collection-item">${objectSummary.key}</a>
            </#list>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col s12 right-align">
        <#if nextMarker??>
            <a href="${rc.contextPath}/${serverName}/${bucketName}/?nextMarker=${nextMarker}">Next</a>
        </#if>
    </div>
</div>
</@my.layout>
