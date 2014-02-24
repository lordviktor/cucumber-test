<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="../shared/_header.jsp"></jsp:include>

    <div class="span-24 prepend-top last">
        <h1>Novo Projeto</h1>
        <hr/>
    </div>
    
    <c:if test="${(errors != null)}">
        <div class="span-24 prepend-top last">
	        <div class="errorExplanation">
	            <h2>Não foi possível salvar o projeto, pelo(s) seguinte(s) motivo(s):</h2>
	            <ul>
	                <c:forEach var="error" items="${errors}">
	                    <li>${error.category}</li>
	                </c:forEach>
	            </ul>
	        </div>
        </div>
    </c:if>
    
    
    <div class="span-24 prepend-top last">
        <div class="form">
		    <form method="post" action="<c:url value="/projetos/cadastrar"/>">
		        <jsp:include page="_form.jsp"/>
		    </form>
	    </div>
    </div>
    
    <jsp:include page="../shared/_footer.jsp"></jsp:include>
