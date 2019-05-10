<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html ng-app="adresbestand" ng-controller="personController">
<head lang="en"
    <@header.headmeta/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.js"></script>
<script src="<@spring.url '/js/adresbestand_cu.js'/>"></script>
<div ng-if="isPersonEmpty()"><title><@spring.message "createAddress.title" /></title></div>
<div ng-if="!isPersonEmpty()"><title><@spring.message "updateAddress.title" /></title></div>
</head>
<body>

<div id="wrapper">
    <!-- Sidebar -->
<@sidebar.menu/>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid" >
            <div class="row">
                <div class="col-lg-12">
                    <h1 ng-if="isPersonEmpty()"><@spring.message "createAddress.title" /></h1>
                    <h1 ng-if="isReadOnly()"><@spring.message "readAddress.title" /></h1>
                    <form id="createUpdateForm" name="createUpdateForm" class="form-horizontal" ng-submit="createUpdate()" novalidate>
                        <#--<div class="alert alert-success">-->
                            <#--<i class="icon-ok-sign icon-green"></i> <span> <@spring.message code=success /></span>-->
                        <#--</div>-->

                        <p ng-if="isPersonEmpty()"><@spring.message "uc.subCreateTitle" /></p>
                        <p ng-if="isReadOnly()"><@spring.message "uc.readTitle" /></p>
                        <p ng-if="isEdit()"><@spring.message "uc.subUpdateTitle" /></p>
                        </p>
                        <br/>
                        <div class="form-group" >
                            <label for="firstName" class="col-sm-1"><@spring.message "firstname" /></label>
                            <div class="col-sm-3">
                            <input type="hidden" name="id" id="id"  ng-model="person.id"/>
                            <input type="text" class="form-control" id="firstName" name="firstName" autofocus ng-model="person.firstName" ng-readonly="isReadOnly()" />
                            </div>
                            <label for="lastName" class="col-sm-1"><@spring.message "lastname" /> *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="lastName" name="lastName" ng-model="person.lastName"  ng-readonly="isReadOnly()" ng-required="true" />
                                <div ng-show="createUpdateForm.lastName.$touched && createUpdateForm.lastName.$invalid"><small class="error"><@spring.message "error.lastName.mandatory" /></small></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-1"><@spring.message "street" /> *</label>
                            <div class="col-sm-3">
                                <input type="hidden" name="mainAddress.id" id="mainAddress.id" ng-model="person.mainAddress.id" />
                                <input type="text" class="form-control" id="street" name="street" ng-model="person.mainAddress.street"  ng-readonly="isReadOnly()" ng-required="true" />
                                <div ng-show="createUpdateForm.street.$touched && createUpdateForm.street.$invalid"><small class="error"><@spring.message "error.street.mandatory" /></small></div>
                            </div>
                            <label for="houseNumber" class="col-sm-1"><@spring.message "housenumber" /> *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="houseNumber" name="houseNumber"  ng-model="person.mainAddress.houseNumber" ng-readonly="isReadOnly()" ng-required="true" />
                                <div ng-show="createUpdateForm.houseNumber.$touched && createUpdateForm.houseNumber.$invalid"><small class="error"><@spring.message "error.housenumber.mandatory" /></small></div>
                            </div>
                            <label for="box" class="col-sm-1"><@spring.message "box" /></label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="box" name="mainAddress.box"  ng-readonly="isReadOnly()" ng-model="person.mainAddress.box" />
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="city" class="col-sm-1"><@spring.message "city" /> *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control autocomplete-suggestions" id="municipalityId" name="municipalityId" ng-model="person.mainAddress.municipality.id" />
                                <input type="text" class="form-control autocomplete-suggestions" id="city" name="city"  ng-readonly="isReadOnly()" ng-required="true" ng-model="person.mainAddress.municipality.city" />
                                <div ng-show="createUpdateForm.city.$touched && createUpdateForm.city.$invalid"><small class="error"><@spring.message "error.city.mandatory" /></small></div>
                            </div>
                            <label for="zipcode" class="col-sm-1"><@spring.message "zipcode" /> *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="zipCode" name="zipCode"  ng-readonly="isReadOnly()" ng-model="person.mainAddress.municipality.zipCode"  />
                                <div ng-show="createUpdateForm.zipCode.$dirty && createUpdateForm.zipCode.$invalid"><small class="error"><@spring.message "error.zipcode.mandatory" /></small></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobilePhone" class="col-sm-1"><@spring.message "mobilePhone" /></label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="mobilePhone" name="mobilePhone"  ng-readonly="isReadOnly()" ng-model="person.mobilePhone" />
                            </div>
                            <label for="phone" class="col-sm-1"><@spring.message "phone" /></label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="phone" name="phone"  ng-readonly="isReadOnly()" ng-model="person.phone" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emails" class="col-sm-1"><@spring.message "emails" /></label>
                            <div class="col-sm-3">
                                <textarea class="form-control" name="emails" type="email" ng-readonly="isReadOnly()" ng-model="person.emails"></textarea>
                            </div>
                        </div>

                        <div class="buttons">
                            <button class="btn bold" id="btn_save" ng-disabled="createUpdateForm.$invalid"><@spring.message "button.save" /></button>
                            <button type="reset" class="btn btn-default"><@spring.message "button.reset" /></button>
                            <div ng-if="isReadOnly() || isEdit()">
                                <a href="<@spring.url '/print/' />" target="_blank" class="btn btn-info" role="button"><@spring.message "button.print" /></a>
                            </div>

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
