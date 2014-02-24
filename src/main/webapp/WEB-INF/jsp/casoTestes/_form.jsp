<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="casoTeste.id" name="casoTeste.id" type="hidden" value="${casoTeste.id}"/>
<input id="casoTeste.deleted" name="casoTeste.deleted" type="hidden" value="${casoTeste.deleted}"/>
<input id="plano_teste_id" name="plano_teste_id" type="hidden" value="${plano_teste_id}"/>

<ol>
    <li>
        <label for="casoTeste.titulo">Titulo:</label>
        <input type="text" id="casoTeste.titulo" name="casoTeste.titulo" value="${casoTeste.titulo}"/>
    </li>
    <li>
        <label for="casoTeste.descricao">Descrição:</label>
        <textarea rows="3" id="casoTeste.descricao" name="casoTeste.descricao">${casoTeste.descricao}</textarea>
    </li>
    <li>
        <label for="casoTeste.caso">Caso:</label>
        <textarea rows="3" id="casoTeste.caso" name="casoTeste.caso">${casoTeste.caso}</textarea>
    </li>
    
</ol>

<div class="span-24 last right_aligned">
    <hr/>
    
    <a href="javascript:submitForm()" class="button_save">
        Salvar
    </a>
    <a href="<c:url value="/casoTestes/index?id=${plano_teste_id}"/>" class="button_cancel">
        Cancelar
    </a>

</div>
    
