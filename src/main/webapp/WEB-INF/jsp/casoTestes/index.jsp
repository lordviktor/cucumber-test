<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <jsp:include page="../shared/_header.jsp"></jsp:include>

    <!--  Título da página -->
    <div class="span-24 prepend-top last">

        <div class="span-12">
          <h1>Casos de teste.</h1>
        </div>
    
	    <div class="span-12 last right_aligned">
		    <a href="<c:url value="/casoTestes/novo?id=${plano_teste_id}"/>" class="button_new">
		       Novo Teste.
		    </a>
		    &nbsp;
	        <a href="<c:url value="/planoTestes/index?id=${projeto_id}"/>" class="button_back">
	            Voltar
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
            <th class="left_aligned" colspan="2">Caso de Teste</th> 
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
                <c:if test="${empty casoTesteList}">
                    <tr>
                        <td colspan="3" class="center_aligned">
                             Não há casos de teste cadastrados.
                        </td>
                    </tr>
                </c:if>
                <c:if test="${not empty casoTesteList}">
                    <c:forEach var="casoTeste" items="${casoTesteList}">
                        <tr>
                            <td style="width:48px">
                               <img src="<c:url value="/images/folder.png"/>" style="width:48px;height:48px"/>
                            </td>
                            <td>
                                <a href="<c:url value="/logExecucaos/index?id=${casoTeste.id}"/>">
                                <strong>${casoTeste.titulo}</strong>
                                </a><br/>
                                <small>${casoTeste.descricao}</small>
                                <br/><br/>
                                <small>
	                               <span style="color:blue">
	                               Total de execuções: ${casoTeste.runTimes}
	                               </span>
	                            </small>
                            </td>
                            <td style="width:150px;" class="center_aligned">
                                <a href="<c:url value="/casoTestes/editar?id=${casoTeste.id}"/>" class="button_edit">
                                    Editar
                                </a>&nbsp;
                                <a href="<c:url value="/casoTestes/executarTeste?id=${casoTeste.id}"/>" class="button_run">
                                    Executar caso de teste.
                                </a>&nbsp;                   
                                <a href="<c:url value="/casoTestes/remover?id=${casoTeste.id}"/>" 
                                   onclick="return confirm('Deseja realmente excluir o projeto?');"
                                   class="button_remove">
                                    Remover
                                </a>
                                <a href="<c:url value="/logExecucaos/index?id=${casoTeste.id}"/>" class="button_print">
                                    Resultados.
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
    
   <jsp:include page="../shared/_footer.jsp"></jsp:include> 
    