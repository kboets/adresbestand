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
    <div id="page-content-wrapper" >
        <div class="container-fluid" ng-controller="searchController">
            <div class="row">
                <div class="col-lg-12">
                    <h1><@spring.message "searchAddress.title" /></h1>
                    <form name="searchAddressForm"  class="form-horizontal" ng-submit="searchPersons(searchObject)">
                        <span ng-show="searchAddressForm.$invalid">Gelieve tenminste een criteria in te geven</span>
                        <p><@spring.message "search.intro1" /> <br/>
                        <@spring.message "search.intro2" />
                        </p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2"><@spring.message "firstname" /></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="firstName" name="firstName" ng-model="searchObject.firstName" autofocus />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2"><@spring.message "lastname" /></label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="lastName" name="lastName" minlength="2"  ng-model="searchObject.lastName"/>
                            </div>
                        </div>

                        <div class="buttons">
                            <button type="submit" class="btn btn-primary" ><@spring.message "button.search" /></button>
                            <button class="btn btn-default" type="reset"><@spring.message "button.reset" /></button>
                        </div>

                    </form>
                </div>
               <br/><br/>
                <div class="col-lg-12">
                <form id="searchAddressForm2"   name="searchAddressForm2" class="form-horizontal" ng-submit="print()">
                <div ng-show="persons.length !== 0 && searched">
                    <h2><@spring.message "search.result.title"/></h2>
                    <p><@spring.message "search.result.intro"/></p>
                    <div class="col-lg-12">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th><@spring.message "firstname" /></th>
                                <th><@spring.message "lastname" /></th>
                                <th><@spring.message "adres" /></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="person in persons">
                                    <td><input type="checkbox"  checklist-model="selected.persons"  checklist-value="person" /> </td>
                                    <td><a href="<@spring.url '/update/{{person.id}}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                        &nbsp;
                                        <a href="<@spring.url '/view/{{person.id}}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>
                                        &nbsp;
                                        <a href="personRemoveModal" data-toggle="modal" data-id="{{person.id}}" data-target="#personRemoveModal" class="btn btn-danger btn-xs announce"><span class="glyphicon glyphicon-remove" aria-hidden="true" ></span></a>
                                    </td>
                                    <td>{{person.firstName}}</td>
                                    <td>{{person.lastName}}</td>
                                    <td>{{person.mainAddress.street}} {{person.mainAddress.houseNumber}} {{person.mainAddress.box}}
                                        <br/>{{person.mainAddress.municipality.zipCode}} {{person.mainAddress.municipality.city}}
                                        <br/>{{person.phone}} <br/> {{person.mobilePhone}}</td>
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
                            </tbody>
                        </table>
                        <div class="buttons" ng-show="{{checkedPersons.length !== 0}}">
                            <button class="btn btn-info" id="btn_print" ><@spring.message "button.print" /></button>
                            <button class="btn btn-info" id="btn_alle" ng-click="checkAll()"><@spring.message "button.all" /></button>
                            {{checkedPersons}}
                        </div>
                    </div>

                <div ng-show="persons.length === 0 && searched">
                    <div class="col-lg-12">
                        <h2><@spring.message "search.result.title"/></h2>
                        <p><@spring.message "search.noresult.intro"/><br/><@spring.message "search.noresult.intro2"/></p>
                    </div>
                </div>

                </form>
                </div>

            </div>
           </div>
    </div>

</body>
</html>
