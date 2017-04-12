<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><@spring.message "searchAddress.title" /></title>

    <!-- Bootstrap Core CSS -->
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<@spring.url '/css/simple-sidebar.css' />" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
                        <h1>Simple Sidebar</h1>
                        <p>This template has a responsive menu toggling system. The menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will appear/disappear. On small screens, the page content will be pushed off canvas.</p>
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
                                <td><a class="btn btn-default btn-xs"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                    &nbsp;
                                    <a class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
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
