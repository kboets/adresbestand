<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html lang="en" ng-app="adresbestand">
<head>
    <@header.headmeta/>
        <script src="<@spring.url '/js/adresbestand_searchResult.js'/>"></script>
    <title><@spring.message "searchAddress.title" /></title>
</head>
<body>

    <div id="wrapper">
        <!-- Sidebar -->
        <@sidebar.menu/>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid" ng-controller="searchController" ng-init="searchAll()">
                <div class="row">
                    <div class="col-lg-12">
                        <h1><@spring.message "showAll.title" /></h1>
                        <p><@spring.message "showAll.subtitle" /></p>
                        <form id="allAddressForm"   name="allAddressForm" class="form-horizontal" ng-submit="print()">
                        <dir-pagination-controls></dir-pagination-controls>

                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="col-md-1"></th>
                                <th class="col-md-1"></th>
                                <th class="col-md-2"><@spring.message "showAll.firstname" /></th>
                                <th class="col-md-2"><@spring.message "showAll.lastname" /></th>
                                <th class="col-md-4"><@spring.message "showAll.address" /></th>
                                <th class="col-md-2"><@spring.message "showAll.phone" /></th>
                            </tr>
                            </thead>
                            <tr dir-paginate="person in persons | itemsPerPage: 10">
                                <td><input type="checkbox"  checklist-model="selected.persons"  checklist-value="person" /> </td>
                                <td><a href="<@spring.url '/update/{{person.id}}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                    &nbsp;
                                    <a href="<@spring.url '/view/{{person.id}}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>
                                    &nbsp;
                                    <a href="personRemoveModal" data-toggle="modal" data-id="{{person.id}}" data-target="#personRemoveModal" class="btn btn-danger btn-xs announce"><span class="glyphicon glyphicon-remove" aria-hidden="true" ></span></a>
                                </td>
                                <td>{{person.firstName}}</td>
                                <td>{{person.lastName}}</td>
                                <td>{{person.mainAddress.street}} {{person.mainAddress.houseNumber}} {{person.mainAddress.box}} <br />{{person.mainAddress.municipality.zipCode}} {{person.mainAddress.municipality.city}}</td>
                                <td>{{person.phone}} <br/>{{person.mobilePhone}}</td>
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
                        </table>
                    </div>
                    <div class="buttons">
                        <button class="btn btn-info" id="btn_print" ><@spring.message "button.print" /></button>
                        <#--<button class="btn btn-info" id="btn_alle" ng-click="checkAll()"><@spring.message "button.all" /></button>-->
                    </div>
                    </form>
                </div>
            </div>
        </div>

    </div>

</body>

</html>
