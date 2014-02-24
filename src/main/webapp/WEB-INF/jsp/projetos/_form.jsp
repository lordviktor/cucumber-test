<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="projeto.id" name="projeto.id" type="hidden" value="${projeto.id}"/>
<ol>
    <li>
        <label for="projeto.titulo">Titulo:</label>
        <input type="text" id="projeto.titulo" name="projeto.titulo" value="${projeto.titulo}"/>
    </li>
    <li>
    <label for="projeto.url">URL:</label>
        <input type="text" id="projeto.url" name="projeto.url" value="${projeto.url}" />
    </li>
    <li>
        <label for="projeto.descricao">Descrição:</label>
        <textarea rows="3" id="projeto.descricao" name="projeto.descricao">${projeto.descricao}</textarea>
    </li>
</ol>

<div class="span-24 last right_aligned">
    <hr/>
    
    <a href="javascript:submitForm()" class="button_save">
        Salvar
    </a>
    <a href="<c:url value="/projetos/index"/>" class="button_cancel">
        Cancelar
    </a>

</div>