<%@ page import="com.monaco.prestamos.Role" %>

<fieldset class="form">
    <!-- Lista de roles -->
    <div class="form-group col-sm-7 ml-5">
        <label for="role">Role</label>
        <g:select class="form-control" name="role.id"
                  from="${Role.list()}"
                  value="${user?.id ? user?.role?.first()?.id : ''}"
                  optionKey="id" />
    </div>

    <f:all bean="user"/>
</fieldset>