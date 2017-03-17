<#macro menu>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a href="/adresbestand/"><@spring.message "home.link" /></a>
        </li>
        <li>
            <a href="<@spring.url '/findAll'/>"><@spring.message "findAll.link" /></a>
        </li>
        <li>
            <a href="<@spring.url '/create'/>"><@spring.message "create.link" /></a>
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

</#macro>