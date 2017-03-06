<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><@spring.message "searchAddress.title" /></title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
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
                    <h1 th:text="#{createAddress.title}"/>
                    <form action="#" th:action="@{/create}" th:object="${person}" method="post" class="form-horizontal">

                        <div th:if="${#fields.hasGlobalErrors()}">
                            <div class="alert alert-danger">
                                <span><label class="control-label" th:each="err : ${#fields.globalErrors()}"
                                             th:text="${err}"/></span>
                            </div>
                        </div>
                        <p>Geef hier het nieuwe adres in. De velden met een * zijn verplicht.</p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-2">Voornaam *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="firstName" th:field="*{firstName}"/>
                            </div>
                            <label for="lastName" class="col-sm-1">Naam *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="lastName" th:field="*{lastName}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-2">Straat *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="street" th:field="*{mainAddress.street}"/>
                            </div>
                            <label for="houseNumber" class="col-sm-1">Nummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="houseNumber" th:field="*{mainAddress.number}"/>
                            </div>
                            <label for="box" class="col-sm-1">Bus</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="box" th:field="*{mainAddress.number}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="city" class="col-sm-2">Gemeente *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="city" th:field="*{mainAddress.city}"/>
                            </div>
                            <label for="zipcode" class="col-sm-1">Postnummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="zipcode" th:field="*{mainAddress.zipCode}"/>
                            </div>
                        </div>
                        <div class="buttons">
                            <button class="btn bold" id="btn_save">Bewaar</button>
                            <button type="reset" class="btn btn-default">Reset</button>
                        </div>
                    </form>
                    <br/><br/>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>


</body>

</html>
