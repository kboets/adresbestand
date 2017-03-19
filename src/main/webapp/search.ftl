<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><@spring.message "searchAddress.title" /></title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
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
                    <h1><@spring.message "searchAddress.title" /></h1>
                    <form name="searchAddressForm" action="searchAddress" method="POST" class="form-horizontal">
                    <#if errors?? && errors?size != 0 >
                        <#list errors as error>
                            <div class="alert alert-danger">
                                <span><label class="control-label"><@spring.message code=error /></span>
                            </div>
                        </#list>
                    </#if>
                        <p>Hier kan u een of meerdere adressen opzoeken. <br/>
                            Geef een voornaam of naam in. Een van beide is verplicht.
                        </p>
                        <div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2">Voornaam</label>
                            <div class="col-sm-5" >
                            <@spring.formInput "searchAddressForm.firstName" "" "text"/>
                            <#list spring.status.errorMessages as error>
                                <b style="color: red">${error}</b>
                            </#list>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2">Naam</label>
                            <div class="col-sm-5">
                            <@spring.formInput "searchAddressForm.lastName" "" "text"/>
                            <#list spring.status.errorMessages as error>
                                <b style="color: red">${error}</b>
                            </#list>
                            </div>
                        </div>

                        <div class="buttons">
                            <button class="btn bold" id="btn_save">zoek</button>
                            <button class="btn btn-default" type="reset">reset</button>
                        </div>
                    </form>
                </div>
                <br/><br/>
            <#if persons?? && persons?size != 0 >
            <h2><@spring.message "search.result.title"/></h2>
                <p>De volgende person(en) werden teruggevonden.</p>

                <#assign firstUrl='/findAll/1'> />
                <#assign lasttUrl='/findAll/${persons.totalPages}'> />
                <#assign prevUrl='/findAll/${currentIndex - 1}'> />
                <#assign nextUrl='/findAll/${currentIndex + 1}'> />

            <div class="pagination">
                <ul>
                <#if currentIndex == 1>
                    <li class="disabled"><a href="#">&lt;&lt;</a></li>
                    <li class="disabled"><a href="#">&lt;</a></li>
                <#else>
                    <li><a href="<@spring.url '/findAll/1'/>">&lt;&lt;</a></li>
                    <li><a href="<@spring.url '/findAll/${currentIndex - 1}'/>">&lt;</a></li>
                </#if>
                <#list persons as page>
                    <#if page_index == currentIndex>
                        <li class="active"><a href="<@spring.url '/findAll/${page_index}'/>">${page_index}</a></li>
                    <#else>
                        <li><a href="<@spring.url '/findAll/${page_index}'/>"><c:out value="${i}" /></a></li>
                    </#if>
                </#list>

                    <c:choose>
                        <c:when test="${currentIndex == deploymentLog.totalPages}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
                <#--<div class="col-lg-12">-->
                    <#--<table class="table table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th><@spring.message "firstname" /></th>-->
                            <#--<th><@spring.message "lastname" /></th>-->
                            <#--<th><@spring.message "adres" /></th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                            <#--<#list persons as person>-->
                            <#--<tr>-->
                                <#--<td><#if (person.firstName??)>${person.firstName}</#if></td>-->
                                <#--<td>${person.lastName}</td>-->
                                <#--<td>${person.mainAddress.value}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->
            <#elseif persons??>
                <div class="col-lg-12">
                    <h2><@spring.message "search.result.title"/></h2>
                    <p>Er werd niemand teruggevonden met deze zoekcriteria. <br/>Gelieve opnieuw te proberen.</p>
                </div>
            </#if>
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
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
    </script>

</body>

</html>
