<#import "parts/common.ftlh" as c> 

<@c.page>
 
<div class="form-row">
  <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
        <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}" 
        name="title" placeholder="Title" 
        value="<#if exercise??>${exercise.title}</#if>" />
        <#if titleError??>
        <div class="invalid-feedback">
          ${titleError}
        </div>
        </#if>
 		<div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
        </form>
    </div>
</div>

</@c.page>