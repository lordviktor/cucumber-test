<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <jsp:include page="../shared/_header.jsp"></jsp:include>

    <!--  Título da página -->
    <div class="span-24 prepend-top last">
    
        <div class="span-12">
		  <h1>Cadastro de projetos.</h1>
		</div>
		
		<div class="span-12 last right_aligned">
			<a href="<c:url value="/projetos/novo"/>" class="button_new">
			   Novo Projeto
			</a>
		</div>
		
		<hr/>
	</div>
	
	<!--  Mensagens de alerta e notificação. -->
	<c:if test="${notice != null}">
	   <div class="span-24 prepend-top last">
		   <div class="message">
		       <p>${notice}</p>
		   </div>
	   </div>
	</c:if>
	
	<!-- Grid de projetos. -->
	<div class="span-24 prepend-top last grid">
		<table cellspacing="0">
			<thead>
			<tr>
				<th class="left_aligned" colspan="2">Projeto </th> 
				<th>&nbsp;</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${empty projetoList}">
				 	<tr>
				 		<td colspan="3" class="center_aligned">
				 		     Não há projetos cadastrados.
				 		</td>
				 	</tr>
				</c:if>
	            <c:if test="${not empty projetoList}">
	                <c:forEach var="projeto" items="${projetoList}">
	                    <tr>
	                        <td style="width:48px">
	                           <img src="<c:url value="/images/folder.png"/>" style="width:48px;height:48px"/>
	                        </td>
	                        <td>
	                            <a href="<c:url value="/planoTestes/index?id=${projeto.id}"/>">
	                                <strong>${projeto.titulo}</strong>
	                            </a><br/>
	                            <small>${projeto.descricao}</small>
	                        </td>
	                        <td style="width:100px;" class="center_aligned">
	                            <a href="<c:url value="/projetos/editar?id=${projeto.id}"/>" class="button_edit">
	                                Editar
	                            </a>&nbsp;
	                            <a href="<c:url value="/projetos/remover?id=${projeto.id}"/>" 
	                               onclick="return confirm('Deseja realmente excluir o projeto?');"
	                               class="button_remove">
	                                Remover
	                            </a>
	
	                        </td>
	                    </tr>
	                </c:forEach>
	            </c:if>
			</tbody>
		</table>
	</div>

    <jsp:include page="../shared/_footer.jsp"></jsp:include>
