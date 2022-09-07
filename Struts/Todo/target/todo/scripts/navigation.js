/*
* loadContent: false if you just want to change the navigation
* */

var NAVIGATION = {
    showTodo: function (loadContent= true) {
        $('#navbarSupportedContent ul li a').filter(".active").removeClass("active")
        $('#navHome').addClass("active")
        if(loadContent){
            HOME.loadAllTodo()
        }
    },
    showEdit: function (loadContent= true) {
        $('#navbarSupportedContent ul li a').filter(".active").removeClass("active")
        $('#navEdit').addClass("active")
        if (loadContent){
            CREATEUPDATE.createNewTodo()
        }
    }
}