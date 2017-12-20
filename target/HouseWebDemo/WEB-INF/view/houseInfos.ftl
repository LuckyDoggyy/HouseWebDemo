<#import "spring.ftl" as spring>
<#import "common.ftl" as common>

<@common.page "House Informations" >
<ul>
    <@common.selection param="Area" count=3 conditions=["under 50m2", "50-100m2", "above100m2"] />
    <@common.selection param="Price" count=3 conditions=["under 100W", "100-150W", "above 150W"] />
    <@common.selection param="Bedroom" count=5 conditions=[1,2,3,4,">5"] />
</ul>
    <@common.infoTable />
</@common.page>

