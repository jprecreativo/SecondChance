<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div id="tableAJAX">
    <c:choose>
        <c:when test="${!empty requestScope.items}">
            <table border='1'>
                <c:forEach var="item" items="${requestScope.items}">
                    <tr>
                        <td>${item.category}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                    <c:choose>
                        <c:when test="${empty item.status}">
                            <td>No specified.</td>
                        </c:when>    
                        <c:otherwise>
                            <td>${item.status}</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${empty item.description}">
                            <td>No specified.</td>
                        </c:when>    
                        <c:otherwise>
                            <td>${item.description}</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${empty item.ZC}">
                            <td>No specified.</td>
                        </c:when>    
                        <c:otherwise>
                            <td>${item.ZC}</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${empty item.age}">
                            <td>No specified.</td>
                        </c:when>    
                        <c:otherwise>
                            <td>${item.age}</td>
                        </c:otherwise>
                    </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>There are no items.</p>
        </c:otherwise>
    </c:choose>
</div>
