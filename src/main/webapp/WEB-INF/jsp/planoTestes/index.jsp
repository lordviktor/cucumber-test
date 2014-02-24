<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <jsp:include page="../shared/_header.jsp"></jsp:include>

    <!--  Título da página -->
    <div class="span-24 prepend-top last">
    
        <div class="span-12">
          <h1>Planos de teste.</h1>
        </div>
        
        <div class="span-12 last right_aligned">
		    <a href="<c:url value="/planoTestes/novo?id=${projeto_id}"/>"  class="button_new">
               Novo Plano
            </a>
		    &nbsp;
            <a href="<c:url value="/projetos/index"/>" class="button_back">
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
            <th class="left_aligned" colspan="2">Plano de Teste</th> 
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
                <c:if test="${empty planoTesteList}">
                    <tr>
                        <td colspan="3" class="center_aligned">
                             Não há planos de teste cadastrados.
                        </td>
                    </tr>
                </c:if>
                <c:if test="${not empty planoTesteList}">
                    <c:forEach var="planoTeste" items="${planoTesteList}">
                        <tr>
                            <td style="width:48px">
                               <img src="<c:url value="/images/folder.png"/>" style="width:48px;height:48px"/>
                            </td>
                            <td>
                                <a href="<c:url value="/casoTestes/index?id=${planoTeste.id}"/>">
                                    <strong>${planoTeste.titulo}</strong>
                                </a><br/>
                                <small>${planoTeste.descricao}</small>
                                <br><br><small>Versão:${planoTeste.versao}</small>
                            </td>
                            <td style="width:150px;" class="center_aligned">
                                <a href="<c:url value="/planoTestes/editar?id=${planoTeste.id}"/>" class="button_edit">
                                    Editar
                                </a>&nbsp;
                                <a href="<c:url value="/planoTestes/versionar?id=${planoTeste.id}"/>" class="button_plus">
                                    Incrementar Versão
                                </a>&nbsp;
                                <a href="<c:url value="/planoTestes/remover?id=${planoTeste.id}"/>" 
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
    