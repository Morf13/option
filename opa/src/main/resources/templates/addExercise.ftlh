<#import "parts/common.ftlh" as c> 

<@c.page>

<div class="form-row">
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Title" />
 		<input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>
</@c.page>