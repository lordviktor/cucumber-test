<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <jsp:include page="../shared/_header.jsp"></jsp:include>

    <!--  Título da página -->
    <div class="span-24 prepend-top last">

        <div class="span-12">
          <h1>Log de Execucão.</h1>
          <h3>${caso_teste.titulo}</h3>
        </div>
    
        <div class="span-12 last right_aligned">
            
            <a href="<c:url value="/casoTestes/index?id=${plano_teste_id}"/>" class="button_back">
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
            <th class="center_aligned">Data</th> 
            <th class="center_aligned">Status</th> 
            <th class="left_aligned">Mensagem</th> 
        </tr>
        </thead>
        <tbody>
                <c:if test="${empty logExecucaoList}">
                    <tr>
                        <td colspan="3" class="center_aligned">
                             Não há logs de execução cadastrados.
                        </td>
                    </tr>
                </c:if>
                <c:if test="${not empty logExecucaoList}">
                    <c:forEach var="logExecucao" items="${logExecucaoList}">
                        <tr>
                            <td class="center_aligned">
                                <fmt:formatDate value="${logExecucao.dataExecucao}" type="both" pattern="dd/MM/yyyy HH:mm:ss" />
                            </td>
                            <td class="center_aligned">
                                <c:if test="${logExecucao.sucesso}">
                                    <img src="<c:url value="/images/bullet_green.png"/>" style="width:32px;height:32px"/><br/>
                                    <small>Sucesso</small>
                                </c:if>
                                <c:if test="${!logExecucao.sucesso}">
                                    <img src="<c:url value="/images/bullet_red.png"/>" style="width:32px;height:32px"/><br/>
                                    <small>Falha</small>
                                </c:if>
                            </td>
                            <td>
                                ${logExecucao.mensagem}
                            </td>
                        </tr>  
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
    
   <jsp:include page="../shared/_footer.jsp"></jsp:include> 