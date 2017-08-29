<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html lang="en">
<head>
    <@header.headmeta/>
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
                            </tr>
                            </thead>
                            <#list pagePersons.content as person>
                            <tr>
                                <#assign personId=person.id>
                                <td><a href="<@spring.url '/update/${personId}'/>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                    &nbsp;
                                    <a href="<@spring.url '/remove/${personId}'/>" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                </td>
                                <td><#if (person.firstName??)>${person.firstName}</#if></td>
                                <td>${person.lastName}</td>
                                <td>${person.mainAddress.street} ${person.mainAddress.houseNumber}, ${person.mainAddress.municipality.zipCode?c} ${person.mainAddress.municipality.city} </td>
                            </tr>
                            </#list>
                        </table>


                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>

    </script>

</body>

</html>
