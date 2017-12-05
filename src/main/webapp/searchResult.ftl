<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html lang="en">
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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1><@spring.message "showAll.title" /></h1>
                        <p><@spring.message "showAll.subtitle" /></p>
                        <div class="pagination">
                            <#if currentIndex == 1>
                                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                                <li class="disabled"><a href="#">&lt;</a></li>
                            <#else>
                                <li><a href="<@spring.url '/findAll/1'/>">&lt;&lt;</a></li>
                                <li><a href="<@spring.url '/findAll/${currentIndex - 1}'/>">&lt;</a></li>
                            </#if>
                            <#list beginIndex..<(endIndex+1) as index>
                                <#if index == currentIndex>
                                    <li class="active"><a href="<@spring.url '/findAll/${index}'/>">${index}</a></li>
                                <#else>
                                    <li><a href="<@spring.url '/findAll/${index}'/>">${index}</a></li>
                                </#if>
                            </#list>
                             <#assign totalPages=pagePersons.totalPages>
                             <#if currentIndex == totalPages>
                                 <li class="disabled"><a href="#">&gt;</a></li>
                                 <li class="disabled"><a href="#">&gt;&gt;</a></li>
                             <#else>
                                 <li><a href="<@spring.url '/findAll/${currentIndex+1}'/>">&gt;</a></li>
                                 <li><a href="<@spring.url '/findAll/${totalPages}'/>">&gt;&gt;</a></li>
                             </#if>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="col-md-1"></th>
                                <th class="col-md-2"><@spring.message "showAll.firstname" /></th>
                                <th class="col-md-2"><@spring.message "showAll.lastname" /></th>
                                <th class="col-md-4"><@spring.message "showAll.address" /></th>
                                <th class="col-md-2"><@spring.message "showAll.phone" /></th>
                            </tr>
                            </thead>
                            <#list pagePersons.content as person>
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
                                <td>${person.mainAddress.street} ${person.mainAddress.houseNumber}, ${person.mainAddress.municipality.zipCode?c} ${person.mainAddress.municipality.city} </td>
                                <td><#if (person.phone??)>${person.phone}<br/></#if><#if (person.mobilePhone??)> ${person.mobilePhone}</#if></td>
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
                        </table>
                    </div>
                    <div class="buttons">
                        <a href="<@spring.url '/print' />" target="_blank" class="btn btn-info" role="button"><@spring.message "button.print" /></a>
                    </div>
                </div>
            </div>
        </div>

    </div>

</body>

</html>
