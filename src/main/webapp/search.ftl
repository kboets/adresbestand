<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <@header.headmeta/>
        <script src="<@spring.url '/js/adresbestand_search.js'/>"></script>
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
                    <h1><@spring.message "searchAddress.title" /></h1>
                    <form id="searchAddressForm"  name="searchAddressForm" action="searchAddress" method="POST" class="form-horizontal">
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
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="firstName" name="firstName" autofocus />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2">Naam</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="lastName" name="lastName" minlength="2" />
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
                                    <a href="<@spring.url '/remove/${personId}'/>" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                </td>
                                <td><#if (person.firstName??)>${person.firstName}</#if></td>
                                <td>${person.lastName}</td>
                                <td>${person.mainAddress.value}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
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


    <!-- Menu Toggle Script -->
    <script>
//        $(document).ready(function() {
//            console.log("ready and set");
//            $("#menu-toggle").click(function (e) {
//                e.preventDefault();
//                $("#wrapper").toggleClass("toggled");
//            });
//
//            $("searchAddressForm").validate();
//        });



    </script>

</body>

</html>
