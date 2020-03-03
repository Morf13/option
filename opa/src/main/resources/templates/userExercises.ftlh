<#import "parts/common.ftlh" as c>

<@c.page>
<#if isCurrentUser>
    <#include "parts/exerciseEdit.ftlh" />
</#if>

<#include "parts/exerciseList.ftlh" />
</@c.page>