<#import "parts/common.ftlh" as c> 

<@c.page>


<#list exercises as exercise>
<div class = "card my-3" style="width: 18rem">
	<b>${exercise.title}</b>

</div>
<#else> No exercises </#list> </@c.page>