<#import "/spring.ftl" as spring/>
<#import "macro/sidebar.ftl" as sidebar>
<#import "macro/head.ftl" as header>
<html>
<head lang="en">
    <@header.headmeta/>
    <#if person.lastName??>
        <title><@spring.message "createAddress.title" /></title>
    <#else>
        <title><@spring.message "updateAddress.title" /></title>
    </#if>
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
                    <h1><#if person.lastName??>
                           <@spring.message "updateAddress.title" />
                        <#else>
                            <@spring.message "createAddress.title" />
                        </#if>
                    </h1>
                    <form name="person" action="create_update.ftl" method="POST" class="form-horizontal">
                        <p><#if person.lastName??>
                            <@spring.message "uc.subUpdateTitle" />
                        <#else>
                            <@spring.message "uc.subCreateTitle" />
                        </#if></p>
                        <br/>
                        <div class="form-group">
                            <label for="firstName" class="col-sm-1">Voornaam *</label>
                            <div class="col-sm-3">
                                <@spring.formInput "person.firstName" "size='35'" "text" />
                            </div>
                            <label for="lastName" class="col-sm-1">Naam *</label>
                            <div class="col-sm-3">
                                <@spring.formInput "person.lastName" "size='35'" "text"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-1">Straat *</label>
                            <div class="col-sm-3">
                                <@spring.formInput "person.mainAddress.street" "size='35'" "text"/>
                            </div>
                            <label for="houseNumber" class="col-sm-1">Nummer *</label>
                            <div class="col-sm-2">
                                <@spring.formInput "person.mainAddress.houseNumber" "size='5'" "text"/>
                            </div>
                            <div class="col-sm-1">
                                <label for="box">Bus</label>
                                <@spring.formInput "person.mainAddress.box" "size='3'" "text"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="city" class="col-sm-1">Gemeente *</label>
                            <div class="col-sm-3">
                                <@spring.formInput "person.mainAddress.municipality.city" "size='35'" "text" />
                            </div>
                            <label for="zipcode" class="col-sm-1">Postnummer *</label>
                            <div class="col-sm-1">
                                <@spring.formInput "person.mainAddress.municipality.zipCode" "size='4'" "text"/>
                            </div>
                        </div>
                        <div class="buttons">
                            <button class="btn bold" id="btn_save">bewaar</button>
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
