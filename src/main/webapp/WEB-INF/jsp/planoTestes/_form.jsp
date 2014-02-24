<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="planoTeste.id" name="planoTeste.id" type="hidden" value="${planoTeste.id}"/>
<input id="projeto_id" name="projeto_id" type="hidden" value="${projeto_id}"/>

<ol>
    <li>
        <label for="planoTeste.titulo">Titulo:</label>
        <input type="text" id="planoTeste.titulo" name="planoTeste.titulo" value="${planoTeste.titulo}"/>
    </li>
    <li>
        <label for="planoTeste.descricao">Descrição:</label>
        <textarea rows="3" id="planoTeste.descricao" name="planoTeste.descricao">${planoTeste.descricao}</textarea>
    </li>
    <li>
        <label for="planoTeste.versão">Versão:</label>
        <input type="text" id="planoTeste.versao" name="planoTeste.versao" value="${planoTeste.versao}"/>
    </li>
</ol>

<div class="span-24 last right_aligned">
    <hr/>
    
    <a href="javascript:submitForm()" class="button_save">
        Salvar
    </a>
    <a href="<c:url value="/planoTestes/index?id=${projeto_id}"/>" class="button_cancel">
        Cancelar
    </a>

</div>
