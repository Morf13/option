<#include "security.ftlh">

<div class="card-columns">
    <#list exercises as exercise>
        <div class="card my-3">
            <div class="m-2">
                <span>${exercise.title}</span><br/>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-exercises/${exercise.author.id}">${exercise.authorName}</a>
            
            </div>
        </div>
    <#else>
        No Execises
    </#list>
</div>