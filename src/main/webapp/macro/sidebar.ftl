<#macro menu>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a href="/adresbestand/"><@spring.message "home.link" /></a>
        </li>
        <li>
            <a href="<@spring.url '/'/>"><@spring.message "search.link" /></a>
        </li>
        <li>
            <a href="<@spring.url '/findAll/1'/>"><@spring.message "findAll.link" /></a>
        </li>
        <li>
            <a href="<@spring.url '/create'/>"><@spring.message "create.link" /></a>
        </li>
    </ul>
</div>

</#macro>