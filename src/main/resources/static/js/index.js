let name= document.querySelector("#name");
let password =document.querySelector("#password");

let invalidInput=document.querySelector("#invalid");
let users=[];
if (localStorage.getItem("user") !== null){
    users=JSON.parse(localStorage.getItem("user"));
}
function clearForm(){
    name.value='';
    password.value='';
}
function checkData(){
    for (const user of users) {
        if (user.name===name.value && user.password===password.value){
            console.log("valid data");
            clearForm();
            invalidInput.style.display="none";
            return true;
        }
    }
    clearForm();
    invalidInput.style.display="block";
    return false
}
let btn=document.querySelector("#click");
btn.addEventListener("click",function (){
    if (checkData())window.location.href="login.html";
})
