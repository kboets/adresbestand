<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html>
<head lang="en">
    <@header.headmeta/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.js"></script>
        <script src="<@spring.url '/js/adresbestand_cu.js'/>"></script>
    <#if person.lastName??>
        <title><@spring.message "createAddress.title" /></title>
    <#else>
        <title><@spring.message "updateAddress.title" /></title>
    </#if>
</head>
<body>

<div id="wrapper">
    <!-- Sidebar -->
<@sidebar.menu/>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1><#if readOnly??>
                            <#assign modus="readOnly">
                            <@spring.message "readAddress.title" />
                        <#elseif person.lastName??>
                            <@spring.message "updateAddress.title" />
                            <#assign modus="edit">
                        <#else>
                            <@spring.message "createAddress.title" />
                            <#assign modus="create">
                        </#if>
                    </h1>
                    <form id="createUpdateForm" name="person" action="createUpdate" method="POST" class="form-horizontal">
                    <#if success??>
                        <div class="alert alert-success">
                            <i class="icon-ok-sign icon-green"></i> <span> <@spring.message code=success /></span>
                        </div>
                    </#if>
                        <p><#if modus=="readOnly">
                            <@spring.message "uc.readTitle" />
                        <#elseif modus=="edit">
                            <@spring.message "uc.subUpdateTitle" />
                        <#else>
                            <@spring.message "uc.subCreateTitle" />
                        </#if></p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-1"><@spring.message "firstname" /></label>
                            <div class="col-sm-3">
                                <#if person.id??>
                                    <input type="hidden" name="id" id="id"  value="${person.id}"/>
                                </#if>
                                <input type="text" class="form-control" id="firstName" name="firstName" autofocus
                                <#if person.firstName??>
                                       value="${person.firstName}"
                                <#else>
                                       value=""
                                </#if>
                                 <#if modus=="readOnly">
                                        disabled
                                 </#if>
                                />
                            </div>
                            <label for="lastName" class="col-sm-1"><@spring.message "lastname" /> *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="lastName" name="lastName"
                                <#if person.lastName??>
                                       value="${person.lastName}"
                                <#else>
                                       value=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-1"><@spring.message "street" /> *</label>
                            <div class="col-sm-3">
                                <#if person.mainAddress??>
                                    <input type="hidden" name="mainAddress.id" id="mainAddress.id"  value="${person.mainAddress.id}"/>
                                </#if>
                                <input type="text" class="form-control" id="street" name="mainAddress.street"
                                <#if person.mainAddress?? && person.mainAddress.street??>
                                       value="${person.mainAddress.street}"
                                <#else>
                                       value=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                            <label for="houseNumber" class="col-sm-1"><@spring.message "housenumber" /> *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="houseNumber" name="mainAddress.houseNumber"
                                <#if person.mainAddress?? && person.mainAddress.houseNumber??>
                                       value="${person.mainAddress.houseNumber}"
                                <#else>
                                       value=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                            <label for="box" class="col-sm-1"><@spring.message "box" /></label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="box" name="mainAddress.box"
                                <#if person.mainAddress?? && person.mainAddress.box??>
                                       value="${person.mainAddress.box}"
                                <#else>
                                       value=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="city" class="col-sm-1"><@spring.message "city" /> *</label>
                            <div class="col-sm-3">
                                <#if person.mainAddress?? && person.mainAddress.municipality.city??>
                                    <input type="hidden" class="form-control autocomplete-suggestions" id="municipalityId" name="mainAddress.municipality.id" value="${person.mainAddress.municipality.id}"/>
                                <#else>
                                    <input type="hidden" class="form-control autocomplete-suggestions" id="municipalityId" name="mainAddress.municipality.id"/>
                                </#if>
                                <input type="text" class="form-control autocomplete-suggestions" id="city" name="mainAddress.municipality.city"
                                <#if person.mainAddress?? && person.mainAddress.municipality.city??>
                                       placeholder="${person.mainAddress.municipality.city}"
                                       value="${person.mainAddress.municipality.city}"
                                <#else>
                                       value=""
                                       placeholder=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                            <label for="zipcode" class="col-sm-1"><@spring.message "zipcode" /> *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="zipCode" name="mainAddress.municipality.zipCode"
                                <#if person.mainAddress?? && person.mainAddress.municipality.zipCode??>
                                       value="${person.mainAddress.municipality.zipCode?c}"
                                <#else>
                                       value=""
                                </#if>
                                <#if modus=="readOnly">
                                       disabled
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emails" class="col-sm-1"><@spring.message "emails" /></label>
                            <div class="col-sm-3">
                                <#if person.emails?? && person.emails?size != 0 >
                                    <#list person.emails as emails>
                                        <#if modus=="readOnly">
                                            <textarea class="form-control" name="emails" readonly="true">${emails}</textarea>
                                         <#else>
                                             <textarea class="form-control" name="emails">${emails}</textarea>
                                        </#if>
                                    </textarea>
                                    </#list>
                                <#else>
                                    <#if modus=="readOnly">
                                        <textarea class="form-control" name="emails" readonly="true"></textarea>
                                    <#else>
                                        <textarea class="form-control" name="emails"></textarea>
                                    </#if>

                                </#if>

                            </div>
                        </div>
                        <div class="buttons">
                            <#if modus=="create">
                                <button class="btn bold" id="btn_save"><@spring.message "button.save" /></button>
                                <button type="reset" class="btn btn-default"><@spring.message "button.reset" /></button>
                            <#elseif modus=="edit">
                                <button class="btn bold" id="btn_save"><@spring.message "button.save" /></button>
                            <#elseif modus=="edit" || modus=="readOnly">
                                <a href="<@spring.url '/print'/>" class="btn btn-info" role="button"><@spring.message "button.print" /></a>
                            </#if>
                        </div>
                    </form>
                    <br/><br/>
                </div>
            </div>
        </div>
    </div>

</div>
</body>

</html>
