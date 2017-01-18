<%@ include file="/include-internal.jsp"%>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>

<jsp:useBean id="name" class="sferencik.teamcity.parametrics.Names"/>

<l:settingsGroup title="ParaMetrics settings">
    <tr>
        <td>Parameter whose value should be used</td>
        <td><props:textProperty name="${name.parameterNameParameterName}" /></td>
    </tr>
    <tr>
        <td>Name of the statistic value to set</td>
        <td><props:textProperty name="${name.statisticNameParameterName}" /></td>
    </tr>
</l:settingsGroup>
