<#import "macro/layout.ftl" as my>
<@my.layout>
    <#list objectSummaryList as objectSummary>
    <div>
        ${objectSummary.key}
    </div>
    </#list>
</@my.layout>
