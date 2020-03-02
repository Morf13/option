<#import "parts/common.ftlh" as c> <@c.page>

<div class="form-row">
	<div class="form-group mt-3">
		<form method="post" enctype="multipart/form-data">
			<input type="text"
				class="form-control ${(titleError??)?string('is-invalid', '')}"
				name="title" placeholder="Title"
				value="<#if exercise??>${exercise.title}</#if>" />
			<#if titleError??>
        <div class="invalid-feedback">
          ${titleError}
        </div>
        </#if>
 		<div class="form-group">
                <div class="custom-file">
                    <input type="file" class="form-control ${(fileNameError??)?string('is-invalid', '')}"  name="file"  id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                <#if fileNameError??>
        <div class="invalid-feedback">
          ${fileNameError}
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
       <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
	</div>
</div>

</@c.page>