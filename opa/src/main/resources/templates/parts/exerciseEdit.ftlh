<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Exercise editor
</a>
<div class="collapse <#if exercise??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                       value="<#if exercise??>${exercise.title}</#if>" name="title" placeholder="Exercise title" />
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>
            
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file"  class="form-control ${(fileNameError??)?string('is-invalid', '')}" />
                    <#if fileNameError??>
                    <div class="invalid-feedback">
                        ${fileNameError}
                    </div>
                </#if>
                  
                </div>
                
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save exercise</button>
            </div>
        </form>
    </div>
</div>
