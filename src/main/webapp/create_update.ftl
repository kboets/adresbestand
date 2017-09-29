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
                    <h1><#if person.lastName??>
                           <@spring.message "updateAddress.title" />
                        <#else>
                            <@spring.message "createAddress.title" />
                        </#if>
                    </h1>
                    <form id="createUpdateForm" name="person" action="createUpdate" method="POST" class="form-horizontal">
                    <#if success??>
                        <div class="alert alert-success">
                            <i class="icon-ok-sign icon-green"></i> <span> <@spring.message code=success /></span>
                        </div>
                    </#if>
                        <p><#if person.lastName??>
                            <@spring.message "uc.subUpdateTitle" />
                        <#else>
                            <@spring.message "uc.subCreateTitle" />
                        </#if></p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-1">Voornaam </label>
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
                                />
                            </div>
                            <label for="lastName" class="col-sm-1">Naam *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="lastName" name="lastName"
                                <#if person.lastName??>
                                       value="${person.lastName}"
                                <#else>
                                       value=""
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-1">Straat *</label>
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
                                />
                            </div>
                            <label for="houseNumber" class="col-sm-1">Nummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="houseNumber" name="mainAddress.houseNumber"
                                <#if person.mainAddress?? && person.mainAddress.houseNumber??>
                                       value="${person.mainAddress.houseNumber}"
                                <#else>
                                       value=""
                                </#if>
                                />
                            </div>
                            <label for="box" class="col-sm-1">Bus</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="box" name="mainAddress.box"
                                <#if person.mainAddress?? && person.mainAddress.box??>
                                       value="${person.mainAddress.box}"
                                <#else>
                                       value=""
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="city" class="col-sm-1">Gemeente *</label>
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
                                />
                            </div>
                            <label for="zipcode" class="col-sm-1">Postnummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="zipCode" name="mainAddress.municipality.zipCode"
                                <#if person.mainAddress?? && person.mainAddress.municipality.zipCode??>
                                       value="${person.mainAddress.municipality.zipCode?c}"
                                <#else>
                                       value=""
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="buttons">
                            <button class="btn bold" id="btn_save">bewaar</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </div>
                    </form>
                    <br/><br/>
                </div>
            </div>
        </div>
    </div>
    <#--<!-- /#page-content-wrapper &ndash;&gt;-->
    <#--<script>-->
        <#--console.log("inside page of create update");-->

    <#--</script>-->
</div>
</body>

</html>
