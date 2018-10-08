<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html lang="en" xmlns="http://www.w3.org/1999/html" ng-app="adresbestand">
<head>
    <@header.headmeta/>
        <script src="<@spring.url '/js/adresbestand_searchResult.js'/>"></script>

    <title><@spring.message "searchAddress.title" /></title>
</head>
<body>

<div id="wrapper">
    <!-- Sidebar -->
<@sidebar.menu/>
    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1><@spring.message "searchAddress.title" /></h1>
                    <form id="searchAddressForm"  name="searchAddressForm" action="searchAddress" method="POST" class="form-horizontal">
                    <#if errors?? && errors?size != 0 >
                        <#list errors as error>
                            <div class="alert alert-danger">
                                <span><label class="control-label"><@spring.message code=error /></span>
                            </div>
                        </#list>
                    </#if>
                        <p><@spring.message "search.intro1" /> <br/>
                        <@spring.message "search.intro2" />
                        </p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2"><@spring.message "firstname" /></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="firstName" name="firstName" autofocus />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2"><@spring.message "lastname" /></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="lastName" name="lastName" minlength="2" />
                            </div>
                        </div>

                        <div class="buttons">
                            <button class="btn bold" id="btn_save"><@spring.message "button.search" /></button>
                            <button class="btn btn-default" type="reset"><@spring.message "button.reset" /></button>
                        </div>
                    </form>
                </div>
               <br/><br/>
                <div class="col-lg-12">
                <form id="searchAddressForm2"   name="searchAddressForm2" action="print" method="POST" class="form-horizontal">
                <#if persons?? && persons?size != 0 >
                    <h2><@spring.message "search.result.title"/></h2>
                    <p><@spring.message "search.result.intro"/></p>
                    <div class="col-lg-12">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th></th>
                                <th><@spring.message "firstname" /></th>
                                <th><@spring.message "lastname" /></th>
                                <th><@spring.message "adres" /></th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list persons as person>
                                <tr>
                                    <#assign personId=person.id>
                                    <td><a href="<@spring.url '/update/${personId}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                        &nbsp;
                                        <a href="<@spring.url '/view/${personId}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>
                                        &nbsp;
                                        <a href="personRemoveModal" data-toggle="modal" data-id="${personId}" data-target="#personRemoveModal" class="btn btn-danger btn-xs announce"><span class="glyphicon glyphicon-remove" aria-hidden="true" ></span></a>
                                    </td>
                                    <td><#if (person.firstName??)>${person.firstName}</#if></td>
                                    <td>${person.lastName}</td>
                                    <td>${person.mainAddress.value} <#if (person.phone??)><br/> ${person.phone}</#if><#if (person.mobilePhone??)><br/> ${person.mobilePhone}</#if></td>
                                </tr>
                                <!-- Modal -->
                                <div class="modal fade bannerformmodal" id="personRemoveModal" tabindex="-1" role="dialog" aria-labelledby="bannerformmodal" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <div class="modal-header">
                                                <h3 class="modal-title" id="exampleModalLabel"><@spring.message "removeModal.title" /></h3>
                                            </div>
                                            <div class="modal-body">
                                                <input type="hidden" name="hiddenValue" id="personId" value="1" />
                                                <@spring.message "removeModal.body" />
                                            </div>
                                            <div class="modal-footer">
                                                <button id="confirm_modal"  type="button" class="btn btn-primary"><@spring.message "removeModal.remove" /></button>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><@spring.message "removeModal.close" /></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </#list>
                            </tbody>
                        </table>
                        <div class="buttons">
                            <button class="btn btn-info" id="btn_print" formtarget="persons"><@spring.message "button.print" /></button>
                            <#--<a href="<@spring.url '/print' />" target="_blank" class="btn btn-info" role="button"><@spring.message "button.print" /></a>-->
                        </div>
                    </div>
                <#elseif persons??>
                    <div class="col-lg-12">
                        <h2><@spring.message "search.result.title"/></h2>
                        <p><@spring.message "search.noresult.intro"/><br/><@spring.message "search.noresult.intro2"/></p>
                    </div>
                </#if>

                </form>
                </div>

            </div>
           </div>
    </div>

</body>
</html>
