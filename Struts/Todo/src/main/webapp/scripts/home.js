var HOME = {
    loadAllTodo: function () {
        $.get(
            'get',
            function (data,status){
                if(status === "success"){
                    html = `<h1 class=\"display-6 mt-5\">All Todos</h1><table class=\"w-100 table table-striped table-hover mt-3\"><thead class=\"table-dark\"><tr><th>ID</th><th>Task</th><th>Actions</th></tr></thead><tbody>`

                    data.result.list.forEach(todo=>{
                        html += `
                            <tr>
                                <td>${todo.id}</td>
                                <td>${todo.task}</td>
                                <td>
                                    <button class="btn btn-outline-info btn-sm" onclick="HOME.editTODO(${todo.id},'${todo.task}')" >Edit</button>
                                    <button class="btn btn-outline-danger btn-sm" onclick="HOME.deleteTodo(${todo.id},'${todo.task}')" >Delete</button>
                                </td>
                        `
                    })

                    html += `</tbody></table>`
                }else{
                    html = `<h1 class="display-6 mt-5 text-danger">Something Went Wrong</h1>`
                }
                $('#body').html(html)
            }
        )
    },

    deleteTodo: function (id,task){
        $.post(
            'delete',
            {id},
            function (data,status){
                HOME.loadAllTodo()
            }
        )
    },
    editTODO(id, task) {
        NAVIGATION.showEdit(false)
        CREATEUPDATE.updateOldTodo(id,task)
    }
}