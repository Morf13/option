


<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    <#if exercise??>Exercise editor<#else>Add exercise</#if>
</a>

<div class="collapse <#if exercise??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" action = "/addExercise" enctype="multipart/form-data">
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
             <input type="hidden" name="id" value="<#if exercise??>${exercise.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><#if exercise??>Save exercise<#else>Add exercise</#if></button>
            </div>
        </form>
    </div>
</div>
