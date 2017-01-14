<#import "/spring.ftl" as spring/>
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
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="/adresbestand/"><@spring.message "home.link" /></a>
            </li>
            <li>
                <a href="#">Dashboard</a>
            </li>
            <li>
                <a href="#">Shortcuts</a>
            </li>
            <li>
                <a href="#">Overview</a>
            </li>
            <li>
                <a href="#">Events</a>
            </li>
            <li>
                <a href="#">About</a>
            </li>
            <li>
                <a href="#">Services</a>
            </li>
            <li>
                <a href="#">Contact</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1><@spring.message "searchAddress.title" /></h1>
                    <form name="searchAddressForm" action="searchAddress" method="POST" class="form-horizontal">
                        <p>Hier kan u een of meerdere adressen opzoeken. <br/>
                            Geef een voornaam of naam in. Een van beide is verplicht.
                        </p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2">Voornaam</label>
                            <div class="col-sm-3">
                            <@spring.formInput "searchAddressForm.firstName" "" "text"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-sm-2">Naam</label>
                            <div class="col-sm-3">
                            <@spring.formInput "searchAddressForm.lastName" "" "text"/>
                            </div>
                        </div>

                        <div class="buttons">
                            <button class="btn bold" id="btn_save">zoek</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </div>
                    </form>
                </div>
                <br/><br/>
                <#if persons?? && persons?size != 0 >
                <div class="col-lg-12">
                    <h2><@spring.message "search.result.title"/></h2>
                    <p>De volgende person(en) werden teruggevonden.</p>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "firstname" /></th>
                            <th><@spring.message "lastname" /></th>
                            <th><@spring.message "adres" /></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list persons as person>
                            <tr>
                                <td>${person.firstName}</td>
                                <td>${person.lastName}</td>
                                <td>${person.mainAddress.value}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
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
