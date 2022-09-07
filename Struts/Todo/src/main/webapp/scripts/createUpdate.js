var CREATEUPDATE = {
    createNewTodo: function (){
        var html = `
            <h1 class="display-6 mt-5">
                Add Todo
            </h1>
            <div class="input-group mb-3 mt-3">
              <input type="text" id="task"  autocomplete="off" class="form-control" placeholder="Todo" >
              <button class="btn btn-outline-primary" type="button" id="button-addon2" onclick="CREATEUPDATE.createTodo()">Create</button>
            </div>
        `

        $('#body').html(html)
    },
    updateOldTodo: function (id,task){
        var html = `
            <h1 class="display-6 mt-5">
                Edit Todo: ${id} 
            </h1>
            <div class="input-group mb-3 mt-3">
              <input type="text" id="task" class="form-control" autocomplete="off" placeholder="Todo" value="${task}">
              <button class="btn btn-outline-primary" type="button" id="button-addon2" onclick="CREATEUPDATE.updateTodo(${id})">Update</button>
            </div>
        `

        $('#body').html(html)
    },
    updateTodo(id) {
        var task = $("#task").val();
        $.post(
            "update",
            {id,task},
            function (data,status){
                NAVIGATION.showTodo()
            }
        )
    },
    createTodo() {
        var task = $("#task").val();
        $.post(
            "create",
            {task},
            function (data,status){
                NAVIGATION.showTodo()
            }
        )
    }
}