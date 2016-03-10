<#import "macro/layout.ftl" as my>
<@my.layout>
    <#list bucketList as bucket>
        <div>
            <a href="buckets/${bucket.name}/">${bucket.name}</a>
        </div>
    </#list>
</@my.layout>
