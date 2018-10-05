
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<%@ attribute name="countOfPages" required="true" %>
<%@ attribute name="curPage" required="true" %>
<%@ attribute name="url" required="true" %>

<c:if test="${countOfPages != 1}">
    <ul class="pagination">
        <c:choose>
            <c:when test="${countOfPages < 6}">
                <c:forEach begin="1" end="${countOfPages}" var="i">
                    <c:if test="${i == curPage}">
                        <li class="active"><a href="#">${i}</a></li>
                    </c:if>
                    <c:if test="${i != curPage}">
                        <li><a href="${url}?page=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:if test="${curPage > 3}">
                    <c:set var="offset" value="0"/>
                    <c:if test="${curPage + 2 > countOfPages}">
                        <c:set var="offset" value="${curPage + 2 - countOfPages}"/>
                    </c:if>
                    <li><a href="${url}?page=1">&larr; В начало</a></li>
                    <c:set var="begin" value="${curPage - 2 - offset}"/>
                </c:if>
                <c:if test="${curPage <= 3}">
                    <c:set var="begin" value="1"/>
                </c:if>
                <c:forEach begin="${begin}" end="${begin + 4}" var="i">
                    <c:if test="${i == curPage}">
                        <li class="active"><a href="#">${i}</a></li>
                    </c:if>
                    <c:if test="${i != curPage}">
                        <li><a href="${url}?page=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${curPage < countOfPages - 2}">
                    <li><a href='${url}?page=${count}'>В конец &rarr;</a></li>
                </c:if>
            </c:otherwise>
        </c:choose>
    </ul>
</c:if>