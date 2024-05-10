
function check(){
    let form = document.forms[0];
    let deadline = form.elements.namedItem("deadline").value;
    if(deadline!=null){
        form.submit();
    }
    else {
        alert("Введите дедлайн!");
        return false;
    }
}
