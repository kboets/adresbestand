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
                                <input type="text" class="form-control" id="firstName" value=""
                                <#if person.firstName??>
                                       value="${person.firstName}"
                                </#if>
                                />
                            </div>
                            <label for="lastName" class="col-sm-1">Naam *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="lastName" value=""
                                <#if person.lastName??>
                                       value="${person.lastName}"
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="street" class="col-sm-1">Straat *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="street" value=""
                                <#if person.mainAddress?? && person.mainAddress.street??>
                                       value="${person.mainAddress.street}"
                                </#if>
                                />
                                <#--<@spring.formInput "person.mainAddress.street" "size='35'" "text"/>-->
                            </div>
                            <label for="houseNumber" class="col-sm-1">Nummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="houseNumber" value=""
                                <#if person.mainAddress?? && person.mainAddress.houseNumber??>
                                       value="${person.mainAddress.houseNumber}"
                                </#if>
                                />
                            </div>
                            <label for="box" class="col-sm-1">Bus</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="box" value=""
                                <#if person.mainAddress?? && person.mainAddress.box??>
                                       value="${person.mainAddress.box}"
                                </#if>
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="city" class="col-sm-1">Gemeente *</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="city" value=""
                                <#if person.mainAddress?? && person.mainAddress.municipality.city??>
                                       value="${person.mainAddress.municipality.city}"
                                </#if>
                                />
                            </div>
                            <label for="zipcode" class="col-sm-1">Postnummer *</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control" id="zipCode" value=""
                                <#if person.mainAddress?? && person.mainAddress.municipality.zipCode??>
                                       value="${person.mainAddress.municipality.zipCode}"
                                </#if>
                                />
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
    <script>
        $(document).ready(function() {
            $('#city').autocomplete({
                minLength: 1,
                source: function (request, response) {
                    $.getJSON("<@spring.url '/getCitiesWithName'/>", request, function(result) {
                        response($.map(result, function(item) {
                            return {
                                // following property gets displayed in drop down
                                label: item.city,
                                // following property gets entered in the textbox
                                value: item.city
                            }
                        }));
                    });
                }
            <#--source:-->
            <#--serviceUrl: '<@spring.url '/getCitiesWithName'/>',-->
            <#--paramName: "cityName",-->
            <#--delimiter: ",",-->
            <#--transformResult: function(response) {-->
            <#--return {-->
            <#--suggestions: $.map($.parseJSON(response), function(item) {-->
            <#--return { value: item.city, data: item.id };-->
            <#--})-->

            <#--};-->

            <#--}-->
            });


        });

    </script>
</div>
</body>

</html>
